package com.tca.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tca.service.IHelloWorldService;

@RestController
public class HelloWorldController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Autowired
	private IHelloWorldService helloWorldService;
	
	@RequestMapping("/hello")
	public String hello() {
		LOGGER.info("开始调用provider的方法");
		return helloWorldService.hello();
	}
}
