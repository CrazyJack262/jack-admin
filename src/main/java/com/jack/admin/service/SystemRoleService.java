package com.jack.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.admin.entity.dao.SystemRole;
import com.jack.admin.entity.vo.SystemRoleMenuVo;

import java.util.List;

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

    /**
     * 新增角色菜单配置
     *
     * @param vo
     * @return
     */
    boolean saveRoleMenu(SystemRoleMenuVo vo);

    /**
     * 获取指定角色的菜单id
     *
     * @param roleId
     * @return
     */
    List<Integer> getMenuByRoleId(Integer roleId);
}
