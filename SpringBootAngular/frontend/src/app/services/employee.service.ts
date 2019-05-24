import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Employee } from '../models/employee';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  public API: string = "http://localhost:8080/api/employees";

  constructor(private http: HttpClient) { }

  getAllEmployee(){
    return this.http.get(this.API);
  }

  addNewEmployee(employee: Employee){
    return this.http.post(this.API, employee);
  }

  deleteEmployee(id: number){
    return this.http.delete(`${this.API}/${id}`);
  }
  
  updateEmployee(employee: Employee) {
    // return this.http.put(`${this.API}/${employee.id}`, employee);
    return this.http.put(this.API, employee);
  }

  searchEmployees(term: string): Observable<Employee[]> {
    if (!term.trim()) {
      // if not search term, return empty employee array.
      return of([]);
    }

    return this.http.get<Employee[]>(`${this.API}/?name=${term}`)
  }
  
}
