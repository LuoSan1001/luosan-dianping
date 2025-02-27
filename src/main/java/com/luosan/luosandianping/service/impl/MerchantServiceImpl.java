package com.luosan.luosandianping.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.json.JSONUtil;
import com.luosan.luosandianping.entity.Merchant;
import com.luosan.luosandianping.mapper.MerchantMapper;
import com.luosan.luosandianping.service.IMerchantService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static com.luosan.luosandianping.utils.RedisConstants.*;

@Slf4j
@Service
public class MerchantServiceImpl implements IMerchantService {
    @Resource
    private MerchantMapper merchantMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Merchant getMerchant(Integer mId) {
        if (mId != null) {
            String merchantListStr = stringRedisTemplate.opsForValue().get(MERCHANT_ID_KEY + mId);
            if (merchantListStr != null) {
                log.info("访问redis");
                log.info("getMerchantListStr:{}", merchantListStr);
                return JSONUtil.toBean(merchantListStr, Merchant.class);
            }

            log.info("访问数据库");
            Merchant merchant = merchantMapper.getMerchant(mId);
            if (merchant != null) {
                stringRedisTemplate.opsForValue().set(MERCHANT_ID_KEY + mId, JSONUtil.toJsonStr(merchant), CACHE_SHOP_TTL, TimeUnit.MINUTES);
            } else {
                stringRedisTemplate.opsForValue().set(MERCHANT_ID_KEY + mId, "", LOCK_SHOP_TTL, TimeUnit.MINUTES);
            }
            return merchant;
        }
        return null;
    }

    @Override
    @Transactional
    public Integer updateMerchant(Merchant merchant) {
        int result = merchantMapper.updateMerchant(merchant);
        stringRedisTemplate.delete(MERCHANT_ID_KEY + merchant.getMId());
        return result;
    }

    private boolean tryLock(String key) {
        Boolean isLock = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", LOCK_SHOP_TTL, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(isLock);
    }

    private void unLock(String key) {
         stringRedisTemplate.delete(key);
    }
}
