package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.Department;


public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
