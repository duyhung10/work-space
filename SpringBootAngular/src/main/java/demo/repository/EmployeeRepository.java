package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
