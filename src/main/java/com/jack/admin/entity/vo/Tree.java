package com.jack.admin.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.LinkedList;

/**
 * @author crazyjack262
 * @date 2020-06-22 16:14
 */
@ApiModel(value = "树状图封装对象", description = "树状图封装对象")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tree {

    @ApiModelProperty(value = "主键", required = true)
    private Integer id;

    @ApiModelProperty(value = "名称", required = true)
    private String label;

    @ApiModelProperty(value = "名称", required = true)
    private Integer version;

    @ApiModelProperty(value = "子节点", required = true)
    private LinkedList<Tree> children;

    /**
     * 新增子节点
     *
     * @param jsTree
     */
    public void addChildren(Tree jsTree) {
        if (null == this.children) {
            setChildren(new LinkedList<>());
        }
        children.addFirst(jsTree);
    }
}
