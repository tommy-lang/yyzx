package com.htu.yyzx.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htu.yyzx.common.*;
import com.htu.yyzx.mapper.MenuMapper;
import com.htu.yyzx.mapper.RoleMenuMapper;
import com.htu.yyzx.mapper.UserMapper;
import com.htu.yyzx.model.dto.user.UserLoginRequest;
import com.htu.yyzx.model.dto.user.UserQueryRequest;
import com.htu.yyzx.model.entity.Menu;
import com.htu.yyzx.model.entity.User;
import com.htu.yyzx.model.enums.UserRoleStatus;
import com.htu.yyzx.model.vo.ListUserVO;
import com.htu.yyzx.model.vo.MenuVO;
import com.htu.yyzx.model.vo.UserVO;
import com.htu.yyzx.service.UserService;
import com.htu.yyzx.utils.MaskUtils;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final RoleMenuMapper roleMenuMapper;
    private final MenuMapper menuMapper;

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @return
     */
    @Override
    public BaseResponse<UserVO> userLogin(UserLoginRequest userLoginRequest) {
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();
        // 1. 查询当前登录用户信息
        User user = lambdaQuery().eq(User::getUsername, username).one();
        if (user != null) {
            // 检查密码是否输入正确
            if (user.getPassword().equals(password)) {
                // 登录成功
                StpUtil.login(user.getId(), true);
                // SaTokenServlet
                // 2. 获取当前用户的角色 ID
                int roleId = user.getRoleId();
                // 3. 通过角色 ID，查找对应的菜单 ID
                List<Integer> menuIds = roleMenuMapper.queryMenuIdsByRoleId(roleId);
                // 4. 通过菜单 ID，查询菜单的信息
                List<Menu> menus = menuMapper.queryMenusByMenuIds(menuIds);
                // 5. 查询每个菜单的子菜单
                List<MenuVO> menuVOs = BeanUtil.copyToList(menus, MenuVO.class);
                for (MenuVO menuVO : menuVOs) {
                    // 6. 查询当前菜单的子菜单
                    List<Menu> children = menuMapper.queryByParentId(menuVO.getId());
                    menuVO.setMenuList(children);
                }
                UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
                userVO.setMenuVOList(menuVOs);
                // 8. 创建口令
                JwtBuilder builder = Jwts.builder();
                String token = builder
                        .setSubject(userVO.getUsername()) // 主体
                        .setIssuedAt(new Date()) // 签发时间: 设置 token 的生成时间
                        .setExpiration(
                                new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)
                        ) // 过期时间
                        .setId(userVO.getId().toString()) // 设置token id
                        .signWith(SignatureAlgorithm.HS256, "neuedu123") // 设置加密方式与密码
                        .compact();
                // 9. 将 token 设置给 userVO
                userVO.setToken(token);
                return R.success(userVO, "登录成功！");
            }
        }
        return R.error(ErrorCode.PARAMS_ERROR, "账号或者密码错误！");
    }

    /**
     * 获取全部管家用户
     *
     * @return
     */
    @Override
    public BaseResponse<List<String>> queryAllUser() {
        List<String> list = lambdaQuery()
                .select(User::getNickname)
                .eq(User::getRoleId, UserRoleStatus.BUTLER.getCode())
                .list()
                .stream().map(User::getNickname)
                .toList();
        return R.success(list);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public BaseResponse<String> addUser(User user) {
        int insert = baseMapper.insert(user);
        return R.success(null, "添加成功");
    }

    /**
     * （批量或者单个）删除用户
     *
     * @param user
     * @return
     */
    @Override
    public BaseResponse<String> deleteUser(DeleteRequestBody user) {
        if (user.getIds().size() > 1) {
            baseMapper.deleteById(user.getIds().get(0));
        } else {
            baseMapper.deleteBatchIds(user.getIds());
        }
        return R.success(null, "删除成功");
    }

    /**
     * 更新用户信息
     *
     * @param updateUserRequest
     * @return
     */
    @Override
    public BaseResponse<String> updateUser(User updateUserRequest) {
        updateUserRequest.setPassword(null);
        baseMapper.updateById(updateUserRequest);
        return R.success(null, "更新成功");
    }

    /**
     * 分页查询用户
     *
     * @param userQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<ListUserVO>> queryUser(UserQueryRequest userQueryRequest) {
        String nickname = userQueryRequest.getNickname();
        String username = userQueryRequest.getUsername();
        Integer sex = userQueryRequest.getSex();
        Date startTime = userQueryRequest.getStartTime();
        Date endTime = userQueryRequest.getEndTime();
        String email = userQueryRequest.getEmail();
        Integer roleId = userQueryRequest.getRoleId();
        String phoneNumber = userQueryRequest.getPhoneNumber();
        int current = userQueryRequest.getCurrent();
        int pageSize = userQueryRequest.getPageSize();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();

        LambdaQueryWrapper<User> queryUserWrapper = new LambdaQueryWrapper<User>()
                .like(StrUtil.isNotBlank(nickname), User::getNickname, nickname)
                .like(StrUtil.isNotBlank(username), User::getUsername, username)
                .like(StrUtil.isNotBlank(email), User::getEmail, email)
                .like(StrUtil.isNotBlank(phoneNumber), User::getPhoneNumber, phoneNumber)
                .eq(sex != null, User::getSex, sex)
                .eq(roleId != null, User::getRoleId, roleId)
                .between(startTime != null && endTime != null, User::getCreateTime, startTime, endTime);
        Page<User> page = page(new Page<>(current, pageSize), queryUserWrapper);
        Page<ListUserVO> listUserVOPage = new Page<>(current, pageSize, page.getTotal());
        listUserVOPage.setRecords(page.getRecords().stream().map(user -> {
            ListUserVO listUserVO = BeanUtil.copyProperties(user, ListUserVO.class);
            MaskRule rule = new MaskRule();
            rule.setType(0);
            rule.setScope(1);
            rule.setCount(3);
            listUserVO.setPassword(MaskUtils.commonMask(user.getPassword(), rule));
            // listUserVO.setPassword(MaskUtils.mobilePhone(user.getPassword()));
            return listUserVO;
        }).toList());
        return R.success(listUserVOPage, "查询成功");
    }

    /**
     * 分页查询健康管家
     *
     * @param healthAdminQueryRequest
     * @return
     */
    @Override
    public BaseResponse<Page<User>> queryHealthAdmin(UserQueryRequest healthAdminQueryRequest) {
        String nickname = healthAdminQueryRequest.getNickname();
        String username = healthAdminQueryRequest.getUsername();
        Integer sex = healthAdminQueryRequest.getSex();
        String phoneNumber = healthAdminQueryRequest.getPhoneNumber();
        int current = healthAdminQueryRequest.getCurrent();
        int pageSize = healthAdminQueryRequest.getPageSize();
        String sortField = healthAdminQueryRequest.getSortField();
        String sortOrder = healthAdminQueryRequest.getSortOrder();

        Page<User> page = page(new Page<>(current, pageSize), new LambdaQueryWrapper<User>()
                .eq(User::getRoleId, UserRoleStatus.BUTLER.getCode())
                .like(StrUtil.isNotBlank(nickname), User::getNickname, nickname)
                .like(StrUtil.isNotBlank(username), User::getUsername, username)
                .like(StrUtil.isNotBlank(phoneNumber), User::getPhoneNumber, phoneNumber)
                .eq(sex != null, User::getSex, sex)
        );
        return R.success(page, "查询成功");
    }


}




