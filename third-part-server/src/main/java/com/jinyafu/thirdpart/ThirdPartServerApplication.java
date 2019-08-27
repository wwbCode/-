package com.jinyafu.thirdpart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class ThirdPartServerApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(ThirdPartServerApplication.class, args);
    }
    
    @Value("${ribbon.ReadTimeout}")
    private int ribbonReadTimeout;
    @Value("${ribbon.ConnectTimeout}")
    private int ribbonConnectionTimeout;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory =  new HttpComponentsClientHttpRequestFactory();
        // ribbon超时时间配置
        httpRequestFactory.setReadTimeout(ribbonReadTimeout);
        httpRequestFactory.setConnectTimeout(ribbonConnectionTimeout);
        return new RestTemplate(httpRequestFactory);
    }
}
