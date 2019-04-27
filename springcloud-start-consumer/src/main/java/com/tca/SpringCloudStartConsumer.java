package com.tca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class SpringCloudStartConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudStartConsumer.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStartConsumer.class, args);
		LOGGER.info("springcloud-start-consumer starts...");
	}
}
