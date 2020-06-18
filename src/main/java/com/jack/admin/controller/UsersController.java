package com.jack.admin.controller;

import com.jack.admin.entity.dao.SystemUser;
import com.jack.admin.entity.vo.SystemUserVo;
import com.jack.admin.service.SystemUserService;
import com.jack.admin.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author crazyjack262
 * @date 2020-06-15 15:11
 */
@Slf4j
@RestController
@RequestMapping("users")
@AllArgsConstructor
@Api(description = "用户控制器")
public class UsersController {

    private final SystemUserService systemUserService;

    @ApiOperation(value = "用户列表条件查询", notes = "用户列表条件查询", response = SystemUserVo.class)
    @GetMapping("search")
    public Object search(@ApiParam(value = "页码", defaultValue = "1") @RequestParam Integer page,
                         @ApiParam(value = "每页行数", defaultValue = "10") @RequestParam Integer limit,
                         @ApiParam(value = "用户名", defaultValue = "admin") @RequestParam(required = false) String username,
                         @ApiParam(value = "用户状态 0 正常 1 异常", defaultValue = "0") @RequestParam(required = false) Integer userStatus) {
        return Result.ok(systemUserService.search(page, limit, username, userStatus));
    }

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", response = SystemUserVo.class)
    @GetMapping("/{id}")
    public Object getById(@ApiParam(value = "用户id", defaultValue = "1") @PathVariable Integer id) {
        return Result.ok(systemUserService.findById(id));
    }

    @ApiOperation(value = "删除指定用户", notes = "删除指定用户", response = SystemUserVo.class)
    @DeleteMapping("/{id}")
    public Object deleteById(@ApiParam(value = "用户id", defaultValue = "1") @PathVariable Integer id) {
        return Result.ok(systemUserService.deleteById(id));
    }

    @ApiOperation(value = "更新指定用户", notes = "更新指定用户", response = SystemUserVo.class)
    @PutMapping("/{id}")
    public Object updateById(@ApiParam(value = "用户id", defaultValue = "1") @PathVariable Integer id, @RequestBody SystemUserVo vo) {
        vo.setId(id);
        SystemUser systemUser = vo.toDao();
        return Result.ok(systemUserService.updateUserById(systemUser));
    }

    @ApiOperation(value = "新增用户", notes = "新增用户", response = SystemUserVo.class)
    @PostMapping
    public Object saveUser(@RequestBody SystemUserVo vo) {
        SystemUser systemUser = vo.toDao();
        return Result.ok(systemUserService.saveUser(systemUser));
    }
}
