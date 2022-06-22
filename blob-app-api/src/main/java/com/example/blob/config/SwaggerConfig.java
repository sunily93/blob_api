package com.example.blob.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER="Authorization";
	
	private ApiKey apiKeys()
	{
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	
	private List<SecurityContext> securityContext()
	{
		return Arrays.asList(SecurityContext.builder().securityReferences(sercurityRefrences()).build());
	}
	
	private List<SecurityReference> sercurityRefrences()
	{
		AuthorizationScope scopes=new AuthorizationScope("global", "access everythings");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scopes}));
	}
	
	@Bean
	public Docket api()
	{
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(securityContext())
				.securitySchemes(Arrays.asList(apiKeys()))
				.select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo getInfo() {
		
		return new ApiInfo("Blogging Application : Backend course", "This project use Springboot", "1.0", "Terms of service", new Contact("Sunil", "http://sunil.com", "sunily010@gmail.com"), "api license", "Licences",Collections.emptyList());
	}
	
}
