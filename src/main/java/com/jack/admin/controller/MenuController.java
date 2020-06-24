package com.jack.admin.controller;

import com.jack.admin.entity.dao.SystemMenu;
import com.jack.admin.entity.vo.Tree;
import com.jack.admin.service.SystemMenuService;
import com.jack.admin.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author crazyjack262
 * @date 2020-06-24 14:06
 */
@Slf4j
@RestController
@RequestMapping("menu")
@Api(description = "菜单控制器")
public class MenuController {
    @Autowired
    private SystemMenuService menuService;

    @ApiOperation(value = "获取所有菜单-树形态", notes = "获取所有菜单-树形态", response = Tree.class)
    @GetMapping("/trees")
    public Object getTrees() {
        return Result.ok(menuService.getTrees());
    }

    @ApiOperation(value = "查询所有菜单列表", notes = "查询所有菜单列表", response = SystemMenu.class)
    @GetMapping("listAll")
    public Object listAll() {
        return Result.ok(menuService.list());
    }

    @ApiOperation(value = "获取菜单信息", notes = "获取菜单信息", response = SystemMenu.class)
    @GetMapping("/{id}")
    public Object getById(@ApiParam(value = "菜单id", defaultValue = "1") @PathVariable Integer id) {
        return Result.ok(menuService.getById(id));
    }

    @ApiOperation(value = "删除指定菜单", notes = "删除指定菜单", response = SystemMenu.class)
    @DeleteMapping("/{id}")
    public Object deleteById(@ApiParam(value = "菜单id", defaultValue = "1") @PathVariable Integer id) {
        return Result.ok(menuService.removeById(id));
    }

    @ApiOperation(value = "更新指定菜单", notes = "更新指定菜单", response = SystemMenu.class)
    @PutMapping("/{id}")
    public Object updateById(@ApiParam(value = "菜单id", defaultValue = "1") @PathVariable Integer id, @RequestBody SystemMenu vo) {
        vo.setId(id);
        return Result.ok(menuService.updateById(vo));
    }

    @ApiOperation(value = "新增菜单", notes = "新增菜单", response = SystemMenu.class)
    @PostMapping
    public Object saveUser(@RequestBody SystemMenu vo) {
        return Result.ok(menuService.save(vo));
    }
}
