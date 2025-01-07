package com.htu.yyzx.model.dto.user;

import com.htu.yyzx.constant.PasswordSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequest {
    /**
     * 系统账号
     */
    @NotBlank(message = "账号不为空")
    private String username;

    /**
     * 密码
     */
    @Size(
            min = PasswordSize.PASSWORD_MIN_SIZE, max = PasswordSize.PASSWORD_MAX_SIZE,
            message = "密码长度为"+PasswordSize.PASSWORD_MIN_SIZE+"-"+PasswordSize.PASSWORD_MAX_SIZE+"位"
    )
    private String password;

}
