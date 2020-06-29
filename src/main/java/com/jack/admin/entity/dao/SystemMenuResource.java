package com.jack.admin.entity.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author crazyjack262
 * @date 2020-06-28 16:15
 */
@Data
@ApiModel(value = "资源菜单关系对象", description = "资源菜单关系对象")
@TableName("t_system_menu_res")
public class SystemMenuResource {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer menuId;

    private Integer resId;

}
