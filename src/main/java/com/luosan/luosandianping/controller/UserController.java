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
    public String sendCode(@RequestParam String phone) {
        return userService.sendCode(phone);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginFormDTO loginFormDTO) {
        return userService.login(loginFormDTO);
    }

    @GetMapping("/nihao")
    public String nihao() {
        return "niaho";
    }
}
