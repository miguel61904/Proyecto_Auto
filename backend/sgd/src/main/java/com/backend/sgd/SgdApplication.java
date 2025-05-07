package com.backend.sgd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SgdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgdApplication.class, args);
        System.out.println("Sistema de gestion de la demanda del talento");
	}

}
