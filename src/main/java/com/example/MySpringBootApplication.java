
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MySpringBootApplication {
	private static final String token = "FreshWinds";
	
	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args);
		System.out.println("mySecretKey is "+System.getProperty("mySecretKey"));
		
		if(token.equals(System.getProperty("mySecretKey")))
			System.out.println("valid secret");
		else
			System.out.println("invalid secret");
	}
}
