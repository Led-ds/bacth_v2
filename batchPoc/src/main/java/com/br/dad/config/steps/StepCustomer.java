package com.br.dad.config.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepCustomer {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Value("${params.chunk}")
	private Integer param;
	
	public Step stepCustomer() {
		
		return null;
	}
}
