package com.jack.admin.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author crazyjack262
 * @date 2020-06-30 11:49
 */
@Data
@ApiModel(value = "PC侧边栏元设置", description = "PC侧边栏元设置")
public class MenuMeta {

    @ApiModelProperty(value = "标题", name = "title", required = true)
    private String title;

    @ApiModelProperty(value = "图标", name = "icon", required = false)
    private String icon;

    @ApiModelProperty(value = "如果设置为true，则不会被 <keep-alive> 缓存(默认 false)", name = "noCache", required = false)
    private boolean noCache = true;

    @ApiModelProperty(value = "如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)", name = "breadcrumb", required = false)
    private boolean breadcrumb = true;


}
