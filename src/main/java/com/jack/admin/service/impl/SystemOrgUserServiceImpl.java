package com.jack.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.admin.common.enumtype.ErrorCode;
import com.jack.admin.common.exception.ServiceException;
import com.jack.admin.entity.dao.SystemOrgUser;
import com.jack.admin.entity.vo.SystemOrgUserVo;
import com.jack.admin.mapper.SystemOrgUserMapper;
import com.jack.admin.service.SystemOrgUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author crazyjack262
 * @date 2020-06-23 14:29
 */
@Service
public class SystemOrgUserServiceImpl extends ServiceImpl<SystemOrgUserMapper, SystemOrgUser> implements SystemOrgUserService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrgUser(SystemOrgUserVo vo) {
        List<Integer> userList = vo.getUserList();
        if (CollectionUtils.isEmpty(userList)) {
            throw new ServiceException(ErrorCode.COMMON_PARAMS_REQUIRED, "用户不能为空");
        }
        QueryWrapper<SystemOrgUser> query = Wrappers.query();
        query.in("user_id", userList);
        List<SystemOrgUser> systemOrgUsers = baseMapper.selectList(query);
        if (!CollectionUtils.isEmpty(systemOrgUsers)) {
            throw new ServiceException(ErrorCode.COMMON_SQL_NAME_EXIST, "选择用户已绑定部门");
        }
        SystemOrgUser systemOrgUser = new SystemOrgUser();
        systemOrgUser.setOrgId(vo.getOrgId());
        userList.forEach(userId -> {
            systemOrgUser.setUserId(userId);
            baseMapper.insert(systemOrgUser);
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByUserId(Integer userId) {
        SystemOrgUser orgUser = new SystemOrgUser();
        orgUser.setUserId(userId);
        int delete = baseMapper.delete(Wrappers.query(orgUser));
        return delete > 0;
    }
}
