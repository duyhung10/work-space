import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms'
import { Student } from '../models/Student';
import { Subscription } from 'rxjs';
import { Course } from '../models/Course';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { StudentService } from '../services/student.service';
import { CourseService } from '../services/course.service';
import { Enrolment } from '../models/Enrolment';

@Component({
  selector: 'app-student-add',
  templateUrl: './student-add.component.html',
  styleUrls: ['./student-add.component.css']
})
export class StudentAddComponent implements OnInit, OnDestroy {

  public subscription: Subscription;
  public subscription2: Subscription;
  public student: Student;
  public courses: {};
  public listIdCourseSelectedTemp = new Set();
  public listIdCourseSelected = [];

  formAddStudent: FormGroup;

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
    if(this.subscription2) {
      this.subscription.unsubscribe();
    }
  }

  createForm(){
    this.formAddStudent = this._formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')]],
    }); 
  }

  createNewStudent(){
    this.student.name = this.formAddStudent.controls['name'].value;
    this.student.email = this.formAddStudent.controls['email'].value;
  }

  onGoBack(){
    this.location.back();
  }

  onSubmitAdd(){
    this.createNewStudent();
    this.subscription = this.studentService.addNewStudent(this.student).subscribe((data: Student) => {
      this.student = data;

      console.log(this.student.studentId);

      for(let id of this.listIdCourseSelected){
        let course = new Course();
        course.courseId = id;
        let enrolment = new Enrolment();
        enrolment.student = this.student;
        enrolment.course = course;

        this.subscription2 = this.studentService.registerCourseForStudent(enrolment).subscribe((data: Student) =>{
          console.log(data);
        });
      }

      alert("Thêm sinh viên thành công");
      this.router.navigateByUrl('/students');
    });
  }

  onClickCheckBox(id: number){
    if(this.listIdCourseSelectedTemp.has(id)){
      this.listIdCourseSelectedTemp.delete(id);
    } else {
      this.listIdCourseSelectedTemp.add(id);
    }

    console.log(this.listIdCourseSelectedTemp);

    this.listIdCourseSelected = Array.from(this.listIdCourseSelectedTemp.values());
    console.log(this.listIdCourseSelected);
  }
}
