package com.jack.admin.entity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author crazyjack262
 * @date 2020-06-23 14:26
 */
@Data
@TableName("t_system_org_user")
public class SystemOrgUser {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 组织架构ID，关联org表
     */
    private Integer orgId;

    /**
     * 用户ID，关联user表
     */
    private Integer userId;
}
