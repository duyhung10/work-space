package demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.entity.Enrolment;
import demo.repository.EnrolmentRepository;

@Service
public class EnrolmentService {
	@Autowired
	EnrolmentRepository enrolmentRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	public List<Enrolment> getAll() {
		return enrolmentRepository.findAll();
	}
	
	public Enrolment findById(int id) {
		return enrolmentRepository.findById(id).orElse(null);
	}
	
	public Enrolment create(Enrolment enrolment) {
		return enrolmentRepository.save(enrolment);
	}
	
	public Enrolment update(Enrolment enrolment) {
		return enrolmentRepository.save(enrolment);
	}
	
	public void delete(int id) {
		enrolmentRepository.deleteById(id);
	}
	
	// Xóa dữ liệu có điều kiện
	@Transactional
	public int deleteEnrolment(Enrolment enrolment) {
		int studentId = enrolment.getStudent().getStudentId();
		int courseId = enrolment.getCourse().getCourseId();
		
		String query = "DELETE FROM student_course.enrolment WHERE student_id=" + studentId +" AND course_id=" + courseId +";";
		Query q = entityManager.createNativeQuery(query);
		
		q.executeUpdate();
		
		return  studentId;
	}

}
