package com.tca.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tca.feign.fallback.HelloWorldFallbackFactory;

@FeignClient(value = "client-provider", fallbackFactory = HelloWorldFallbackFactory.class)
public interface HelloWorldFeign {
	
	@RequestMapping("/hello")
	public String hello();

	@RequestMapping("/encoding")
	public String encoding();
	
}
