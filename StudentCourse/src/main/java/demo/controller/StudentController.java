package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.Enrolment;
import demo.entity.Student;
import demo.service.EnrolmentService;
import demo.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
	@Autowired
	StudentService studentService;

	@Autowired
	EnrolmentService enrolmentService;
	
	// Get All Student
	@GetMapping
	public List<Student> findAllStudent() {
		return studentService.getAll();
	}

	// Get Student by Id
	@GetMapping("/{id}")
	public Student findById(@PathVariable int id) {
		return studentService.findById(id);
	}

	// Create new Student
	@PostMapping
	public Student create(@RequestBody Student student) {
		return studentService.create(student);
	}

	// Update Student
	@PutMapping("/{id}")
	public Student update(@RequestBody Student student, @PathVariable int id) {
		return studentService.update(student);
	}

	@DeleteMapping("/{id}")
	public void detele(@PathVariable int id) {
		studentService.delete(id);
	}
	
	// Tìm các khóa học của sinh viên
	@GetMapping("/{id}/courses")
	public List findAllCourseOfStudent(@PathVariable int id){
		return studentService.findAllCourseOfStudent(id);
	}
	
	// Thêm dữ liệu vào bảng enrolment
	@PostMapping("/{id}/register")
	public Enrolment registerCourseForStudent(@RequestBody Enrolment enrolment) {
		return enrolmentService.create(enrolment);
	}
	
	// Xóa dữ liệu trong bảng enrolment có điều kiệu
	@PutMapping("/{id}/cancel-register")
	public int deteleRegister(@RequestBody Enrolment enrolment) {
		return enrolmentService.deleteEnrolment(enrolment);
	}
}
