package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Department;
import demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	DepartmentRepository departmentRepository;
	
	public List<Department> getAll() {
		return departmentRepository.findAll();
	}
	
	public Department findById(int id) {
		return departmentRepository.findById(id).orElse(null);
	}
}
