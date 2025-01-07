package com.htu.yyzx.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.ErrorCode;
import com.htu.yyzx.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author
 * @from
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Sa-Token 未登录异常
    @ExceptionHandler(NotLoginException.class)
    public BaseResponse notLoginExceptionHandler(NotLoginException e) {
        // 打印堆栈， 以供调试
        e.printStackTrace();
        if (NotLoginException.NOT_TOKEN.equals(e.getType())) {
            return R.error(ErrorCode.UNUSED_TOKEN, "未能读取到有效 token");
        } else if (e.getType().equals(NotLoginException.INVALID_TOKEN)) {
            return R.error(ErrorCode.UNUSED_TOKEN, "token 无效, 请先登录！！");
        } else if (e.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            return R.error(ErrorCode.UNUSED_TOKEN, "token 已过期");
        } else if (e.getType().equals(NotLoginException.BE_REPLACED)) {
            return R.error(ErrorCode.UNUSED_TOKEN, "token 已被顶下线");
        } else if (e.getType().equals(NotLoginException.KICK_OUT)) {
            return R.error(ErrorCode.UNUSED_TOKEN, "token 已被踢下线");
        }
        // else if(e.getType().equals(NotLoginException.TO)) {
        //     message = "token 已被冻结";
        // }
        // else if(e.getType().equals(NotLoginException.NO_PREFIX)) {
        //     message = "未按照指定前缀提交 token";
        // }
        return R.error(ErrorCode.UNUSED_TOKEN, "当前会话未登录");
    }

    @ExceptionHandler(NotRoleException.class)
    public BaseResponse notRoleExceptionHandler(NotRoleException e) {
        // 打印堆栈， 以供调试
        e.printStackTrace();
        return R.error(ErrorCode.NO_AUTH_ERROR, "无权限，请升级权限后重试");
    }

    @ExceptionHandler(NotPermissionException.class)
    public BaseResponse NotPermissionExceptionHandler(NotPermissionException e) {
        // 打印堆栈， 以供调试
        e.printStackTrace();
        return R.error(ErrorCode.NO_AUTH_ERROR, "无权限，请升级权限后重试");
    }

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return R.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return R.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
