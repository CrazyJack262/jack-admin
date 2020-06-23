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
     * @param page
     * @param limit
     * @param username
     * @param userStatus
     * @return
     */
    IPage<SystemUser> search(Integer page, Integer limit, String username, Integer userStatus);

    /**
     * 获取指定id用户信息
     *
     * @param id
     * @return
     */
    SystemUserVo findById(Integer id);

    /**
     * 删除指定用户
     *
     * @param id
     * @return
     */
    boolean deleteById(Integer id);

    /**
     * 新增用户
     *
     * @param systemUser
     * @return
     */
    SystemUserVo saveUser(SystemUser systemUser);

    /**
     * 更新用户
     *
     * @param systemUser
     * @return
     */
    boolean updateUserById(SystemUser systemUser);

    /**
     * 指定部门下的用户查询
     *
     * @param page
     * @param limit
     * @param orgId
     * @param username
     * @return
     */
    IPage<SystemUser> searchByOrg(Integer page, Integer limit, Integer orgId, Integer orgStatus, String username);
}
