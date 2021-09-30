package com.br.dad.config.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.br.dad.entities.Customer;

@Configuration
public class StepCustomer {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Value("${params.chunk}")
	private Integer param;
	
	public Step stepCustomer(
			ItemReader<Customer> customerItemReader, 
			ItemProcessor<Customer, Customer> customerItemProcessor, 
			ItemWriter<Customer> customerItemWriter) {
		
		return stepBuilderFactory
				.get("stepCustomer")
				.<Customer, Customer>chunk(param)
				.reader(customerItemReader)
				.processor(customerItemProcessor)
				.writer(customerItemWriter)
				.build();
	}
}
