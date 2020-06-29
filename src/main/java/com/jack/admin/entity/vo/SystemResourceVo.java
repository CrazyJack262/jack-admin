package com.jack.admin.entity.vo;

import com.jack.admin.entity.dao.SystemResource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author crazyjack262
 * @date 2020-06-29 10:19
 */
@Data
public class SystemResourceVo extends SystemResource {

    @NotNull(message = "菜单id不能为空")
    @ApiModelProperty(value = "资源名称", name = "resourceName", required = true)
    private Integer menuId;
}
