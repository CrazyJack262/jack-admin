package com.jack.admin.entity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * t_system_org
 * @author 
 */
@Data
@TableName("t_system_org")
@ApiModel(value = "组织架构对象", description = "组织架构对象SystemOrg")
public class SystemOrg {

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = " del,modify必传", name = "id", required = true)
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 组织架构名称
	 */
	@ApiModelProperty(value = " 组织架构名称", name = "orgName", required = true)
	private String orgName;

	/**
	 * 上级组织ID
	 */
	@ApiModelProperty(value = "上级组织ID", name = "orgPid", required = true)
	private Integer orgPid;

	/**
	 * 组织架构全编码
	 */
	@ApiModelProperty(hidden = true)
	private String orgAllCode;

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


	@ApiModelProperty(value = "修改时必传", name = "version", required = true)
	@Version
	private Integer version;

}