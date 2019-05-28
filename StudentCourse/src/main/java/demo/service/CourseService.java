package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Course;
import demo.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	public List<Course> getAll() {
		return courseRepository.findAll();
	}
	
	public Course findById(int id) {
		return courseRepository.findById(id).orElse(null);
	}
	
	public Course create(Course course) {
		return courseRepository.save(course);
	}
	
	public Course update(Course course) {
		return courseRepository.save(course);
	}
	
	public void delete(int id) {
		courseRepository.deleteById(id);
	}
}
