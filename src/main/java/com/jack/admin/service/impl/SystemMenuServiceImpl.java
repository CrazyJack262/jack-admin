package com.jack.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.admin.entity.dao.SystemMenu;
import com.jack.admin.entity.vo.MenuMeta;
import com.jack.admin.entity.vo.MenuTree;
import com.jack.admin.entity.vo.Tree;
import com.jack.admin.mapper.SystemMenuMapper;
import com.jack.admin.service.SystemMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author crazyjack262
 * @date 2020-06-24 14:04
 */
@Service
public class SystemMenuServiceImpl extends ServiceImpl<SystemMenuMapper, SystemMenu> implements SystemMenuService {

    @Override
    public List<Tree> getTrees() {
        List<SystemMenu> menus = baseMapper.selectList(Wrappers.query());
        if (CollectionUtils.isEmpty(menus)) {
            return new ArrayList<>();
        }
        // list 转map
        Map<Integer, Tree> menuMap = new LinkedHashMap<>();
        for (SystemMenu obj : menus) {
            menuMap.put(obj.getId(), this.menuToJsTree(obj));
        }
        Integer key;
        Integer pKey;
        SystemMenu userOrg;
        // 归集子类
        for (int i = menus.size() - 1; i >= 0; i--) {
            userOrg = menus.get(i);
            key = userOrg.getId();
            pKey = userOrg.getParentId();
            if (pKey != 0) {
                if (menuMap.containsKey(pKey)) {
                    Tree jsTree = menuMap.get(pKey);
                    jsTree.addChildren(menuMap.get(key));
                }
                menuMap.remove(key);
            }
        }
        // 便利map，返回结果list
        List<Tree> result = new ArrayList<>();
        for (Map.Entry<Integer, Tree> entry : menuMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    @Override
    public List<MenuTree> getPcTrees() {
        List<SystemMenu> menus = baseMapper.selectList(Wrappers.query());
        if (CollectionUtils.isEmpty(menus)) {
            return new ArrayList<>();
        }
        // list 转map
        Map<Integer, MenuTree> menuMap = new LinkedHashMap<>();
        for (SystemMenu obj : menus) {
            menuMap.put(obj.getId(), this.pcMenuToJsTree(obj));
        }
        Integer key;
        Integer pKey;
        SystemMenu userOrg;
        // 归集子类
        for (int i = menus.size() - 1; i >= 0; i--) {
            userOrg = menus.get(i);
            key = userOrg.getId();
            pKey = userOrg.getParentId();
            if (pKey != 0) {
                if (menuMap.containsKey(pKey)) {
                    MenuTree jsTree = menuMap.get(pKey);
                    jsTree.addChildren(menuMap.get(key));
                }
                menuMap.remove(key);
            }
        }
        // 便利map，返回结果list
        List<MenuTree> result = new ArrayList<>();
        for (Map.Entry<Integer, MenuTree> entry : menuMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    private Tree menuToJsTree(SystemMenu obj) {
        Tree tree = new Tree();
        tree.setId(obj.getId());
        tree.setLabel(obj.getMenuName());
        return tree;
    }

    /**
     * Pc侧边栏
     *
     * @param obj
     * @return
     */
    private MenuTree pcMenuToJsTree(SystemMenu obj) {
        MenuTree menu = new MenuTree();
        MenuMeta menuMeta = new MenuMeta();
        menu.setPath(obj.getMenuUrl());
        menu.setComponent(obj.getMenuComponent());
        menuMeta.setTitle(obj.getMenuName());
        menuMeta.setIcon(obj.getMenuIcon());
        menu.setMeta(menuMeta);
        return menu;
    }
}
