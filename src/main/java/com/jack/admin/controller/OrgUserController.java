package com.jack.admin.controller;

import com.jack.admin.entity.vo.SystemOrgUserVo;
import com.jack.admin.entity.vo.SystemUserVo;
import com.jack.admin.service.SystemOrgUserService;
import com.jack.admin.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author crazyjack262
 * @date 2020-06-23 15:54
 */
@Slf4j
@RestController
@RequestMapping("orgUser")
@Api(description = "用户部门关系控制器")
public class OrgUserController {

    @Autowired
    private SystemOrgUserService orgUserService;

    @ApiOperation(value = "新增部门用户关系", notes = "新增部门用户关系", response = SystemOrgUserVo.class)
    @PostMapping
    public Object saveOrgUser(@RequestBody SystemOrgUserVo vo) {
        return Result.ok(orgUserService.saveOrgUser(vo));
    }

    @ApiOperation(value = "删除指定部门用户关系", notes = "删除指定部门用户关系", response = SystemUserVo.class)
    @DeleteMapping("/{userId}")
    public Object deleteById(@ApiParam(value = "用户id", defaultValue = "1") @PathVariable Integer userId) {
        return Result.ok(orgUserService.deleteByUserId(userId));
    }
}
