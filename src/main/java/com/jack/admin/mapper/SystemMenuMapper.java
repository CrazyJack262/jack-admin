package com.jack.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jack.admin.entity.dao.SystemMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author crazyjack262
 * @date 2020-06-24 14:03
 */
public interface SystemMenuMapper extends BaseMapper<SystemMenu> {


    @Select("SELECT menu_url FROM t_system_menu where id in (SELECT menu_id FROM t_system_role_menu WHERE role_id = #{roleId})")
    List<String> selectByRoleId(@Param("roleId") Integer roleId);
}
