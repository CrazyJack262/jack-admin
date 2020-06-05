package com.jack.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.admin.entity.dao.SystemUser;
import com.jack.admin.mapper.SystemUserMapper;
import com.jack.admin.service.SystemUserService;
import org.springframework.stereotype.Service;

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
}
