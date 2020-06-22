package com.jack.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jack.admin.entity.dao.SystemOrg;
import com.jack.admin.entity.vo.Tree;
import com.jack.admin.mapper.SystemOrgMapper;
import com.jack.admin.service.SystemOrgService;
import org.springframework.stereotype.Service;

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
    public Object getTrees() {
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

    private Tree orgToJsTree(SystemOrg obj) {
        Tree tree = new Tree();
        tree.setId(obj.getId());
        tree.setLabel(obj.getOrgName());
        tree.setVersion(obj.getVersion());
        return tree;
    }
}
