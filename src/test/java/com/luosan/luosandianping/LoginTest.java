package com.luosan.luosandianping;

import com.luosan.luosandianping.service.IUserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class LoginTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void getCode() {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        String code = opsForValue.get("login:code15939340828");
        System.out.println("Code: " + code);
    }
}
