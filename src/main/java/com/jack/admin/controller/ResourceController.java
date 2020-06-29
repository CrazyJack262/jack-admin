package com.jack.admin.controller;

import com.jack.admin.entity.dao.SystemResource;
import com.jack.admin.entity.vo.SystemResourceVo;
import com.jack.admin.service.SystemResourceService;
import com.jack.admin.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author crazyjack262
 * @date 2020-06-28 12:07
 */
@Slf4j
@RestController
@RequestMapping("resource")
@Api(description = "资源控制器")
public class ResourceController {

    @Autowired
    private SystemResourceService resourceService;

    @ApiOperation(value = "资源列表条件查询", notes = "资源列表条件查询", response = SystemResource.class)
    @GetMapping("search")
    public Object search(@ApiParam(value = "页码", defaultValue = "1") @RequestParam Integer page,
                         @ApiParam(value = "每页行数", defaultValue = "10") @RequestParam Integer limit,
                         @ApiParam(value = "资源名", defaultValue = "resourceName") @RequestParam(required = false) String resourceName,
                         @ApiParam(value = "资源名", defaultValue = "1") @RequestParam Integer menuId) {
        return Result.ok(resourceService.search(page, limit, resourceName, menuId));
    }

    @ApiOperation(value = "获取资源信息", notes = "获取资源信息", response = SystemResource.class)
    @GetMapping("/{id}")
    public Object getById(@ApiParam(value = "资源id", defaultValue = "1") @PathVariable Integer id) {
        return Result.ok(resourceService.getById(id));
    }

    @ApiOperation(value = "删除指定资源", notes = "删除指定资源", response = SystemResource.class)
    @DeleteMapping("/{id}")
    public Object deleteById(@ApiParam(value = "资源id", defaultValue = "1") @PathVariable Integer id) {
        return Result.ok(resourceService.deleteById(id));
    }

    @ApiOperation(value = "更新指定资源", notes = "更新指定资源", response = SystemResource.class)
    @PutMapping("/{id}")
    public Object updateById(@ApiParam(value = "资源id", defaultValue = "1") @PathVariable Integer id, @RequestBody SystemResource vo) {
        vo.setId(id);
        return Result.ok(resourceService.updateById(vo));
    }

    @ApiOperation(value = "新增资源", notes = "新增资源", response = SystemResourceVo.class)
    @PostMapping
    public Object saveRes(@RequestBody @Validated SystemResourceVo vo) {
        return Result.ok(resourceService.saveRes(vo));
    }
}
