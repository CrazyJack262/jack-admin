package com.jack.admin.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.jack.admin.entity.dao.SystemUser;
import com.jack.admin.entity.vo.SystemUserVo;
import com.jack.admin.service.SystemUserService;
import com.jack.admin.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Object search(@ApiParam(value = "页码", defaultValue = "1") @RequestParam Integer pageSize,
                         @ApiParam(value = "每页行数", defaultValue = "10") @RequestParam Integer pageNo,
                         String username, Integer userStatus) {
        return Result.ok(systemUserService.search(pageSize, pageNo, username, userStatus));
    }
}