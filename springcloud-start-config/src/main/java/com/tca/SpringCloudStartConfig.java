package com.tca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudStartConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudStartConfig.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStartConfig.class, args);
		LOGGER.info("springcloud-start-config starts...");
	}
}
