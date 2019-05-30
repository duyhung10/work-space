package demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Course;
import demo.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	private EntityManager entityManager;
	
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
	
	// Tìm tất cả các sinh viên(thông tin chi tiết) đăng kí khóa học này
	public List findAllStudentOfCourse(int id) {
		String query = "SELECT temp.course_id, s.* "
				+ "FROM (SELECT * FROM student_course.enrolment where course_id=" + id + ") as temp "
				+ "LEFT JOIN student_course.student as s ON temp.student_id = s.student_id";
		
		Query q = entityManager.createNativeQuery(query);
		
		return q.getResultList();
	}
}
