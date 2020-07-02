package com.jack.admin.controller;

import com.jack.admin.entity.dao.SystemRoleUser;
import com.jack.admin.entity.vo.SystemRoleUserVo;
import com.jack.admin.service.SystemRoleUserService;
import com.jack.admin.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author crazyjack262
 * @date 2020-07-02 14:38
 */
@Slf4j
@RestController
@RequestMapping("roleUser")
@Api(description = "角色用户控制器")
public class RoleUserController {

    @Autowired
    private SystemRoleUserService roleUserService;

    @ApiOperation(value = "删除指定角色用户关系", notes = "删除指定角色用户关系", response = SystemRoleUser.class)
    @DeleteMapping("/{userId}")
    public Object deleteByUserId(@ApiParam(value = "用户id", defaultValue = "1") @PathVariable Integer userId) {
        return Result.ok(roleUserService.deleteByUserId(userId));
    }

    @ApiOperation(value = "新增角色用户关系", notes = "新增角色用户关系", response = SystemRoleUserVo.class)
    @PostMapping
    public Object saveOrgUser(@Valid @RequestBody SystemRoleUserVo vo) {
        return Result.ok(roleUserService.saveRoleUser(vo));
    }
}
