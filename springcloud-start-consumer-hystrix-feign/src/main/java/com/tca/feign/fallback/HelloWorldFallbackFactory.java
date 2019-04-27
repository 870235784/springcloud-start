package com.tca.feign.fallback;

import org.springframework.stereotype.Component;

import com.tca.feign.HelloWorldFeign;

import feign.hystrix.FallbackFactory;

@Component
public class HelloWorldFallbackFactory implements FallbackFactory<HelloWorldFeign>{

	@Override
	public HelloWorldFeign create(Throwable cause) {
		return new HelloWorldFeign() {
			
			@Override
			public String hello() {
				return "this is fallback hello";
			}
			
			@Override
			public String encoding() {
				return "this is fallback encoding";
			}
		};
	}

}
