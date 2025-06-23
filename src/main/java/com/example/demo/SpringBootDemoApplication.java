package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.example.demo")
@EntityScan("com.example.demo.model")
public class SpringBootDemoApplication extends SpringBootServletInitializer {
	
    public static void main(String[] args) {

        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
