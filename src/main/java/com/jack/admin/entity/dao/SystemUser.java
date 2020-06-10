package com.jack.admin.entity.dao;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.jack.admin.entity.vo.SystemUserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 *
 * @author crazyjack262
 * @date 2020-06-05 10:47
 */
@JsonInclude(Include.NON_NULL)
@ApiModel(value = "用户对象", description = "用户对象SystemUser")
@Data
@TableName("t_system_user")
public class SystemUser implements Serializable {

    private static final long serialVersionUID = -1649080014488800810L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录名称
     */
    @ApiModelProperty(value = "登录名", name = "loginName", required = true)
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "登录密码", name = "loginPasswd", required = true)
    private String loginPassword;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户名", name = "userName", required = true)
    private String userName;

    /**
     * 盐值
     */
    @ApiModelProperty(hidden = true)
    private String salt;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址", name = "avatarIcon", required = true)
    private String avatarIcon;

    /**
     * 用户状态 0 正常 1 异常
     */
    @ApiModelProperty(hidden = true)
    private Integer userStatus;

    /**
     * 用户电话
     */
    @ApiModelProperty(value = "用户电话", name = "userPhone", required = false)
    private String userPhone;

    /**
     * 最后登录
     */
    @ApiModelProperty(hidden = true)
    private Date loginTime;

    /**
     * 用户备注
     */
    @ApiModelProperty(value = "用户备注", name = "remark", required = false)
    private String remark;

    /**
     * 用户登录失败次数
     */
    @ApiModelProperty(hidden = true)
    private Short failCount;

    /**
     * 创建者id
     */
    @ApiModelProperty(value = "创建者id", required = true)
    private Integer createUserId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = true)
    @TableField(update = "now()", updateStrategy = FieldStrategy.IGNORED)
    private Date updateTime;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号", required = true)
    @Version
    private Integer version;

    /**
     * 逻辑删除位 0 正常 1 删除
     */
    @ApiModelProperty(value = "逻辑删除位 0 正常 1 删除", required = true)
    private Integer delFlag;

    public SystemUserVo toVo() {
        SystemUserVo vo = new SystemUserVo();
        vo.setId(this.id);
        vo.setLoginName(this.loginName);
        vo.setLoginPassword(this.loginPassword);
        vo.setUserName(this.userName);
        vo.setUserStatus(this.userStatus);
        vo.setUserPhone(this.userPhone);
        vo.setLoginTime(this.loginTime);
        vo.setRemark(this.remark);
        vo.setCreateTime(this.createTime);
        vo.setUpdateTime(this.updateTime);
        vo.setVersion(this.version);
        vo.setDelFlag(this.delFlag);
        return vo;
    }


}