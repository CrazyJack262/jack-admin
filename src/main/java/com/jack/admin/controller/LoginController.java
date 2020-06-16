package com.jack.admin.controller;

import com.jack.admin.entity.vo.SystemUserVo;
import com.jack.admin.service.SystemUserService;
import com.jack.admin.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
}
