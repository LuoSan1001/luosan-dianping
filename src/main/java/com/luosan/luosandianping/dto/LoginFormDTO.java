package com.luosan.luosandianping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
// 后续增加信息后注意保护用户隐私信息，再实现一个UserDTO
public class LoginFormDTO {
    private String phone;
    private String code;
}
