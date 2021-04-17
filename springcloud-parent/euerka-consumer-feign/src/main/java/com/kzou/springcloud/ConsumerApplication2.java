package com.kzou.springcloud;

import com.kzou.springcloud.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
@EnableEurekaClient
@Configurable
@EnableFeignClients
public class ConsumerApplication2 {

    @Autowired
    private ProviderService providerService;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication2.class, args);
    }

    @GetMapping("/feignhi")
    public String hi(){
        System.out.println("开始执行feign方法。。。。");
        return this.providerService.hi();
    }


}
