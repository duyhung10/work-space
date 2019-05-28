import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { EmployeeService } from '../services/employee.service';
import { Employee } from '../models/employee';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit, OnDestroy {
  
  public employeeList: Employee[];
  public subscription: Subscription;

  constructor(
    private router: Router,
    public employeeService: EmployeeService,
  ) { }
  
  ngOnInit() {
    this.subscription = this.employeeService.getAllEmployee().subscribe((data: Employee[]) => {
      this.employeeList = data;
      console.log(this.employeeList);
    });
  }
  
  ngOnDestroy(): void {
    if(this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  onDeleteEmployee(id: number){
    this.subscription = this.employeeService.deleteEmployee(id).subscribe(data => {
      // Gọi lại OnInit để cập nhật lại danh sách employee,
      this.ngOnInit();
    });
  }
}