import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from '../models/Course';
import { Enrolment } from '../models/Enrolment';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  public API: string = "http://localhost:8080/api/courses";

  constructor(private http: HttpClient) { }

  getAllCourse(){
    return this.http.get(this.API);
  }

  getCourse(id: number){
    return this.http.get(`${this.API}/${id}`);
  }

  addNewCourse(course: Course){
    return this.http.post(this.API, course);
  }
  
  updateCourse(course: Course) {
    return this.http.put(`${this.API}/${course.courseId}`, course);
  }
  
  deleteCourse(id: number){
    return this.http.delete(`${this.API}/${id}`);
  }

  //Lấy danh sách sinh viên đã đăng ký khóa học
  getStudentsInCourse(courseId: number) {
    return this.http.get(`${this.API}/${courseId}/students`);
  }

  // Xóa sinh viên khỏi khóa học hiện tại
  deleteStudentInCourse(enrolment: Enrolment){
    let courseId = enrolment.course.courseId;
    return this.http.put(`${this.API}/${courseId}/delete-student`, enrolment);
  }
}
