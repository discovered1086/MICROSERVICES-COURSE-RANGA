package com.kingshuk.springcloudprojects.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class RangaConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RangaConfigServerApplication.class, args);
	}

}
