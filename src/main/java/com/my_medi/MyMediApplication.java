package com.my_medi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MyMediApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMediApplication.class, args);
	}

}
