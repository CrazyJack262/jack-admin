package com.jack.admin.controller;

import com.jack.admin.entity.vo.SystemUserVo;
import com.jack.admin.util.JwtUtil;
import com.jack.admin.util.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

@Api("test")
@RestController
@RequestMapping("index")
public class MyController {

    @RequestMapping
    public Object index(ServletRequest servletRequest) {
        SystemUserVo userInfo = JwtUtil.getUserInfo(servletRequest);
        return Result.ok(userInfo);
    }
}
