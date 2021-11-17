package com.api.fmc.config;

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
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.api.fmc"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());		
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Api Rest Family Medical Center")
				.description("Proyecto del curso Capstone Project")
				.contact(new Contact("Grupo 6", "UPN", "proyectofmc@upn.pe"))
				.license("Apache 2.0")
				.termsOfServiceUrl("https://github.com/JuniorCR21/proyecto_admin_back")
				.version("1.0")
				.build();
	}
}
