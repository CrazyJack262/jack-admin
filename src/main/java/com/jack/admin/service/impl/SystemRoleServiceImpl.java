package com.jack.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.admin.entity.dao.SystemRole;
import com.jack.admin.entity.dao.SystemRoleMenu;
import com.jack.admin.entity.vo.SystemRoleMenuVo;
import com.jack.admin.mapper.SystemRoleMapper;
import com.jack.admin.mapper.SystemRoleMenuMapper;
import com.jack.admin.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author crazyjack262
 * @date 2020-06-24 11:13
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {

    @Autowired
    private SystemRoleMenuMapper roleMenuMapper;

    @Override
    public IPage<SystemRole> search(Integer page, Integer limit, String roleName) {
        IPage<SystemRole> pageRet = new Page<>(page, limit);
        QueryWrapper<SystemRole> query = Wrappers.query();
        query.like(StringUtils.hasText(roleName), "role_name", roleName);
        return baseMapper.selectPage(pageRet, query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleMenu(SystemRoleMenuVo vo) {
        SystemRoleMenu roleMenu = new SystemRoleMenu();
        roleMenu.setRoleId(vo.getRoleId());
        roleMenuMapper.delete(Wrappers.query(roleMenu));
        List<Integer> menuIdList = vo.getMenuIdList();
        SystemRoleMenu temp = new SystemRoleMenu();
        temp.setRoleId(vo.getRoleId());
        for (Integer menuId : menuIdList) {
            temp.setMenuId(menuId);
            roleMenuMapper.insert(temp);
        }
        return true;
    }

    @Override
    public List<Integer> getMenuByRoleId(Integer roleId) {
        SystemRoleMenu temp = new SystemRoleMenu();
        temp.setRoleId(roleId);
        List<SystemRoleMenu> systemRoleMenus = roleMenuMapper.selectList(Wrappers.query(temp));
        return systemRoleMenus.stream().map(SystemRoleMenu::getMenuId).collect(Collectors.toList());
    }
}
