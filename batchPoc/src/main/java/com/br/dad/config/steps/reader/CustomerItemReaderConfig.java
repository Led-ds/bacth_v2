package com.br.dad.config.steps.reader;

import javax.sql.DataSource;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.br.dad.entities.Customer;
import com.br.dad.entities.CustomerMapper;

public class CustomerItemReaderConfig implements StepExecutionListener{
	
	@Value("{params.page.size}")
	private Integer param;
	
	@Value("{params.query.customer.select}")
	private String select;
	
	@Value("{params.query.customer.from}")
	private String from;
	
	@Value("{params.query.customer.where}")
	private String where;
	
	@Value("{params.query.customer.sort}")
	private String sort;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Bean
	public JdbcPagingItemReader<Customer> jdbcPagingItemReader(DataSource dataSource, PagingQueryProvider queryProvider){
		return new JdbcPagingItemReaderBuilder<Customer>()
				   .name("jdbcPagingItemReader")
				   .dataSource(dataSource)
				   .queryProvider(queryProvider)
				   .pageSize(param)
				   .rowMapper(new CustomerMapper())
				   .build();
	}
	
	public SqlPagingQueryProviderFactoryBean queFactoryBean(DataSource dataSource) {
		SqlPagingQueryProviderFactoryBean sql = new SqlPagingQueryProviderFactoryBean();
		sql.setDataSource(dataSource);
		sql.setSelectClause(select.concat(""));
		sql.setFromClause(from.concat(""));
		sql.setWhereClause(where.concat(""));
		sql.setSortKey(sort);
		
		return sql;
		
	}

}
