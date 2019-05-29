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
	public List<Enrolment> findAllCourseOfStudent(int id) {
		String query = "SELECT temp.*, c.name, c.fee FROM " + 
				"(SELECT * FROM student_course.enrolment where student_id = " + id + ") as temp " + 
				"LEFT JOIN student_course.course as c ON temp.course_id = c.course_id ";
		
		Query q = entityManager.createNativeQuery(query);
		
		return q.getResultList();
	}
	
	// Đăng ký các khóa học cho sinh viên, thông tin chi tiết lưu trong List<Enrolment>
	public void registerCourseForStudent(List<Enrolment> listEnrolment, int studentId) {
		for(Enrolment enrolment : listEnrolment) {
			String query = createQueryInsertEnrolment(studentId, enrolment.getCourse().getCourseId(), 
					enrolment.getStartDate(), enrolment.getEndDate());
			
			Query q = entityManager.createNativeQuery(query);
			q.executeUpdate();
		}
	}
	
	// Thêm dữ liệu vào bảng Enrolment - thêm các bản ghi trong mối quan hệ student - course (số lượng lớn)
	public void insertDataIntoEnrolment(List<Enrolment> listEnrolment) {
		for(Enrolment enrolment : listEnrolment) {
			String query = createQueryInsertEnrolment(enrolment.getStudent().getStudentId() , enrolment.getCourse().getCourseId(), 
					enrolment.getStartDate(), enrolment.getEndDate());
			
			Query q = entityManager.createNativeQuery(query);
			
			q.executeUpdate();
		}
	}
	
	
	public String createQueryInsertEnrolment(int studentId, int courseId, String startDate, String endDate) {
		String query = "INSERT INTO student_course.enrolment (student_id, course_id, start_date, end_date) "
				+ "VALUE ('" + studentId + "','" + courseId +"','" + startDate + "','" + endDate + "')";
		return query;
	}
	
}
