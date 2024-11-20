package com.luosan.luosandianping.controller;

import com.luosan.luosandianping.dto.LoginFormDTO;
import com.luosan.luosandianping.service.IUserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lsdp/user")
public class UserController {

    @Resource
    IUserService userService;

    @PostMapping("/code")
    public String sendCode(@RequestParam String phone, HttpSession session) {
        return userService.sendCode(phone, session);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginFormDTO loginFormDTO, HttpSession session) {
        return userService.login(loginFormDTO, session);
    }

    @GetMapping("/nihao")
    public String nihao() {
        return "niaho";
    }
}
