import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { Location } from '@angular/common';
import { Employee } from '../models/employee';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../services/employee.service';
import { Department } from '../models/department';
import { DepartmentService } from '../services/department.service';

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.css']
})
export class EmployeeEditComponent implements OnInit, OnDestroy {

  public subscription: Subscription;
  public employee: Employee;
  public departments: Department[];


  constructor(
    private router: Router,
    private location: Location,
    private employeeService: EmployeeService,
    public departmentService: DepartmentService,
    private activatedRouteService: ActivatedRoute
    ) {}
 
  ngOnInit() {
    this.employee = new Employee;
    this.employee.department = new Department;
    this.loadData();

    this.subscription = this.departmentService.getAllDepartment().subscribe((data: Department[]) => {
      this.departments = data;
    });
  }
  ngOnDestroy(): void {
    if(this.subscription) {
      this.subscription.unsubscribe();
    }
  }
  loadData() {
    // Lấy id của employee được click 'more' (detail)
    const id = +this.activatedRouteService.snapshot.paramMap.get('id');
    // Lấy thông tin của employee được click 'more'
    this.subscription = this.employeeService.getEmployee(id).subscribe((data: Employee)=> {
      this.employee = data;
      console.log(this.employee);
    });
  }


  onSubmitEdit(){
    console.log(this.employee);
  }
 
  dateFormat(date: Date){
    let dateString = date.toDateString();
    return dateString;
  }

  onBack(){
    this.location.back();
  }

  
}
