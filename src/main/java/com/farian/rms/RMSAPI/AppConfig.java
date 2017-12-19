package com.farian.rms.RMSAPI;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.farian.rms.RMSAPI.utils.GeneralQuery;



@Configuration
public class AppConfig {
	
	
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	
}
