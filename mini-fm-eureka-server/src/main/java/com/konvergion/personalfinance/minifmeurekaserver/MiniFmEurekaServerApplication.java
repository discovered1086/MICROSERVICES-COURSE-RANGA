package com.konvergion.personalfinance.minifmeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MiniFmEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniFmEurekaServerApplication.class, args);
    }

}
