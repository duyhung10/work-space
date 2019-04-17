package com.service;

import java.util.LinkedList;
import java.util.List;

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
		List<Customer> data =  customerRepository.findAll();
		
		ServiceResult serviceResult = new ServiceResult(status, message, data);
		return serviceResult;
	}
	
	public ServiceResult findById(int id) {
		ServiceResult serviceResult = new ServiceResult();
		Customer customer = customerRepository.findById(id).orElse(null);
		
		if(customer != null) {
			List<Customer> data = new LinkedList<>();
			data.add(customer);
			
			serviceResult.setData(data);
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
		Customer c = customerRepository.save(customer);
		List<Customer> data = new LinkedList<>();
		data.add(c);
		
		ServiceResult serviceResult = new ServiceResult(status, message, data);
		return serviceResult;
	}
	
	public ServiceResult update(Customer customer) {
		
		
		ServiceResult serviceResult = new ServiceResult();
		
		if(customerRepository.findById(customer.getId()).isPresent()) {
			serviceResult.setStatus(Status.SUCCESS);
			serviceResult.setMessage("Update Customer Succces");
			
			Customer c = customerRepository.save(customer);
			List<Customer> data = new LinkedList<>();
			data.add(c);
			
			serviceResult.setData(data);
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
