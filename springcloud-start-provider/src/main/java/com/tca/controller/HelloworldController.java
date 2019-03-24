package com.tca.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {
	
	@Value("${spring.application.name}")
	private String applicationName;
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello " + applicationName;
	}

	@RequestMapping("/encoding")
	public String encoding() {
		return "你好";
	}
	
}
