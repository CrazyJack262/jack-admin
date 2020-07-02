package com.jack.admin.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author crazyjack262
 * @date 2020-07-02 14:47
 */
@Data
@ApiModel(value = "角色用户关系对象", description = "角色用户关系对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemRoleUserVo {
    @ApiModelProperty(value = "角色id", name = "roleId", required = true)
    @NotNull(message = "角色id不能为空")
    private Integer roleId;

    @ApiModelProperty(value = "用户id", name = "userList", required = true)
    private List<Integer> userList;
}
