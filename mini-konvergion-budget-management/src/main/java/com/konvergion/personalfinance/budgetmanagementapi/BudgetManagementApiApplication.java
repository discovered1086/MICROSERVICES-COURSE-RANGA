package com.konvergion.personalfinance.budgetmanagementapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BudgetManagementApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetManagementApiApplication.class, args);
    }

}
