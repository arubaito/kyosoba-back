package com.kyosoba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 参考 https://rainbow-engine.com/java-spring-boot-hello-world/
@RestController
public class HelloWorldController {
	
	Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	
	@RequestMapping("/")
	public String HelloWorld() {
		logger.error("OK!!");
		return "Hello World";
	}

}
