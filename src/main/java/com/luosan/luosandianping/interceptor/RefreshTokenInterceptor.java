package com.luosan.luosandianping.interceptor;

import cn.hutool.core.bean.BeanUtil;
import com.luosan.luosandianping.dto.UserDTO;
import com.luosan.luosandianping.utils.UserHolder;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.luosan.luosandianping.utils.RedisConstants.USER_TOKEN_KEY;

@Component
public class RefreshTokenInterceptor implements HandlerInterceptor {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (token == null) {
            return true;
        }

        Map<Object, Object> userMapper =  stringRedisTemplate.opsForHash().entries(USER_TOKEN_KEY + token);
        if (userMapper.isEmpty()) {
            return true;
        }

        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMapper, new UserDTO(), false);

        UserHolder.saveUser(userDTO);
        stringRedisTemplate.expire(USER_TOKEN_KEY + token, 10, TimeUnit.DAYS);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
