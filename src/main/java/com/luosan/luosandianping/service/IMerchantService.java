package com.luosan.luosandianping.service;

import com.luosan.luosandianping.entity.Merchant;

import java.util.List;

public interface IMerchantService {
    Merchant getMerchant(Integer mId);
    Integer updateMerchant(Merchant merchant);
}
