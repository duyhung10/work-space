package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Customer;
import com.repository.CustomerRepository;
import com.service.ServiceResult.Status;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	public ServiceResult findAll() {
		
		Status status = Status.SUCCESS;
		String message = "Get All Customer Success";
		Object data = customerRepository.findAll();
		
		ServiceResult serviceResult = new ServiceResult(status, message, data);
		return serviceResult;
	}
	
	public ServiceResult findById(int id) {
		ServiceResult serviceResult = new ServiceResult();
		Customer customer = customerRepository.findById(id).orElse(null);
		
		if(customer != null) {
			serviceResult.setData(customer);
			serviceResult.setStatus(Status.SUCCESS);
			serviceResult.setMessage("Get Current Customer Succces");
		} else {
			serviceResult.setStatus(Status.FAILED);
			serviceResult.setMessage("Customer Not Found");
		}
		return serviceResult;
	}
	
	public ServiceResult create(Customer customer) {
		
		Status status = Status.SUCCESS;
		String message = "Create new Customer Success";
		Object data = customerRepository.save(customer);
		
		ServiceResult serviceResult = new ServiceResult(status, message, data);
		return serviceResult;
	}
	
	public ServiceResult update(Customer customer) {
		
		
		ServiceResult serviceResult = new ServiceResult();
		
		if(customerRepository.findById(customer.getId()).isPresent()) {
			serviceResult.setStatus(Status.SUCCESS);
			serviceResult.setMessage("Update Customer Succces");
			serviceResult.setData(customerRepository.save(customer));
		} else {
			serviceResult.setStatus(Status.FAILED);
			serviceResult.setMessage("Customer Not Found");
		}
		
		return serviceResult;
	}
	
	public ServiceResult delete(int id) {
		ServiceResult serviceResult = new ServiceResult();
		
		if(customerRepository.findById(id).isPresent()) {
			customerRepository.deleteById(id);
			serviceResult.setStatus(Status.SUCCESS);
			serviceResult.setMessage("Delete Customer Succces");
		} else {
			serviceResult.setStatus(Status.FAILED);
			serviceResult.setMessage("Customer Not Found");
		}
		
		return serviceResult;
	}
}
