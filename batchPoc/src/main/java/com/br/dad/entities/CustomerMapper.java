package com.br.dad.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer>{

	private Customer customer;
	
	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		customer = new Customer();
		customer.setId(rs.getLong(0));
		customer.setFullName(rs.getString("COLUNM_NAME"));
		customer.setHashCode(rs.getString("COLUNM_HASH"));
		
		return customer;
	}

}
