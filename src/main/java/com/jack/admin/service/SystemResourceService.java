package com.jack.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.admin.entity.dao.SystemResource;

/**
 * @author crazyjack262
 * @date 2020-06-28 12:05
 */
public interface SystemResourceService extends IService<SystemResource> {
    /**
     * 列表查询
     *
     * @param page
     * @param limit
     * @param resourceName
     * @param menuId
     * @return
     */
    Object search(Integer page, Integer limit, String resourceName, Integer menuId);
}
