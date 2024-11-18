package com.luosan.luosandianping.service;

import com.luosan.luosandianping.dto.LoginFormDTO;
import jakarta.servlet.http.HttpSession;

public interface IUserService {
    String sendCode(String phone, HttpSession session);

    String login(LoginFormDTO loginFormDTO, HttpSession session);
}
