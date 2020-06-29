package com.jack.admin.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author crazyjack262
 * @date 2020-06-29 15:29
 */
@Data
@ApiModel(value = "角色菜单关系对象", description = "角色菜单关系对象")
public class SystemRoleMenuVo {

    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id", name = "roleId", required = true)
    private Integer roleId;

    @NotNull(message = "菜单不能为空")
    @ApiModelProperty(value = "菜单列表", name = "menuIdList", required = true)
    private List<Integer> menuIdList;
}
