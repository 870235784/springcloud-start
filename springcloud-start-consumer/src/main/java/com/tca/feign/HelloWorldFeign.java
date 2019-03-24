package com.tca.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("client-provider")
public interface HelloWorldFeign {
	
	@RequestMapping("/hello")
	public String hello();

	@RequestMapping("/encoding")
	public String encoding();
	
}
