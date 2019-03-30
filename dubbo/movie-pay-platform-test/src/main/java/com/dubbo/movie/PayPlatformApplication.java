package com.dubbo.movie;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration

public class PayPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayPlatformApplication.class, args);
    }

}
