package com.springboot.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.bisoft.configuration")
@ComponentScan("com.bisoft.dao")
@ComponentScan("com.bisoft.service")
@ComponentScan("com.bisoft.controller")
public class DemoBisofyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoBisofyApplication.class, args);
	}
}