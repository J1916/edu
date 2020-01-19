package com.guli.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer //开启服务注册中心服务端
@SpringBootApplication
public class GuliCommonserviceEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuliCommonserviceEurekaApplication.class, args);
    }

}
