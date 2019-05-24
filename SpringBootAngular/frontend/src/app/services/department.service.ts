import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  public API: string = "http://localhost:8080/api/departments";

  constructor(private http: HttpClient) { }

  getAllDepartment(){
    return this.http.get(this.API);
  }

  getAllDepartmentFullInfo(){
    return this.http.get(`${this.API}/full-info`);
  }
}
