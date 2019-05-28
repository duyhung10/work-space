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

import demo.entity.Student;
import demo.service.StudentService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
	@Autowired
	StudentService studentService;

	// Get All Student
	@GetMapping("/students")
	public List<Student> findAllStudent() {
		return studentService.getAll();
	}

	// Get Student by Id
	@GetMapping("/students/{id}")
	public Student findById(@PathVariable int id) {
		return studentService.findById(id);
	}

	// Create new Student
	@PostMapping("/students")
	public Student create(@RequestBody Student student) {
		return studentService.create(student);
	}

	// Update Student
	@PutMapping("/students/{id}")
	public Student update(@RequestBody Student student, @PathVariable int id) {
		return studentService.update(student);
	}

	@DeleteMapping("/students/{id}")
	public void detele(@PathVariable int id) {
		studentService.delete(id);
	}
}
