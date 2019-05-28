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
import demo.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
	@Autowired
	StudentService studentService;

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
	
	
	@GetMapping("/{id}/courses")
	public List<Enrolment> findAllCourseOfStudent(@PathVariable int id){
		return studentService.findAllCourseOfStudent(id);
	}
	
	@GetMapping("/{id}/register")
	public void registerCourseForStudent(@RequestBody List<Enrolment> listEnrolment, @PathVariable int id) {
		studentService.registerCourseForStudent(listEnrolment, id);
	}
	
	@GetMapping("/insert-into-enrolment")
	public void insertDataIntoEnrolment(@RequestBody List<Enrolment> listEnrolment) {
		studentService.insertDataIntoEnrolment(listEnrolment);
	}
	
}
