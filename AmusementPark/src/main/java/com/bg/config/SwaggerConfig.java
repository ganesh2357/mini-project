package com.bg.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@Configuration
//@EnableSwagger2
//@SuppressWarnings({ "deprecation" })
//public class SwaggerConfig extends WebMvcConfigurerAdapter {
//
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
//				.securityContexts(Arrays.asList(securityContext()))
//				.securitySchemes(Arrays.asList(apiKey())).select()
//				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
//	}
//
//	private SecurityContext securityContext() {
//		return SecurityContext.builder().securityReferences(defaultAuth()).build();
//	}
//
//	private List<SecurityReference> defaultAuth() {
//		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//		authorizationScopes[0] = authorizationScope;
//		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfo("Amusament Park", "Created by @Ganesh", "", "",
//				new Contact("Ganesh", "", ""), "", "",
//				Collections.emptyList());
//	}
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("swagger-ui.html").
//		addResourceLocations("classpath:/META-INF/resources/");
//
//		registry.addResourceHandler("/webjars/**").
//		addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}
//
//	private ApiKey apiKey() {
//		return new ApiKey("JWT", "Authorization", "header");
//	}
//}

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket empApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.bg.controller"))

				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Developed by Ganesh").contact(new Contact("Ganesh", "", "")).version("1.0")
				.description("Amusement_Park").build();
	}
}
