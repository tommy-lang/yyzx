package com.htu.yyzx.exception;

import com.htu.yyzx.common.BaseResponse;
import com.htu.yyzx.common.ErrorCode;
import com.htu.yyzx.common.R;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @valiated 异常校验
 * 自定义捕获异常后的处理方式，返回给前端这些处理信息
 */
@ControllerAdvice
public class CustomException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        List<String> collect = allErrors
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();
        return R.error(ErrorCode.PARAMS_ERROR, collect.toString());
    }
}
