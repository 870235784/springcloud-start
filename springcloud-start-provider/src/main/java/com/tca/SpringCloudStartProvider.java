package com.tca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudStartProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudStartProvider.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStartProvider.class, args);
		LOGGER.info("springcloud-start-provider starts...");
	}
}
