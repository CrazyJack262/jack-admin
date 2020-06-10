package com.jack.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.admin.common.enumtype.ErrorCode;
import com.jack.admin.common.exception.ServiceException;
import com.jack.admin.entity.dao.SystemUser;
import com.jack.admin.entity.vo.SystemUserVo;
import com.jack.admin.mapper.SystemUserMapper;
import com.jack.admin.service.SystemUserService;
import com.jack.admin.util.JwtUtil;
import com.jack.admin.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author crazyjack262
 * @date 2020-06-05 10:57
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

    @Override
    public SystemUser getUser(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public SystemUserVo doLogin(String loginName, String password) {
        SystemUser systemUser = new SystemUser();
        systemUser.setLoginName(loginName);
        QueryWrapper<SystemUser> query = Wrappers.query(systemUser);
        SystemUser entity = baseMapper.selectOne(query);
        if (Objects.isNull(entity)) {
            throw new ServiceException(ErrorCode.USER_NOT_EXISTS);
        }
        if (entity.getUserStatus() == 1) {
            throw new ServiceException(ErrorCode.USER_ACCOUNT_STOP);
        }
        if (!StringUtils.isEmpty(password)) {
            String passwd = this.getPassword(entity.getSalt(), password);
            if (!entity.getLoginPassword().equals(passwd)) {
                throw new ServiceException(ErrorCode.WRONG_ACCOUNT_PSW);
            }
        }
        entity.setLoginPassword(null);
        // 有效载荷
        Map<String, Object> chaim = new HashMap<>();
        chaim.put("id", entity.getId());
        chaim.put("loginName", entity.getLoginName());
        chaim.put("userName", entity.getUserName());
        chaim.put("userPhone", entity.getUserPhone());
        chaim.put("avatarIcon", entity.getAvatarIcon());
        String jwtToken = JwtUtil.encode(loginName, 60 * 60 * 1000, chaim);
        SystemUserVo systemUserVo = entity.toVo();
        systemUserVo.setJwtToken(jwtToken);
        return systemUserVo;
    }

    @Override
    public SystemUserVo getUserInfo() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return JwtUtil.getUserInfo(request);
    }

    /**
     * 密码加密规则 [salt + password + key]
     *
     * @param
     * @return
     */
    public String getPassword(String salt, String password) {
        // 用户登录密码的key
        String input = salt + MD5Util.encrypt32(password) + "96a786916v830c445905d596158829bf";
        return MD5Util.encrypt32(input);
    }
}
