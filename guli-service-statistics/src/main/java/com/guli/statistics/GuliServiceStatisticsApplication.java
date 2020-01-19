package com.guli.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients //使用feign调用服务
@EnableEurekaClient //服务注册与发现
@ComponentScan(basePackages = {"com.guli.statistics","com.guli.common"})
@SpringBootApplication
public class GuliServiceStatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuliServiceStatisticsApplication.class, args);
    }

}
