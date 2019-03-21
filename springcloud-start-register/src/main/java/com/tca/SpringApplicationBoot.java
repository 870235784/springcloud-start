package com.tca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringApplicationBoot {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringApplicationBoot.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringApplicationBoot.class, args);
		LOGGER.info("springcloud-start-register starts...");
	}
}
