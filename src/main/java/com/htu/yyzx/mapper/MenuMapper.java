package com.htu.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.htu.yyzx.model.entity.Menu;

import java.util.List;
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据菜单的 Id，批量查询菜单的信息
     *
     * @param menuIds
     * @return
     */
    List<Menu> queryMenusByMenuIds(List<Integer> menuIds);

    List<Menu> queryByParentId(Integer id);
}




