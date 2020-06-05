package com.jack.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.admin.entity.dao.SystemUser;
import com.jack.admin.mapper.SystemUserMapper;

/**
 * @author crazyjack262
 * @date 2020-06-05 10:56
 */
public interface SystemUserService extends IService<SystemUser> {

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    SystemUser getUser(Integer id);
}
