package com.jack.admin.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jack.admin.entity.dao.SystemUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author crazyjack262
 * @date 2020-07-02 14:47
 */
@Data
@ApiModel(value = "用户VO对象", description = "用户VO对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SystemUserVo {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Integer id;

    /**
     * 登录名称
     */
    @ApiModelProperty(value = "登录名", name = "loginName", required = true)
    private String loginName;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址", name = "avatarIcon", required = true)
    private String avatarIcon;

    /**
     * 密码
     */
    @ApiModelProperty(value = "登录密码", name = "loginPassword", required = true)
    private String loginPassword;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户名", name = "userName", required = true)
    private String userName;

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
     * 登录凭证
     */
    @ApiModelProperty(value = "token", name = "登录凭证", required = false)
    private String jwtToken;

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

    public SystemUser toDao() {
        SystemUser dao = new SystemUser();
        dao.setId(this.id);
        dao.setLoginName(this.loginName);
        dao.setLoginPassword(this.loginPassword);
        dao.setUserName(this.userName);
        dao.setSalt(this.loginName);
        dao.setUserStatus(this.userStatus);
        dao.setUserPhone(this.userPhone);
        dao.setLoginTime(this.loginTime);
        dao.setRemark(this.remark);
        dao.setCreateUserId(this.id);
        dao.setCreateTime(this.createTime);
        dao.setUpdateTime(this.updateTime);
        dao.setVersion(this.version);
        dao.setDelFlag(this.delFlag);
        return dao;
    }
}
