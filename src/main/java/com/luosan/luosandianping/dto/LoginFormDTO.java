package com.luosan.luosandianping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginFormDTO {
    private String phone;
    private String code;
}
