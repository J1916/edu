package com.guli.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @EnableDiscoveryClient和@EnableEurekaClient：都是能够让注册中心能够发现，扫描到该服务。
 * @EnableEurekaClient只适用于Eureka作为注册中心，@EnableDiscoveryClient 可以是其他注册中心
 */
@EnableEurekaClient //向服务注册中心注册服务，让注册中心能够发现，扫描到该服务
@ComponentScan(basePackages = {"com.guli.edu", "com.guli.common"})
@SpringBootApplication
public class GuliServiceEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuliServiceEduApplication.class, args);
    }

}
