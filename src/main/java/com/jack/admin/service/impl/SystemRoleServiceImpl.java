package com.jack.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.admin.entity.dao.SystemRole;
import com.jack.admin.mapper.SystemRoleMapper;
import com.jack.admin.service.SystemRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author crazyjack262
 * @date 2020-06-24 11:13
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {

    @Override
    public IPage<SystemRole> search(Integer page, Integer limit, String roleName) {
        IPage<SystemRole> pageRet = new Page<>(page, limit);
        QueryWrapper<SystemRole> query = Wrappers.query();
        query.like(StringUtils.hasText(roleName), "role_name", roleName);
        return baseMapper.selectPage(pageRet, query);
    }
}
