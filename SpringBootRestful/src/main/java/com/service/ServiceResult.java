package com.service;

import java.util.List;

import com.entity.Customer;

public class ServiceResult {
	private Status status = null;
	private String message;
	private List<Customer> data;
	
	public enum Status {
		SUCCESS, FAILED;
	}

	
	
	public ServiceResult() {
	}

	public ServiceResult(Status status, String message, List<Customer> data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Customer> getData() {
		return data;
	}

	public void setData(List<Customer> data) {
		this.data = data;
	}
}
