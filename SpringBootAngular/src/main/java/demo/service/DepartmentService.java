package demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Department;
import demo.model.DepartmentDTO;
import demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	public List<Department> getAll() {
		return departmentRepository.findAll();
	}
	
	public Department findById(int id) {
		return departmentRepository.findById(id).orElse(null);
	}
	
	public List<DepartmentDTO> getFullInfoDepartment() {
		String query = "SELECT d.*, temp.amount \n" + 
				"FROM human_resources.department d\n" + 
				"left join\n" + 
				"(SELECT e.department_id, count(e.department_id) as amount\n" + 
				"FROM human_resources.employee e\n" + 
				"group by e.department_id) as temp\n" + 
				"on d.id = temp.department_id";
		
		Query q = entityManager.createNativeQuery(query);
        
		return q.getResultList();
	}
}
