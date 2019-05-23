package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Employee;
import demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}
	
	public Employee findById(int id) {
		return employeeRepository.findById(id).orElse(null);
	}
	
	public Employee create(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee update(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public void delete(int id) {
		employeeRepository.deleteById(id);
	}
}
