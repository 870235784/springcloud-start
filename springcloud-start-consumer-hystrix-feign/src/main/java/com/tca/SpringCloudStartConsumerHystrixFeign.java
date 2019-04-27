package com.tca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SpringCloudStartConsumerHystrixFeign {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudStartConsumerHystrixFeign.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStartConsumerHystrixFeign.class, args);
		LOGGER.info("springcloud-start-consumer-hystrix-feign starts...");
	}
}
