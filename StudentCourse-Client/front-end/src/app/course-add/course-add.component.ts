import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms'
import { Subscription } from 'rxjs';
import { Course } from '../models/Course';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { CourseService } from '../services/course.service';

@Component({
  selector: 'app-course-add',
  templateUrl: './course-add.component.html',
  styleUrls: ['./course-add.component.css']
})
export class CourseAddComponent implements OnInit, OnDestroy {
  
  public subscription: Subscription;
  public course: Course;

  formAddCourse: FormGroup;

  constructor(public _formBuilder: FormBuilder,
    public courseService: CourseService,
    public router: Router,
    public location: Location) { 
    this.course = new Course();
  }
  ngOnInit() {
    this.createForm();
  }

  ngOnDestroy(): void {
    if(this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  createForm(){
    this.formAddCourse = this._formBuilder.group({
      name: ['', Validators.required],
      fee: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required]
    }); 
  }

  createNewCourse(){
    this.course.name = this.formAddCourse.controls['name'].value;
    this.course.fee = this.formAddCourse.controls['fee'].value;
    this.course.startDate = this.dateFormat(this.formAddCourse.controls['startDate'].value);
    this.course.endDate = this.dateFormat(this.formAddCourse.controls['endDate'].value);
  }

  dateFormat(date: Date){
    let dateString = date.toDateString();
    return dateString;
  }

  onGoBack(){
    this.location.back();
  }

  onSubmitAdd(){
    this.createNewCourse();
    this.subscription = this.courseService.addNewCourse(this.course).subscribe((data: Course) => {
      this.course = data;
      alert("Thêm khóa học thành công");
      this.router.navigateByUrl('/courses');
    });
  }
}
