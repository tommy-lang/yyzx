package com.htu.yyzx.config;

import cn.dev33.satoken.stp.StpInterface;
import com.htu.yyzx.mapper.UserMapper;
import com.htu.yyzx.model.enums.UserRoleStatus;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component  // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UserMapper userMapper;

    /**
     * 返回一个账号所拥有的权限码集合
     *
     * @param o
     * @param s
     * @return
     */
    @Override
    public List<String> getPermissionList(Object o, String s) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        list.add("101");
        list.add("user.add");
        list.add("user.update");
        list.add("user.get");
        // list.add("user.delete");
        list.add("art.*");
        return list;
    }

    /**
     * 返回一个账号所拥有的角色表示集合（权限与角色可以分开校验）
     *
     * @param loginId
     * @param loginType
     * @return
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        // 根据登录id查询用户 id
        Integer roleId = userMapper.selectById(loginId.toString()).getRoleId();

        return roleId == UserRoleStatus.BUTLER.getCode() ? List.of("health-admin") : List.of("admin");
    }
}
