package com.luosan.luosandianping.controller;

import com.luosan.luosandianping.entity.Merchant;
import com.luosan.luosandianping.service.IMerchantService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lsdp/merchant")
public class MerchantController {
    @Resource
    private IMerchantService merchantService;

    @GetMapping
    public Merchant getMerchant(@RequestParam(required = false) Integer mId) {
        return merchantService.getMerchant(mId);
    }

    @PutMapping
    public Integer updateMerchant(@RequestBody Merchant merchant) {
        return merchantService.updateMerchant(merchant);
    }
}
