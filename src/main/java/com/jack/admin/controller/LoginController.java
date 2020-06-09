package com.jack.admin.controller;

import com.jack.admin.entity.vo.SystemUserVo;
import com.jack.admin.service.SystemUserService;
import com.jack.admin.util.JwtUtil;
import com.jack.admin.util.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

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
public class LoginController {

    private final SystemUserService systemUserService;

    @PostMapping("/login")
    public Object login(@RequestBody SystemUserVo vo) {
        SystemUserVo systemUser = systemUserService.doLogin(vo.getLoginName(), vo.getLoginPassword());
        return Result.ok(systemUser);
    }

    @GetMapping
    public Object getUserInfo(ServletRequest servletRequest) {
        SystemUserVo userInfo = JwtUtil.getUserInfo(servletRequest);
        return Result.ok(userInfo);
    }

}
