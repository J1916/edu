package com.guli.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"com.guli.usercenter","com.guli.common"})
@EnableEurekaClient
@SpringBootApplication
public class GuliServiceUsercenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuliServiceUsercenterApplication.class, args);
    }

}
