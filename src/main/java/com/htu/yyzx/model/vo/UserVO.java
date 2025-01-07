package com.htu.yyzx.model.vo;

import com.htu.yyzx.model.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserVO extends User {
    /**
     * token
     */
    private String token;

    /**
     * 用户所拥有的菜单
     */
    private List<MenuVO> menuVOList;
}
