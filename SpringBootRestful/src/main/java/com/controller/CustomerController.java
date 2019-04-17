package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Customer;
import com.service.CustomerService;
import com.service.ServiceResult;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	// Get All Customer
	@GetMapping("/customers")
	public ServiceResult findAllCustomer() {
		return customerService.findAll();
	}

	// Get Customer by Id
	@GetMapping("/customers/{id}")
	public ServiceResult findById(@PathVariable int id) {
		return customerService.findById(id);
	}

	// Create new customer
	@PostMapping("/customers")
	public ServiceResult create(@RequestBody Customer customer) {
		return customerService.create(customer);
	}

	// Update a customer
	@PutMapping("/customers")
	public ServiceResult update(@RequestBody Customer customer) {
		return customerService.update(customer);
	}
	
	@DeleteMapping("/customers/{id}")
	public ServiceResult detele(@PathVariable int id) {
		return customerService.delete(id);
	}
	
	
	
}
