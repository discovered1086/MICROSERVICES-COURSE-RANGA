package com.kingshuk.springcloudprojects.limits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
//@EnableEurekaClient
public class RangaLimitsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RangaLimitsServiceApplication.class, args);
	}

}
