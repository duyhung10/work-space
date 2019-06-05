package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.Enrolment;
import demo.service.EnrolmentService;

@RestController
@RequestMapping("/api/enrolments")
@CrossOrigin(origins = "http://localhost:4200")
public class EnrolmentController {
	@Autowired
	EnrolmentService enrolmentService;
	
	// Get All Student
	@GetMapping
	public List<Enrolment> findAllEnrolment() {
		return enrolmentService.getAll();
	}
	@GetMapping("/{id}")
	public Enrolment findById(@PathVariable int id) {
		return enrolmentService.findById(id);
	}

}
