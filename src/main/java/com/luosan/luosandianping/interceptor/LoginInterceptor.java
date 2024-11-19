package com.luosan.luosandianping.interceptor;

import com.luosan.luosandianping.dto.UserDTO;
import com.luosan.luosandianping.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDTO userDTO = UserHolder.getUser();
        if (userDTO == null) {
            response.setStatus(401);
            return false;
        } else {
            return true;
        }
    }
}
