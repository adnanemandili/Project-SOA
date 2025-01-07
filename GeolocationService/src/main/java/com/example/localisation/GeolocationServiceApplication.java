package com.example.localisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GeolocationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeolocationServiceApplication.class, args);
	}

}
