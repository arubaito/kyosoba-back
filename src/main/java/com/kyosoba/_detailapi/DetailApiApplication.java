package com.kyosoba._detailapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.kyosoba.controller.HelloWorldController;

@SpringBootApplication
@ComponentScan(basePackageClasses = HelloWorldController.class)
public class DetailApiApplication {

	// アプリケーションの実行
	public static void main(String[] args) {
		SpringApplication.run(DetailApiApplication.class, args);
	}

}
