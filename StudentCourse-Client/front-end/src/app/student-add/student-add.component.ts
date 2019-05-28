import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms'
import { Student } from '../models/Student';
import { Subscription } from 'rxjs';
import { Course } from '../models/Course';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { StudentService } from '../services/student.service';
import { CourseService } from '../services/course.service';

@Component({
  selector: 'app-student-add',
  templateUrl: './student-add.component.html',
  styleUrls: ['./student-add.component.css']
})
export class StudentAddComponent implements OnInit, OnDestroy {

  public subscription: Subscription;
  public student: Student;
  public courses: {};

  formAddUser: FormGroup;

  constructor(public _formBuilder: FormBuilder,
    public studentService: StudentService,
    public courseService: CourseService,
    public router: Router,
    public location: Location) { 
    this.student = new Student();
  }

  ngOnInit() {
    this.createForm();
    this.subscription = this.courseService.getAllCourse().subscribe(data => {
      this.courses = data;
      console.log(this.courses);
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
      email: ['', [Validators.required, Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')]],
    }); 
  }

  createNewEmployee(){
    this.student.name = this.formAddUser.controls['name'].value;
    this.student.email = this.formAddUser.controls['email'].value;
  }

  onFormReset(){
    this.formAddUser.reset();
  }

  onGoBack(){
    this.location.back();
  }

  onSubmitAdd(){

  }
}
