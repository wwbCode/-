package com.jinyafu.thirdpart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ThirdPartServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThirdPartServerApplication.class, args);
    }
}
