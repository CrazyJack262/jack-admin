package com.jack.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.admin.common.enumtype.ErrorCode;
import com.jack.admin.common.exception.ServiceException;
import com.jack.admin.entity.dao.SystemOrgUser;
import com.jack.admin.entity.dao.SystemRoleUser;
import com.jack.admin.entity.dao.SystemUser;
import com.jack.admin.entity.vo.SystemUserVo;
import com.jack.admin.mapper.SystemOrgUserMapper;
import com.jack.admin.mapper.SystemRoleUserMapper;
import com.jack.admin.mapper.SystemUserMapper;
import com.jack.admin.service.SystemUserService;
import com.jack.admin.util.JwtUtil;
import com.jack.admin.util.MD5Util;
import com.jack.admin.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author crazyjack262
 * @date 2020-06-05 10:57
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

    @Autowired
    private SystemOrgUserMapper orgUserMapper;

    @Autowired
    private SystemRoleUserMapper roleUserMapper;

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

    @Override
    public IPage<SystemUser> search(Integer page, Integer limit, String username, Integer userStatus) {
        IPage<SystemUser> pageRet = new Page<>(page, limit);
        SystemUser condition = new SystemUser();
        condition.setUserStatus(userStatus);
        QueryWrapper<SystemUser> select = Wrappers.query(condition).select("id,login_name,user_name,user_status,user_phone,login_time,remark,fail_count,version,del_flag");
        select.like(StringUtils.hasText(username), "user_name", username);
        IPage<SystemUser> iPage = baseMapper.selectPage(pageRet, select);
        return iPage;
    }

    @Override
    public SystemUserVo findById(Integer id) {
        SystemUser systemUser = baseMapper.selectById(id);
        if (Objects.isNull(systemUser)) {
            throw new ServiceException(ErrorCode.COMMON_EMPTY_CONDITION_RESULT);
        }
        return systemUser.toVo();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Integer id) {
        int ret = baseMapper.deleteById(id);
        if (ret > 0) {
            return true;
        } else {
            throw new ServiceException(ErrorCode.COMMON_SQL_DELETE_FAIL);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SystemUserVo saveUser(SystemUser systemUser) {
        String salt = UUIDUtil.getUUID();
        String password = getPassword(salt, systemUser.getLoginPassword());
        systemUser.setSalt(salt);
        systemUser.setLoginPassword(password);
        int insert = baseMapper.insert(systemUser);
        if (insert > 0) {
            return systemUser.toVo();
        } else {
            throw new ServiceException(ErrorCode.COMMON_SQL_INSERT_FAIL);
        }
    }

    @Override
    public boolean updateUserById(SystemUser systemUser) {
        Integer version = systemUser.getVersion();
        if (Objects.isNull(version)) {
            throw new ServiceException(ErrorCode.COMMON_SQL_VERSION_NOT_EXIST);
        }
        String loginPassword = systemUser.getLoginPassword();
        if (StringUtils.hasText(loginPassword)) {
            String salt = UUIDUtil.getUUID();
            String password = getPassword(salt, systemUser.getLoginPassword());
            systemUser.setSalt(salt);
            systemUser.setLoginPassword(password);
        }
        int ret = baseMapper.updateById(systemUser);
        if (ret > 0) {
            return true;
        } else {
            throw new ServiceException(ErrorCode.COMMON_SQL_UPDATE_FAIL);
        }
    }

    @Override
    public IPage<SystemUser> searchByOrg(Integer page, Integer limit, Integer orgId, Integer orgStatus, String username) {
        List<Integer> ids = new ArrayList<>();
        if (orgId != 0) {
            SystemOrgUser orgUser = new SystemOrgUser();
            orgUser.setOrgId(orgId);
            List<SystemOrgUser> systemOrgUsers = orgUserMapper.selectList(Wrappers.query(orgUser));
            if (CollectionUtils.isEmpty(systemOrgUsers) && orgStatus == 0) {
                return new Page<>(page, limit);
            }
            ids = systemOrgUsers.stream().map(SystemOrgUser::getUserId).collect(Collectors.toList());
        }
        SystemUser condition = new SystemUser();
        QueryWrapper<SystemUser> select = Wrappers.query(condition).select("id,login_name,user_name,user_status,user_phone,login_time,remark,fail_count,version,del_flag");
        select.like(StringUtils.hasText(username), "user_name", username);
        if (orgStatus == 0) {
            select.in(CollectionUtils.isNotEmpty(ids), "id", ids);
        } else {
            select.notIn(CollectionUtils.isNotEmpty(ids), "id", ids);
        }

        IPage<SystemUser> iPage = baseMapper.selectPage(new Page<>(page, limit), select);
        return iPage;
    }

    @Override
    public IPage<SystemUser> searchByRole(Integer page, Integer limit, Integer orgId, Integer roleStatus, String username) {
        List<Integer> ids = new ArrayList<>();
        if (orgId != 0) {
            SystemRoleUser roleUser = new SystemRoleUser();
            roleUser.setRoleId(orgId);
            List<SystemRoleUser> systemOrgUsers = roleUserMapper.selectList(Wrappers.query(roleUser));
            if (CollectionUtils.isEmpty(systemOrgUsers) && roleStatus == 0) {
                return new Page<>(page, limit);
            }
            ids = systemOrgUsers.stream().map(SystemRoleUser::getUserId).collect(Collectors.toList());
        }
        SystemUser condition = new SystemUser();
        QueryWrapper<SystemUser> select = Wrappers.query(condition).select("id,login_name,user_name,user_status,user_phone,login_time,remark,fail_count,version,del_flag");
        select.like(StringUtils.hasText(username), "user_name", username);
        if (roleStatus == 0) {
            select.in(CollectionUtils.isNotEmpty(ids), "id", ids);
        } else {
            select.notIn(CollectionUtils.isNotEmpty(ids), "id", ids);
        }

        IPage<SystemUser> iPage = baseMapper.selectPage(new Page<>(page, limit), select);
        return iPage;
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
