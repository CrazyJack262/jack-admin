package com.jack.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.admin.common.enumtype.ErrorCode;
import com.jack.admin.common.exception.ServiceException;
import com.jack.admin.entity.dao.SystemOrg;
import com.jack.admin.entity.vo.Tree;
import com.jack.admin.mapper.SystemOrgMapper;
import com.jack.admin.service.SystemOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author crazyjack262
 * @date 2020-06-22 15:45
 */
@Service
public class SystemOrgServiceImpl extends ServiceImpl<SystemOrgMapper, SystemOrg> implements SystemOrgService {

    @Override
    public List<Tree> getTrees() {
        List<SystemOrg> orgList = baseMapper.selectList(Wrappers.query());
        if (CollectionUtils.isEmpty(orgList)) {
            return new ArrayList<>();
        }

        // list 转map
        Map<Integer, Tree> menuMap = new LinkedHashMap<>();
        for (SystemOrg obj : orgList) {
            menuMap.put(obj.getId(), this.orgToJsTree(obj));
        }
        Integer key;
        Integer pKey;
        SystemOrg userOrg;
        // 归集子类
        for (int i = orgList.size() - 1; i >= 0; i--) {
            userOrg = orgList.get(i);
            key = userOrg.getId();
            pKey = userOrg.getOrgPid();
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
    @Transactional(rollbackFor = Exception.class)
    public SystemOrg saveOrg(SystemOrg vo) {
        if (!StringUtils.hasText(vo.getOrgName())) {
            throw new ServiceException(ErrorCode.COMMON_PARAMS_ERR, "部门名称不能为空");
        }
        QueryWrapper<SystemOrg> query = Wrappers.query();
        query.eq("org_name", vo.getOrgName());
        List<SystemOrg> orgList = baseMapper.selectList(query);
        if (CollectionUtils.isNotEmpty(orgList)) {
            throw new ServiceException(ErrorCode.COMMON_SQL_NAME_EXIST, "部门名称已存在");
        }
        int insert = baseMapper.insert(vo);
        if (insert > 0) {
            Integer id = vo.getId();
            Integer orgPid = vo.getOrgPid();
            if (orgPid.equals(0)) {
                vo.setOrgAllCode(id.toString());
            } else {
                SystemOrg pidRecord = baseMapper.selectById(orgPid);
                vo.setOrgAllCode(pidRecord.getOrgAllCode() + "." + id);
            }
        }
        baseMapper.updateById(vo);
        return vo;
    }

    private Tree orgToJsTree(SystemOrg obj) {
        Tree tree = new Tree();
        tree.setId(obj.getId());
        tree.setLabel(obj.getOrgName());
        tree.setVersion(obj.getVersion());
        return tree;
    }
}
