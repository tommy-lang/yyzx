package com.htu.yyzx.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CheckTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("----  拦截器执行: CheckTokenInterceptor  ----");
        String path = request.getRequestURL().toString();
        // 请求是登录/注册接口，直接放行
        if (path.contains("/user/login") || path.contains("/user/register")) {
            return true;
        }

        String token = request.getHeader("token");
        // 没有 token
        if (token == null || token.isEmpty()) {
            return false;
        }

        try {
            // 存在 token 进行校验
            JwtParser jwtParser = Jwts.parser();
            jwtParser.setSigningKey("neuedu123");
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            System.out.println("----  token 验证成功  ----");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("----  token 验证失败  ----");
            return false;

        }
    }
}
