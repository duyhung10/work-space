import { Component, OnInit, OnDestroy } from '@angular/core';
import { Employee } from '../models/employee';
import { EmployeeService } from '../services/employee.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Subscription } from 'rxjs';
import { Department } from '../models/department';

@Component({
  selector: 'app-employee-detail',
  templateUrl: './employee-detail.component.html',
  styleUrls: ['./employee-detail.component.css']
})
export class EmployeeDetailComponent implements OnInit, OnDestroy {

  public subscription: Subscription;
  public employee: Employee;
  
  constructor(
    private router: Router,
    private location: Location,
    private employeeService: EmployeeService,
    private activatedRouteService: ActivatedRoute
    ) {}
 
  ngOnInit() {
    this.employee = new Employee;
    this.employee.department = new Department;
    this.loadData();
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

  onBack(){
    this.location.back();
  }
}
