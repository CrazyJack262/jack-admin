package com.jack.admin.controller;

import com.jack.admin.entity.dao.SystemRole;
import com.jack.admin.entity.vo.SystemRoleMenuVo;
import com.jack.admin.service.SystemRoleService;
import com.jack.admin.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author crazyjack262
 * @date 2020-06-24 11:15
 */
@Slf4j
@RestController
@RequestMapping("role")
@Api(description = "角色控制器")
public class RoleController {
    @Autowired
    private SystemRoleService roleService;

    @ApiOperation(value = "角色列表条件查询", notes = "角色列表条件查询", response = SystemRole.class)
    @GetMapping("list")
    public Object list(@ApiParam(value = "页码", defaultValue = "1") @RequestParam Integer page,
                       @ApiParam(value = "每页行数", defaultValue = "10") @RequestParam Integer limit,
                       @ApiParam(value = "角色名", defaultValue = "admin") @RequestParam(required = false) String roleName) {
        return Result.ok(roleService.search(page, limit, roleName));
    }

    @ApiOperation(value = "查询所有角色列表", notes = "查询所有角色列表", response = SystemRole.class)
    @GetMapping("listAll")
    public Object listAll() {
        return Result.ok(roleService.list());
    }

    @ApiOperation(value = "获取角色信息", notes = "获取角色信息", response = SystemRole.class)
    @GetMapping("/{id}")
    public Object getById(@ApiParam(value = "角色id", defaultValue = "1") @PathVariable Integer id) {
        return Result.ok(roleService.getById(id));
    }

    @ApiOperation(value = "删除指定角色", notes = "删除指定角色", response = SystemRole.class)
    @DeleteMapping("/{id}")
    public Object deleteById(@ApiParam(value = "角色id", defaultValue = "1") @PathVariable Integer id) {
        return Result.ok(roleService.removeById(id));
    }

    @ApiOperation(value = "更新指定角色", notes = "更新指定角色", response = SystemRole.class)
    @PutMapping("/{id}")
    public Object updateById(@ApiParam(value = "角色id", defaultValue = "1") @PathVariable Integer id, @RequestBody SystemRole vo) {
        vo.setId(id);
        return Result.ok(roleService.updateById(vo));
    }

    @ApiOperation(value = "新增角色", notes = "新增角色", response = SystemRole.class)
    @PostMapping
    public Object saveRole(@RequestBody SystemRole vo) {
        return Result.ok(roleService.save(vo));
    }

    @ApiOperation(value = "新增角色菜单配置", notes = "新增角色菜单配置", response = SystemRoleMenuVo.class)
    @PostMapping("/{roleId}/menu")
    public Object saveRoleMenu(@PathVariable Integer roleId, @RequestBody SystemRoleMenuVo vo) {
        vo.setRoleId(roleId);
        return Result.ok(roleService.saveRoleMenu(vo));
    }

    @ApiOperation(value = "查询所有角色列表", notes = "查询所有角色列表", response = SystemRole.class)
    @GetMapping("/{roleId}/menu")
    public Object getMenuByRoleId(@PathVariable Integer roleId) {
        return Result.ok(roleService.getMenuByRoleId(roleId));
    }
}
