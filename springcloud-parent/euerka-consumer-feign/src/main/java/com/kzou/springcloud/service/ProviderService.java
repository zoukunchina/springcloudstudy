package com.kzou.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "KZOU-SERVICE-PROVIDER")
public interface ProviderService {

    @GetMapping("/hi")
    public String hi();

    @GetMapping("/getPort")
    public String getTodayFinishOrderNum();
}
