package com.tca;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy //开启zuul代理
@SpringBootApplication
public class SpringCloudZuul {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudZuul.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZuul.class, args);
		LOGGER.info("springcloud-start-zuul starts...");
	}
}
