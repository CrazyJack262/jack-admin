package com.jack.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.admin.common.enumtype.ErrorCode;
import com.jack.admin.common.exception.ServiceException;
import com.jack.admin.entity.dao.SystemRoleUser;
import com.jack.admin.entity.vo.SystemRoleUserVo;
import com.jack.admin.mapper.SystemRoleUserMapper;
import com.jack.admin.service.SystemRoleUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author crazyjack262
 * @date 2020-07-02 14:43
 */
@Service
public class SystemRoleUserServiceImpl extends ServiceImpl<SystemRoleUserMapper, SystemRoleUser> implements SystemRoleUserService {

    @Override
    public boolean deleteByUserId(Integer userId) {
        SystemRoleUser roleUser = new SystemRoleUser();
        roleUser.setUserId(userId);
        int delete = baseMapper.delete(Wrappers.query(roleUser));
        return delete > 0;
    }

    @Override
    public boolean saveRoleUser(SystemRoleUserVo vo) {
        List<Integer> userList = vo.getUserList();
        if (CollectionUtils.isEmpty(userList)) {
            throw new ServiceException(ErrorCode.COMMON_PARAMS_REQUIRED, "用户不能为空");
        }
        QueryWrapper<SystemRoleUser> query = Wrappers.query();
        query.in("user_id", userList);
        List<SystemRoleUser> roleUsers = baseMapper.selectList(query);
        if (!CollectionUtils.isEmpty(roleUsers)) {
            throw new ServiceException(ErrorCode.COMMON_SQL_NAME_EXIST, "选择用户已绑定角色");
        }
        SystemRoleUser systemRoleUser = new SystemRoleUser();
        systemRoleUser.setRoleId(vo.getRoleId());
        userList.forEach(userId -> {
            systemRoleUser.setUserId(userId);
            baseMapper.insert(systemRoleUser);
        });
        return true;
    }
}
