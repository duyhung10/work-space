package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.Employee;
import demo.service.EmployeeService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	// Get All Customer
	@GetMapping("/employees")
	public List<Employee> findAllCustomer() {
		return employeeService.getAll();
	}

	// Get Customer by Id
	@GetMapping("/employees/{id}")
	public Employee findById(@PathVariable int id) {
		return employeeService.findById(id);
	}

	// Create new customer
	@PostMapping("/employees")
	public Employee create(@RequestBody Employee employee) {
		return employeeService.create(employee);
	}

	// Update a customer
	@PutMapping("/employees")
	public Employee update(@RequestBody Employee employee) {
		return employeeService.update(employee);
	}
	
	@DeleteMapping("/employees/{id}")
	public void detele(@PathVariable int id) {
		employeeService.delete(id);
	}
}
