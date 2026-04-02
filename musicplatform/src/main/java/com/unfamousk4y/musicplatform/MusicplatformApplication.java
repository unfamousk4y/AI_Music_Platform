package com.unfamousk4y.musicplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MusicplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicplatformApplication.class, args);
	}

}
