package com.htu.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.htu.yyzx.model.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Integer> queryMenuIdsByRoleId(@Param("roleId") int roleId);
}




