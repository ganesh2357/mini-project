package com.bg;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AmusementParkApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmusementParkApplication.class, args);
		System.out.println("Application Run Successfully!   u did it");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

}
