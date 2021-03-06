package com.tca.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tca.service.IHelloWorldService;

@RestController
public class HelloWorldController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Autowired
	private IHelloWorldService helloWorldService;
	
	@HystrixCommand(fallbackMethod="hystrixMethod")
	@RequestMapping("/hello")
	public String hello() {
		LOGGER.info("开始调用provider的方法");
		LOGGER.info("当前端口号为:7001");
		return helloWorldService.hello();
	}
	
	@RequestMapping("/encoding")
	public String encoding() {
		LOGGER.info("开始调用provider的方法");
		LOGGER.info("当前端口号为:7001");
		return helloWorldService.encoding();
	}
	
	public String hystrixMethod() {
		return "hystrixMethod invoke";
	}
	
}
