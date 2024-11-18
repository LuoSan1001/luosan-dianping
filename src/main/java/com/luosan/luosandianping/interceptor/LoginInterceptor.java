package com.luosan.luosandianping.interceptor;

import com.luosan.luosandianping.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        try {
            JwtUtils.parse(token);
        } catch (Exception e) {
            response.getWriter().write("{message: 'NOT_LOGIN'}");
            return false;
        }

        return true;
    }
}
