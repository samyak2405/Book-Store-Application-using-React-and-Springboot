package com.javahunter.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ECommAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommAppApplication.class, args);
	}

}
