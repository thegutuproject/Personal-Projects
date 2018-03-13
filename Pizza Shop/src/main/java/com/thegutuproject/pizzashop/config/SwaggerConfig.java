package com.thegutuproject.pizzashop.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket orderEntryApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("orderEntry-api")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.thegutuproject.pizzashop.resource"))
				.paths(regex("/orderentry.*"))
				.build()
				.ignoredParameterTypes(ApiIgnore.class)
				.enableUrlTemplating(true);
	}
	
	@Bean
	public Docket orderLogApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("orderLog-api")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.thegutuproject.pizzashop.resource"))
				.paths(regex("/orderlog.*"))
				.build()
				.ignoredParameterTypes(ApiIgnore.class)
				.enableUrlTemplating(true);
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Pizza Shop Order Manager Application")
				.description("This documents the API's built out for the coding challenge. Please Note: This isn't complete. Only simple API's have been built due to being out of the scope of the requirements.")
				.contact("Alexandru Gutu")
				.license("Apache License Version 2.0")
				.build();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/v2/api-docs.*")
						.allowedOrigins("http://editor.swagger.io");
			}
		};
	}
}
