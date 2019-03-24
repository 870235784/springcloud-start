package com.tca.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello springboot world";
	}

	@RequestMapping("/encoding")
	public String encoding() {
		return "你好";
	}
	
}
