package com.tca.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "provider-01")
public interface IHelloWorldService {
	
	@RequestMapping(value = "/hello",method = RequestMethod.POST)
    String hello();
}
