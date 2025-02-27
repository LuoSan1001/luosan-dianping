package com.luosan.luosandianping.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Merchant implements Serializable {
    private Integer mId;
    private String mName;
    private String mAddress;
    private String mPhone;
    private LocalDate mRegisterDate;
}
