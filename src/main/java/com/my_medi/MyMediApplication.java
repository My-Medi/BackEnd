package com.my_medi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.my_medi.infra.discord.client")
public class MyMediApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMediApplication.class, args);
	}

}
