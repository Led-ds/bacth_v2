package com.br.dad.config.steps.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.br.dad.entities.Customer;

public class CustomerItemProcessorConfig implements ItemProcessor<Customer, Customer>, StepExecutionListener{

	private static final Logger logger = LoggerFactory.getLogger(CustomerItemProcessorConfig.class);
	
	@Value("${integration.api.url}")
	private String url;
	
	@Value("${integration.api.uri}")
	private String uri;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private StringBuilder endpoint;
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		logger.info("Initing processor...");
		endpoint = new StringBuilder();
		endpoint.append(url).append(uri);
	}

	@Override
	public Customer process(Customer item) throws Exception {
		// TODO Auto-generated method stub
		/*
			Podemos ter uma API disponível para integrar com outros serviços
			e trabalhar com seu retorno;
		*/
		ResponseEntity<Customer> response = restTemplate.postForEntity(endpoint.toString(), item, Customer.class);
		item.setAny(response.getBody().getAny());
		
		
		return item;
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		logger.info("End processor...");
		return ExitStatus.COMPLETED;
	}

}
