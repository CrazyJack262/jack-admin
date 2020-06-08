package com.jack.admin.controller;

import com.jack.admin.entity.vo.SystemUserVo;
import com.jack.admin.service.SystemUserService;
import com.jack.admin.util.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/testdemo")
    public ResponseEntity<String> testdemo() {
        return ResponseEntity.ok("我爱蛋炒饭");
    }

}
