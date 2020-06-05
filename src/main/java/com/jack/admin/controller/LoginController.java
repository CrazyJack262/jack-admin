package com.jack.admin.controller;

import com.jack.admin.entity.dao.SystemUser;
import com.jack.admin.service.SystemUserService;
import com.jack.admin.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录登出
 *
 * @author crazyjack262
 * @date 2020-06-05 09:29
 */
@Slf4j
@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    private SystemUserService systemUserService;


    @RequestMapping("/login")
    public Object login(String username, String password) {
        log.info("username:{},password:{}", username, password);
        Map<String, String> map = new HashMap<>();
        if (!"tom".equals(username) || !"123".equals(password)) {
            map.put("msg", "用户名密码错误");
            return ResponseEntity.ok(map);
        }
        SystemUser byId = systemUserService.getById(1);
        return byId;
//        JwtUtil jwtUtil = new JwtUtil();
//        Map<String, Object> chaim = new HashMap<>();
//        chaim.put("username", username);
//        String jwtToken = jwtUtil.encode(username, 5 * 60 * 1000, chaim);
//        map.put("msg", "登录成功");
//        map.put("token", jwtToken);
//        return ResponseEntity.ok(map);
    }

    @RequestMapping("/testdemo")
    public ResponseEntity<String> testdemo() {
        return ResponseEntity.ok("我爱蛋炒饭");
    }

}
