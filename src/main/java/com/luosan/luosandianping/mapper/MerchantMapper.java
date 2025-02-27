package com.luosan.luosandianping.mapper;

import com.luosan.luosandianping.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MerchantMapper {
    Merchant getMerchant(Integer mId);

    Integer updateMerchant(Merchant merchant);
}
