package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.Department;
import demo.model.DepartmentDTO;
import demo.service.DepartmentService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;

	// Get All Department
	@GetMapping("/departments")
	public List<Department> findAllCustomer() {
		return departmentService.getAll();
	}

	// Get Department by Id
	@GetMapping("/departments/{id}")
	public Department findById(@PathVariable int id) {
		return departmentService.findById(id);
	}
	
	// Get All Department full info
	@GetMapping("/departments/full-info")
	public List<DepartmentDTO> getFullInfoDepartment() {
		return departmentService.getFullInfoDepartment();
	}
}
