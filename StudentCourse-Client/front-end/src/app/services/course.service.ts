import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Course } from '../models/Course';

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
    return this.http.put(`${this.API}/${course.id}`, course);
  }
  
  deleteCourse(id: number){
    return this.http.delete(`${this.API}/${id}`);
  }
}
