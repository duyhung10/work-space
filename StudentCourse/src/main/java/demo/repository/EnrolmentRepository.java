package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.Enrolment;

public interface EnrolmentRepository extends JpaRepository<Enrolment, Integer>{

}
