package com.br.dad.config.steps.writter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.br.dad.entities.Customer;

public class CustomerItemWriteConfig implements ItemWriter<Customer>, StepExecutionListener {

	private static final Logger logger = LoggerFactory.getLogger(CustomerItemWriteConfig.class);
	
	@Value("${params.query.update}")
	private String updateQuery;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void write(List<? extends Customer> items) throws Exception {
		try {
			update(items);
			
		} catch (Exception e) {
			items.stream().forEach(it -> logger.warn(it.toString()));
		}
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		logger.info("Initing writer...");		
	}
	
	private int[] update(List<? extends Customer> items) {
		
		return jdbcTemplate.batchUpdate(updateQuery, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, items.get(i).getHashCode());
				ps.setString(2, items.get(i).getAny());
			}
			
			@Override
			public int getBatchSize() {
				return items.size();
			}
		});
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		logger.info("End writer.");
		return ExitStatus.COMPLETED;
	}

}
