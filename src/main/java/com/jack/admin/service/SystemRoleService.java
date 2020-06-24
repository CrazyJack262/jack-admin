package com.jack.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.admin.entity.dao.SystemRole;

/**
 * @author crazyjack262
 * @date 2020-06-24 11:13
 */
public interface SystemRoleService extends IService<SystemRole> {

    /**
     * 分页查询角色
     *
     * @param page
     * @param limit
     * @param roleName
     * @return
     */
    IPage<SystemRole> search(Integer page, Integer limit, String roleName);
}
