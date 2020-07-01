package com.jack.admin.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.LinkedList;

/**
 * @author crazyjack262
 * @date 2020-06-30 11:43
 */
@Data
@ApiModel(value = "菜单PC-树形菜单", description = "菜单PC-树形菜单")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuTree {

    @ApiModelProperty(value = "菜单路径", name = "path", required = true)
    private String path;

    @ApiModelProperty(value = "菜单组件", name = "component", required = true)
    private String component;

    @ApiModelProperty(value = "重定向地址，在面包屑中点击会重定向去的地址/当设置 noRedirect 的时候该路由在面包屑导航中不可被点击", name = "redirect", required = true)
    private String redirect;

    @ApiModelProperty(value = " 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由", name = "meta", required = true)
    private boolean alwaysShow = false;

    @ApiModelProperty(value = "侧边栏元设置", name = "meta", required = true)
    private MenuMeta meta;

    @ApiModelProperty(value = "子节点", name = "children", required = true)
    private LinkedList<MenuTree> children;

    /**
     * 新增子节点
     *
     * @param jsTree
     */
    public void addChildren(MenuTree jsTree) {
        if (null == this.children) {
            setChildren(new LinkedList<>());
        }
        children.addFirst(jsTree);
    }

}
