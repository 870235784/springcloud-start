package com.tca.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloworldController.class);
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	@RequestMapping("/hello")
	public String hello() {
		logger.info("当前端口号为 : 9001");
		return "hello " + applicationName;
	}

	@RequestMapping("/encoding")
	public String encoding() {
		return "你好";
	}
	
}
