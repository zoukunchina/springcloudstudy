package com.kzou;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableEurekaClient
public class ProviderApplication {

    @Value("${server.port}")
    private Integer port;

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

    @GetMapping("/getPort")
    public Integer getTodayFinishOrderNum() {
        return port;
    }

    @GetMapping("/hi")
    public String sayHi(){
        return "hi im 7070";
    }

}
