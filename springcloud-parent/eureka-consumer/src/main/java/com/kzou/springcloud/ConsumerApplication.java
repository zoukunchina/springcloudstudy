package com.kzou.springcloud;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import myrule.MyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="KZOU-SERVICE-PROVIDER",configuration = MyRule.class)
@Configurable
public class ConsumerApplication {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;


    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }


    @GetMapping("/hi")
    public String sayHi(){
        String  hi = restTemplate.getForObject("http://KZOU-SERVICE-PROVIDER/hi", String.class);
        return hi;
    }

    @GetMapping("/getPort")
    public String getTodayFinishOrderNum() {
        RestTemplate restTemplate = new RestTemplate();;

        List<ServiceInstance> instances = discoveryClient.getInstances("KZOU-SERVICE-PROVIDER");
        List<String>  dc = discoveryClient.getServices();

        // 获取第一个服务信息
        ServiceInstance instanceInfo = instances.get(0);
        //获取ip
        String ip = instanceInfo.getHost();
        //获取port
        int port = instanceInfo.getPort();
        String url  ="http://"+ip+":"+port+"/hi";
        String  i = restTemplate.getForObject(url, String.class);
        System.out.println("返回的结果为："+i.toString());
        return i.toString();
    }

}
