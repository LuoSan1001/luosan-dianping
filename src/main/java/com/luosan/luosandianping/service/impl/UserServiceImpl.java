package com.luosan.luosandianping.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.luosan.luosandianping.dto.LoginFormDTO;
import com.luosan.luosandianping.service.IUserService;
import com.luosan.luosandianping.utils.JwtUtils;
import com.luosan.luosandianping.utils.RegexUtils;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public String sendCode(String phone, HttpSession session) {
        if (!RegexUtils.isPhone(phone)) {
            return "电话号码格式错误";
        }
        String code = RandomUtil.randomNumbers(6);
        session.setAttribute("code", code);

        return code;
    }

    @Override
    public String login(LoginFormDTO loginFormDTO, HttpSession session) {
       String memoCode = (String) session.getAttribute("code");

       if (memoCode.equals(loginFormDTO.getCode())) {
           HashMap<String, Object> map = new HashMap<>();
           map.put("phone", loginFormDTO.getPhone());
           map.put("code", loginFormDTO.getCode());

           return JwtUtils.generate(map);
       } else {
           return "fail";
       }
    }
}
