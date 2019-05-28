package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.Student;
import demo.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;
	
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
	
}
