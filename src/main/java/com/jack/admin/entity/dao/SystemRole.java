package com.jack.admin.entity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * t_system_role
 *
 * @author
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@TableName("t_system_role")
@ApiModel(value = "角色对象", description = "角色对象SystemRole")
public class SystemRole {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", name = "roleName", required = true)
    private String roleName;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述", name = "roleDesc", required = false)
    private String roleDesc;

    /**
     * 创建人ID
     */
    @ApiModelProperty(hidden = true)
    private Integer createUserId;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private Date createTime;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(hidden = true)
    private Date updateTime;

    @ApiModelProperty(hidden = true)
    private Integer delFlag;

    @ApiModelProperty(value = "版本号", name = "version", required = false)
    @Version
    private Integer version;


}