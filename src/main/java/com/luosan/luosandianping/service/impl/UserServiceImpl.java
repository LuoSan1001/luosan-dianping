package com.luosan.luosandianping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import com.luosan.luosandianping.dto.LoginFormDTO;
import com.luosan.luosandianping.dto.UserDTO;
import com.luosan.luosandianping.entity.User;
import com.luosan.luosandianping.mapper.UserMapper;
import com.luosan.luosandianping.service.IUserService;
import com.luosan.luosandianping.utils.RegexUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.luosan.luosandianping.utils.RedisConstants.LOGIN_CODE_KEY;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserMapper userMapper;

    @Override
    public String sendCode(String phone) {
        if (!RegexUtils.isPhone(phone)) {
            return "电话号码格式错误";
        }
        String code = RandomUtil.randomNumbers(6);
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone, code, 2L, TimeUnit.MINUTES);

        return code;
    }

    @Override
    public String login(LoginFormDTO loginFormDTO) {
        String phone = loginFormDTO.getPhone();
        String code = loginFormDTO.getCode();

        if (!RegexUtils.isPhone(phone)) {
            return "电话号码格式有误";
        }

        String memoCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        if (code.equals(memoCode)) {
            User user = userMapper.queryByPhone(phone);
            if (user == null) {
                user = createUserByPhone(phone);
            }
            String token = UUID.randomUUID().toString();
            UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
            Map<String, Object> userMapper = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                    CopyOptions.create()
                            .setIgnoreNullValue(true)
                            .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
            String tokenKey = "user:token:" + token;
            stringRedisTemplate.opsForHash().putAll(tokenKey, userMapper);
            stringRedisTemplate.expire(tokenKey, 1, TimeUnit.HOURS);
            return token;
        } else {
            return "验证码错误";
        }
    }

    private User createUserByPhone(String phone) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("default");
        user.setPassword("123456");
        user.setPhone(phone);
        user.setAvatarUrl("none");
        userMapper.createUser(user);
        return user;
    }
}
