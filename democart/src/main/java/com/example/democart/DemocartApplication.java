package com.example.democart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DemocartApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemocartApplication.class, args);
	}

}
