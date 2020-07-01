package com.jack.admin.entity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author crazyjack262
 * @date 2020-06-24 13:57
 */
@Data
@TableName("t_system_menu")
@ApiModel(value = "菜单对象", description = "菜单对象")
public class SystemMenu {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = " del,modify必传", name = "id", required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单名称", name = "menuName", required = true)
    private String menuName;

    @ApiModelProperty(value = "上级id", name = "menuUrl", required = true)
    private Integer parentId;

    @ApiModelProperty(value = "菜单路径", name = "menuUrl", required = false)
    private String menuUrl;

    @ApiModelProperty(value = "菜单排序", name = "menuOrder", required = false)
    private Integer menuOrder;

    @ApiModelProperty(value = "菜单图标", name = "menuIcon", required = true)
    private String menuIcon;

    @ApiModelProperty(value = "菜单层级", name = "level", required = true)
    private Integer level;

    @ApiModelProperty(hidden = true)
    private Integer showFlag;

    @ApiModelProperty(value = "菜单类别 0菜单 1 按钮")
    private Integer menuType;
}
