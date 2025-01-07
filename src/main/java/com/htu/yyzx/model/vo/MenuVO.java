package com.htu.yyzx.model.vo;

import com.htu.yyzx.model.entity.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuVO extends Menu {
    private List<Menu> menuList;
}
