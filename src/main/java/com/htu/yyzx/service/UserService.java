package com.htu.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.DeleteRequestBody;
import com.htu.yyzx.model.dto.user.UserLoginRequest;
import com.htu.yyzx.model.dto.user.UserQueryRequest;
import com.htu.yyzx.model.dto.user.UserRegisterRequest;
import com.htu.yyzx.model.entity.User;
import com.htu.yyzx.model.vo.ListUserVO;
import com.htu.yyzx.model.vo.UserVO;

import java.util.List;

public interface UserService extends IService<User> {

    BaseResponse<UserVO> userLogin(UserLoginRequest userLoginRequest);


    BaseResponse<List<String>> queryAllUser();

    BaseResponse<String> addUser(User user);

    BaseResponse<String> deleteUser(DeleteRequestBody user);

    BaseResponse<String> updateUser(User updateUserRequest);


    BaseResponse<Page<ListUserVO>> queryUser(UserQueryRequest userQueryRequest);

    BaseResponse<Page<User>> queryHealthAdmin(UserQueryRequest healthAdminQueryRequest);
}
