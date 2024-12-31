package com.sky.QuickRide.quickRide.App;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuickRideAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(QuickRideAppApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello");
	}
}
