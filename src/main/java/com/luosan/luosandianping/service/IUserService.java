package com.luosan.luosandianping.service;

import com.luosan.luosandianping.dto.LoginFormDTO;
import jakarta.servlet.http.HttpSession;

public interface IUserService {
    String sendCode(String phone);

    String login(LoginFormDTO loginFormDTO);
}
