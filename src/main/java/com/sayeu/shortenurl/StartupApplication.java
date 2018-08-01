package com.sayeu.shortenurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class StartupApplication {

    public static void main(String[] args) {
		SpringApplication.run(StartupApplication.class, args);
	}
}
