import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Employee } from '../models/employee';
import { EmployeeService } from '../services/employee.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-employee-detail',
  templateUrl: './employee-detail.component.html',
  styleUrls: ['./employee-detail.component.css']
})
export class EmployeeDetailComponent implements OnInit {

  currentEmployee: Employee;
  isShowDefault = false;

  constructor(
    private router: Router,
    private location: Location,
    private employeeService: EmployeeService,
    private dialogRef: MatDialogRef<EmployeeDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
    ) {}
 
  ngOnInit() {
    this.currentEmployee = this.data.empl;
  }

  onNoClick(){
    this.dialogRef.close();
  }

  onSubmit(){
    console.log(this.currentEmployee);
    this.dialogRef.close();
  }

  onClickEdit(){
    this.isShowDefault = !this.isShowDefault;
  }
  onClickConfirm(){
    this.employeeService.updateEmployee(this.currentEmployee).subscribe(data => {
      alert('Chỉnh sửa thông tin thành công');
      window.location.reload();
    });
  }
}
