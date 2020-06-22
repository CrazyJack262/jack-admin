package com.jack.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.admin.entity.dao.SystemOrg;

/**
 * @author crazyjack262
 * @date 2020-06-22 15:44
 */
public interface SystemOrgService extends IService<SystemOrg> {

    /**
     * 获取树形态org
     *
     * @return
     */
    Object getTrees();

}
