package com.htu.yyzx.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.common.R;
import com.htu.yyzx.mapper.UserMapper;
import com.htu.yyzx.model.dto.user.UserLoginRequest;
import com.htu.yyzx.model.dto.user.UserQueryRequest;
import com.htu.yyzx.model.entity.User;
import com.htu.yyzx.model.vo.ListUserVO;
import com.htu.yyzx.model.vo.UserVO;
import com.htu.yyzx.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    // 测试
    @GetMapping("hello")
    public BaseResponse<String> hello() {
        User one = userMapper.selectById(1);
        return R.success("成功" + one);
    }

    /**
     * 登录
     *
     * @param userLoginRequest（封装了需要传递的数据）
     * @return
     */
    @PostMapping("login")
    public BaseResponse<UserVO> userLogin(@RequestBody @Validated UserLoginRequest userLoginRequest) {
        System.out.println(userLoginRequest.toString());
        return userService.userLogin(userLoginRequest);
    }
    /**
     * 增加
     *
     * @param user
     * @return
     */
    @SaCheckRole("admin")
    @PostMapping("addUser")
    public BaseResponse<String> addUser(@RequestBody @Validated User user) {

        return userService.addUser(user);
    }

    /**
     * 删除
     *
     * @param user
     * @return
     */
    @PostMapping("deleteUser")
    public BaseResponse<String> deleteUser(@RequestBody @Validated DeleteRequestBody user) {
        return userService.deleteUser(user);
    }

    /**
     * 更新用户信息
     *
     * @param updateUserRequest
     * @return
     */
    @PostMapping("updateUser")
    public BaseResponse<String> updateUser(@RequestBody @Validated User updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }

    /**
     * 查询用户信息
     *
     * @param userQueryRequest
     * @return
     */
    @PostMapping("queryUser")
    public BaseResponse<Page<ListUserVO>> queryUser(@RequestBody @Validated UserQueryRequest userQueryRequest) {
        return userService.queryUser(userQueryRequest);
    }

}
