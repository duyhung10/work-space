import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from '../models/Student';
import { Enrolment } from '../models/Enrolment';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  public API: string = "http://localhost:8080/api/students";

  list: number[];

  constructor(private http: HttpClient) { }

  getAllStudent(){
    return this.http.get(this.API);
  }

  getStudent(id: number){
    return this.http.get(`${this.API}/${id}`);
  }

  addNewStudent(student: Student){
    return this.http.post(this.API, student);
  }
  
  updateStudent(student: Student) {
    return this.http.put(`${this.API}/${student.studentId}`, student);
  }
  
  deleteStudent(id: number){
    return this.http.delete(`${this.API}/${id}`);
  }

  // Đăng kí khóa học cho sinh viên (thêm và DB)
  registerCourseForStudent(enrolment: Enrolment) {
    let studentId = enrolment.student.studentId;
    return this.http.post(`${this.API}/${studentId}/register`, enrolment);
  }

  //Lấy các khóa học đã đăng ký của student
  getCourseRegisteredOfStudent(studentId: number) {
    return this.http.get(`${this.API}/${studentId}/courses`);
  }

  // Hủy đăng kí học cho sinh viên
  cancelRegister(studentId: number, courseId: number){
    return this.http.delete(`${this.API}/${studentId}/cancel-register/${courseId}`);
  }
}
