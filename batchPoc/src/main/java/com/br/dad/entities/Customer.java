package com.br.dad.entities;

public class Customer {

	private Long id;
	private String fullName;
	private String hashCode;
	private String any;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getHashCode() {
		return hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
	public String getAny() {
		return any;
	}
	public void setAny(String any) {
		this.any = any;
	}
}
