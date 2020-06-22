package com.jack.admin.controller;

import com.jack.admin.entity.dao.SystemOrg;
import com.jack.admin.entity.vo.SystemUserVo;
import com.jack.admin.entity.vo.Tree;
import com.jack.admin.service.SystemOrgService;
import com.jack.admin.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author crazyjack262
 * @date 2020-06-22 15:50
 */
@Slf4j
@RestController
@RequestMapping("org")
@AllArgsConstructor
@Api(description = "部门控制器")
public class OrgController {

    private final SystemOrgService orgService;
    @ApiOperation(value = "获取部门信息-树形态", notes = "获取部门信息-树形态", response = Tree.class)
    @GetMapping("/trees")
    public Object getTrees() {
        return Result.ok(orgService.getTrees());
    }

    @ApiOperation(value = "获取部门信息", notes = "获取部门信息", response = SystemUserVo.class)
    @GetMapping("/{id}")
    public Object getById(@ApiParam(value = "部门id", defaultValue = "1") @PathVariable Integer id) {
        return Result.ok(orgService.getById(id));
    }

    @ApiOperation(value = "删除指定部门", notes = "删除指定部门", response = SystemUserVo.class)
    @DeleteMapping("/{id}")
    public Object deleteById(@ApiParam(value = "部门id", defaultValue = "1") @PathVariable Integer id) {
        return Result.ok(orgService.removeById(id));
    }

    @ApiOperation(value = "更新指定部门", notes = "更新指定部门", response = SystemUserVo.class)
    @PutMapping("/{id}")
    public Object updateById(@ApiParam(value = "部门id", defaultValue = "1") @PathVariable Integer id, @RequestBody SystemOrg vo) {
        vo.setId(id);
        return Result.ok(orgService.updateById(vo));
    }

    @ApiOperation(value = "新增部门", notes = "新增部门", response = SystemUserVo.class)
    @PostMapping
    public Object saveOrg(@RequestBody SystemOrg vo) {
        return Result.ok(orgService.save(vo));
    }
}
