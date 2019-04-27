package com.tca.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tca.feign.HelloWorldFeign;
import com.tca.service.IHelloWorldService;

@Service
public class HelloWorldServiceImpl implements IHelloWorldService{
	
	@Autowired
	private HelloWorldFeign helloWorldFeign;

	@Override
	public String hello() {
		return helloWorldFeign.hello();
	}

	@Override
	public String encoding() {
		return helloWorldFeign.encoding();
	}

}
