package com.jack.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jack.admin.entity.dao.SystemUser;
import com.jack.admin.entity.vo.SystemUserVo;

/**
 * @author crazyjack262
 * @date 2020-06-05 10:56
 */
public interface SystemUserService extends IService<SystemUser> {

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    SystemUser getUser(Integer id);

    /**
     * 账号密码登录
     *
     * @param loginName
     * @param password
     * @return
     */
    SystemUserVo doLogin(String loginName, String password);

    /**
     * 获取当前用户
     *
     * @return
     */
    SystemUserVo getUserInfo();

    /**
     * 用户列表查询
     *
     * @param pageSize
     * @param pageNo
     * @param username
     * @param userStatus
     * @return
     */
    IPage<SystemUser> search(Integer pageSize, Integer pageNo, String username, Integer userStatus);
}
