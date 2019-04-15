package sbdatajpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import sbdatajpa.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	Employee findByEmpNo(String empNo);

	List<Employee> findByFullNameLike(String fullName);

	List<Employee> findByHireDateGreaterThan(Date hireDate);

	@Query("SELECT coalesce(max(e.id), 0) FROM Employee e")
	Long getMaxId();
}
