package com.rms.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rms.utils.RMSConstanst;

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
	public Docket rmsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.rms.employee"))
				//.paths(()-> "/employee")
				//.paths("/employee.*")
				.build();
	}
	
	private ApiInfo metadata() {
		Contact farianContact = new Contact(RMSConstanst.SWAGGER_API_CONTACT_NAME, RMSConstanst.SWAGGER_API_CONTACT_URL, RMSConstanst.SWAGGER_API_CONTACT_EMAIL);
		
		ApiInfo apiInfo = new ApiInfo(RMSConstanst.SWAGGER_API_TITLE, RMSConstanst.SWAGGER_API_DESCRIPTION, 
				RMSConstanst.SWAGGER_API_VERSION, 
				RMSConstanst.SWAGGER_API_TERMOFSERVICE, farianContact , "", "" );
		
		return apiInfo;
		
	}
	
	
}
