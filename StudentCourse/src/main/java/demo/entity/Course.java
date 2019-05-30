package demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	
	private String name;
	
	private int fee;
	
	private String startDate;
	
	private String endDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "course", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Enrolment> enrolments;

	
	public Course() {
    }

    public Course(String name, int fee) {
        this.name = name;
        this.fee = fee;
        enrolments = new HashSet<>();
    }
	
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Set<Enrolment> getEnrolments() {
		return enrolments;
	}

	public void setEnrolments(Set<Enrolment> enrolments) {
		this.enrolments = enrolments;
	}

}
