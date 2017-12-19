package com.farian.rms.RMSAPI.documentation;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.farian.rms.RMSAPI.employee.Employee;
import com.farian.rms.RMSAPI.employee.employmenthistory.EmployementHistoryService;
import com.farian.rms.RMSAPI.utils.RMSConstanst;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket rmsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				//Provide all endpoint for employee data management",
				.tags(new Tag("Employee", ""))
				.tags(new Tag("Grade History", "")) //Provide all endpoint for history of employee's grade data management
				.tags(new Tag("Employment History", "")) //Provide all endpoint for history of employee's employment data management
				.tags(new Tag("Family Member", "")) // Provide all endpoint for history of employee's family data management
				.select()
				
				.apis(RequestHandlerSelectors.basePackage("com.farian.rms.RMSAPI.employee"))
				
				//.paths(()-> "/employee")
				// .paths("/employee.*")
				
				.build();
	}
	
	private ApiInfo metadata() {
		Contact farianContact = new Contact(RMSConstanst.SWAGGER_API_CONTACT_NAME, RMSConstanst.SWAGGER_API_CONTACT_URL, RMSConstanst.SWAGGER_API_CONTACT_EMAIL);
		
		ApiInfo apiInfo = new ApiInfo(RMSConstanst.SWAGGER_API_TITLE, RMSConstanst.SWAGGER_API_DESCRIPTION, 
				RMSConstanst.SWAGGER_API_VERSION, 
				RMSConstanst.SWAGGER_API_TERMOFSERVICE, farianContact , "", "" )
				;
		
		return apiInfo;
		
	}
	
	
	 
	
}
