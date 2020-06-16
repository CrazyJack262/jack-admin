package com.jack.admin.controller;

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
 * 登录登出
 *
 * @author crazyjack262
 * @date 2020-06-05 09:29
 */
@Slf4j
@RestController
@RequestMapping("user")
@AllArgsConstructor
@Api(description = "登录控制器")
public class LoginController {

    private final SystemUserService systemUserService;

    @ApiOperation(value = "登录", notes = "登录", response = SystemUserVo.class)
    @PostMapping
    public Object login(@RequestBody SystemUserVo vo) {
        SystemUserVo systemUser = systemUserService.doLogin(vo.getLoginName(), vo.getLoginPassword());
        return Result.ok(systemUser);
    }

    @ApiOperation(value = "获取登录用户信息", notes = "获取登录用户信息", response = SystemUserVo.class)
    @GetMapping
    public Object getUserInfo() {
        return Result.ok(systemUserService.getUserInfo());
    }

    @ApiOperation(value = "用户列表条件查询", notes = "用户列表条件查询", response = SystemUserVo.class)
    @GetMapping("search")
    public Object search(@ApiParam(value = "页码", defaultValue = "1") @RequestParam Integer pageSize,
                         @ApiParam(value = "每页行数", defaultValue = "10") @RequestParam Integer pageNo,
                         @ApiParam(value = "用户名", defaultValue = "admin") @RequestParam(required = false) String username,
                         @ApiParam(value = "用户状态 0 正常 1 异常", defaultValue = "0") @RequestParam(required = false) Integer userStatus) {
        return Result.ok(systemUserService.search(pageSize, pageNo, username, userStatus));
    }
}
