package org.example.cicd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;


@SpringBootApplication
public class MySpringBootApplication {
	private static final String token = "FreshWinds";

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args);
		System.out.println("mySecretKey is " + System.getProperty("mySecretKey"));

		if (StringUtils.isEmpty(System.getProperty("mySecretKey")))
			System.out.println("secret is null");
		else if (token.equals(System.getProperty("mySecretKey")))
			System.out.println("valid secret");
		else
			System.out.println("invalid secret");
	}
}
