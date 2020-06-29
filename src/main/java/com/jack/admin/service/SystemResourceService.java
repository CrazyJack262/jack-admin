package com.jack.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.admin.entity.dao.SystemResource;
import com.jack.admin.entity.vo.SystemResourceVo;

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
    IPage<SystemResource> search(Integer page, Integer limit, String resourceName, Integer menuId);

    /**
     * 新增资源
     *
     * @param vo
     * @return
     */
    boolean saveRes(SystemResourceVo vo);

    /**
     * 删除指定资源
     *
     * @param id
     * @return
     */
    boolean deleteById(Integer id);
}
