package demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Enrolment;
import demo.entity.Student;
import demo.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	public List<Student> getAll() {
		return studentRepository.findAll();
	}
	
	public Student findById(int id) {
		return studentRepository.findById(id).orElse(null);
	}
	
	public Student create(Student student) {
		return studentRepository.save(student);
	}
	
	public Student update(Student student) {
		return studentRepository.save(student);
	}
	
	public void delete(int id) {
		studentRepository.deleteById(id);
	}
	
	
	// Tìm tất cả các khóa học(thông tin chi tiết) của sinh  viên 
	public List findAllCourseOfStudent(int id) {
		String query = "SELECT temp.student_id, c.* FROM " + 
				"(SELECT * FROM student_course.enrolment where student_id = " + id + ") as temp " + 
				"LEFT JOIN student_course.course as c ON temp.course_id = c.course_id ";
		
		Query q = entityManager.createNativeQuery(query);
		
		return q.getResultList();
	}
	
}
