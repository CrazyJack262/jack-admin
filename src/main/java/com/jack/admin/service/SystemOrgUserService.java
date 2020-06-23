package com.jack.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.admin.entity.dao.SystemOrgUser;
import com.jack.admin.entity.vo.SystemOrgUserVo;

/**
 * @author crazyjack262
 * @date 2020-06-23 14:28
 */
public interface SystemOrgUserService extends IService<SystemOrgUser> {
    /**
     * 新增用户部门关系
     *
     * @param vo
     * @return
     */
    boolean saveOrgUser(SystemOrgUserVo vo);

    /**
     * 删除指定用户的 部门关系
     *
     * @param userId
     * @return
     */
    boolean deleteByUserId(Integer userId);
}
