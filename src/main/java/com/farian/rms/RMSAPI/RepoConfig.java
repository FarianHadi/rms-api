package com.farian.rms.RMSAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import com.farian.rms.RMSAPI.utils.GeneralQuery;

@Controller
public class RepoConfig {

	@Bean 
	public GeneralQuery generalQuery() {
		return new GeneralQuery();
	}
}
