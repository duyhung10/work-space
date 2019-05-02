package com.service;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Customer;
import com.repository.CustomerRepository;
import com.service.ServiceResult.Status;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	EntityManager entityManager;
	
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
	
	public ServiceResult createBasicInfor(String name) {
		Customer customer = new Customer();
		customer.setName(name);
		
		Status status = Status.SUCCESS;
		String message = "Create new Customer with Customer with basic infor Success ";
		Customer c = customerRepository.save(customer);
		List<Customer> data = new LinkedList<>();
		data.add(c);
		
		ServiceResult serviceResult = new ServiceResult(status, message, data);
		return serviceResult;
	}
	
	@Transactional( propagation = Propagation.REQUIRED)
	public ServiceResult saveAttachInfor(int id, String address) throws Exception {
		final String DEFAULT  = "Default";
		
		if(address.equals(DEFAULT)) {
			Status status = Status.FAILED;
			String message = "Create Failed";
			
			ServiceResult serviceResult = new ServiceResult(status, message, null);
			this.demoException();
			return serviceResult;
			
 		} else {
			Customer customer = customerRepository.findById(id).orElse(null);
			customer.setAddress(address);
			
			Status status = Status.SUCCESS;
			String message = "Create new Customer with Customer with full infor Success ";
			Customer c = customerRepository.save(customer);
			List<Customer> data = new LinkedList<>();
			data.add(c);
			
			ServiceResult serviceResult = new ServiceResult(status, message, data);
			return serviceResult;
		}
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public ServiceResult createTwoSteps(Customer customer) throws Exception {
		
		ServiceResult serviceResult2 = new ServiceResult();
		
		ServiceResult serviceResult1 = createBasicInfor(customer.getName());
		int id = serviceResult1.getData().get(0).getId();
		
		serviceResult2 = saveAttachInfor(id, customer.getAddress());
		
		return serviceResult2;
	}
	
	public void executeCustomQuery(String query) {
		Query q = entityManager.createNativeQuery(query);
		q.executeUpdate();
	}
	
	public void demoException() throws Exception {
		throw new Exception("Demo throw exception!!");
	}
}
