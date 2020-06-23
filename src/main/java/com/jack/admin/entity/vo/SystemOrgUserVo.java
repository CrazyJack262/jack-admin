package com.jack.admin.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author crazyjack262
 * @date 2020-06-23 15:58
 */
@Data
@ApiModel(value = "部门用户关系对象", description = "部门用户关系对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemOrgUserVo {

    @ApiModelProperty(value = "部门id", name = "loginName", required = true)
    private Integer orgId;

    @ApiModelProperty(value = "用户id", name = "loginName", required = true)
    private List<Integer> userList;
}
