package com.jack.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.admin.entity.dao.SystemMenuResource;
import com.jack.admin.entity.dao.SystemResource;
import com.jack.admin.mapper.SystemMenuResourceMapper;
import com.jack.admin.mapper.SystemResourceMapper;
import com.jack.admin.service.SystemResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author crazyjack262
 * @date 2020-06-28 12:05
 */
@Service
public class SystemResourceServiceImpl extends ServiceImpl<SystemResourceMapper, SystemResource> implements SystemResourceService {

    @Autowired
    private SystemMenuResourceMapper menuResourceMapper;


    @Override
    public Object search(Integer page, Integer limit, String resourceName, Integer menuId) {
        IPage<SystemResource> pageRet = new Page<>(page, limit);
        SystemMenuResource menuResource = new SystemMenuResource();
        menuResource.setMenuId(menuId);
        List<SystemMenuResource> systemMenuResources = menuResourceMapper.selectList(Wrappers.query(menuResource));
        if (CollectionUtils.isEmpty(systemMenuResources)) {
            return pageRet;
        }
        List<Integer> ids = systemMenuResources.stream().map(SystemMenuResource::getResId).collect(Collectors.toList());
        QueryWrapper<SystemResource> query = Wrappers.query();
        query.like(StringUtils.hasText(resourceName), "resource_name", resourceName);
        query.in("id", ids);
        return baseMapper.selectPage(pageRet, query);
    }
}
