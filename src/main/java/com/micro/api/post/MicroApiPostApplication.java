package com.micro.api.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MicroApiPostApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroApiPostApplication.class, args);
	}
}
