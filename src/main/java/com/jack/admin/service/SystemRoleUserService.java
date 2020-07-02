package com.jack.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.admin.entity.dao.SystemRoleUser;
import com.jack.admin.entity.vo.SystemRoleUserVo;

/**
 * @author crazyjack262
 * @date 2020-07-02 14:42
 */
public interface SystemRoleUserService extends IService<SystemRoleUser> {

    /**
     * 删除用户角色
     *
     * @param userId
     * @return
     */
    boolean deleteByUserId(Integer userId);

    /**
     * 新增用户角色
     *
     * @param vo
     * @return
     */
    boolean saveRoleUser(SystemRoleUserVo vo);
}
