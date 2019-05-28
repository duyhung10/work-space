package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
