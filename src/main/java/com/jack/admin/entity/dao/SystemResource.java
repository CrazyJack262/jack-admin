package com.jack.admin.entity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author crazyjack262
 * @date 2020-06-28 12:01
 */
@Data
@ApiModel(value = "资源对象", description = "资源对象SystemResource")
@TableName("t_system_resources")
public class SystemResource {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键", name = "resourceName", required = true)
    private Integer id;

    @ApiModelProperty(value = "资源名称", name = "resourceName", required = true)
    private String resourceName;

    @ApiModelProperty(value = "资源URI", name = "resourceUri", required = true)
    private String resourceUrl;

    @ApiModelProperty(value = "资源权限", name = "permission", required = false)
    private String permission;

    @ApiModelProperty(hidden = true)
    private Integer resourceType;

    @ApiModelProperty(hidden = true)
    private Date createTime;

}
