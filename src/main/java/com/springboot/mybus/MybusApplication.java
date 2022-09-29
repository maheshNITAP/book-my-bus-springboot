package com.springboot.mybus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MybusApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybusApplication.class, args);
	}

}
