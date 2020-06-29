package com.jack.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.admin.entity.dao.SystemMenu;
import com.jack.admin.entity.vo.Tree;

import java.util.List;

/**
 * @author crazyjack262
 * @date 2020-06-24 14:04
 */
public interface SystemMenuService extends IService<SystemMenu> {

    /**
     * 获取所有菜单-树形态
     *
     * @return
     */
    List<Tree> getTrees();

}
