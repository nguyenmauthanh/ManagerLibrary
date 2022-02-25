package com.elcom.managerlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ManagerLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerLibraryApplication.class, args);
	}

}
