import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms'

import { Subscription } from 'rxjs';
import { Employee } from '../models/employee';
import { EmployeeService } from '../services/employee.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { Department } from '../models/department';
import { DepartmentService } from '../services/department.service';


var passwordTemp: string;

@Component({
  selector: 'app-add-user-form',
  templateUrl: './add-user-form.component.html',
  styleUrls: ['./add-user-form.component.css']
})
export class AddUserFormComponent implements OnInit, OnDestroy {
  
  public subscription: Subscription;

  public employee: Employee;
  public departmentTemp: Department;
  public departments: Department[];

  formAddUser: FormGroup;

  constructor(
    public _formBuilder: FormBuilder,
    public employeeService: EmployeeService,
    public departmentService: DepartmentService,
    public router: Router,
    public location: Location    
    ) {
    this.employee = new Employee();
    this.departmentTemp = new Department();
  }
  
  
  ngOnInit() {
    this.createForm();
    this.subscription = this.departmentService.getAllDepartment().subscribe((data: Department[]) => {
      this.departments = data;
    });
  }
  
  ngOnDestroy(): void {
    if(this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  createForm(){
    this.formAddUser = this._formBuilder.group({
      name: ['', Validators.required],
      birthday: ['', Validators.required],
      sex: [''],
      numberphone: ['', [Validators.required, Validators.pattern('[0-9]{9}')]],
      department: ['', Validators.required],
      username: ['', [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(20)
      ]],
      password: ['', [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(20),
      ]],
      confirmpassword: ['', [Validators.required, confirmpasswordValidator]],
      email: ['', Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')],
    }); 
  }


  onSubmitAdd(){
    this.createNewEmployee();
    this.subscription = this.employeeService.addNewEmployee(this.employee).subscribe(data => {
      this.router.navigate(['employees']);
      alert('Thêm nhân viên thành công');
    });
  }

  createNewEmployee(){
    this.employee.name = this.formAddUser.controls['name'].value;
    this.employee.birthday =  this.formAddUser.controls['birthday'].value ? this.dateFormat(this.formAddUser.controls['birthday'].value) : '';
    this.employee.sex = this.formAddUser.controls['sex'].value;
    this.employee.numberPhone =  `0${this.formAddUser.controls['numberphone'].value}`;
    this.employee.username = this.formAddUser.controls['username'].value;
    this.employee.password = this.formAddUser.controls['password'].value;
    this.employee.email = this.formAddUser.controls['email'].value;
    this.departmentTemp.id = this.formAddUser.controls['department'].value;
    this.employee.department = this.departmentTemp; 
  }

  dateFormat(date: Date){
    let dateString = date.toDateString();
    return dateString;
  }

  onFormReset(){
    this.formAddUser.reset();
  }

  onGoBack(){
    this.location.back();
  }

  setPasswordTemp(){
    passwordTemp = this.formAddUser.controls['password'].value;
  }

}

function confirmpasswordValidator(formControl: FormControl){
  if(formControl.value != passwordTemp){
    return{
      errorConfirmpassword: true
    }
  }
}
