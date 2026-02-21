package com.example.SmartAgriConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SmartAgriConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartAgriConnectApplication.class, args);
	}

}
