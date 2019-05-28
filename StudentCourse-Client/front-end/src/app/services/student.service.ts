import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Student } from '../models/Student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  public API: string = "http://localhost:8080/api/students";

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
  
  updateEmployee(student: Student) {
    return this.http.put(`${this.API}/${student.id}`, student);
  }
  
  deleteStudent(id: number){
    return this.http.delete(`${this.API}/${id}`);
  }
}
