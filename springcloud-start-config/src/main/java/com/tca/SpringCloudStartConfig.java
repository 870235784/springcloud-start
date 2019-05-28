package com.tca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer //开启配置中心
public class SpringCloudStartConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudStartConfig.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStartConfig.class, args);
		LOGGER.info("springcloud-start-config starts...");
	}
}
