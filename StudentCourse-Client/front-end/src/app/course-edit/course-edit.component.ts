import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms'
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Subscription } from 'rxjs';
import { Student } from '../models/Student';
import { Course } from '../models/Course';
import { CourseService } from '../services/course.service';
import { Enrolment } from '../models/Enrolment';

@Component({
  selector: 'app-course-edit',
  templateUrl: './course-edit.component.html',
  styleUrls: ['./course-edit.component.css']
})
export class CourseEditComponent implements OnInit, OnDestroy {

  public subscription: Subscription;
  public subscription2: Subscription;
  public course: Course;
  public studentsInCourse: Student[];

  public listIdStudentSelectedTemp = new Set();
  public listIdStudentSelected = []

  formCourse: FormGroup;
  constructor(
    public _formBuilder: FormBuilder,
    private router: Router,
    private location: Location,
    private courseService: CourseService,
    private activatedRouteService: ActivatedRoute
    ) { }


  ngOnInit() {
    this.createForm();
    this.course = new Course();
    this.loadData();
  }

  ngOnDestroy(): void {
    if(this.subscription) {
      this.subscription.unsubscribe();
    }
    if(this.subscription2) {
      this.subscription2.unsubscribe();
    }
  }

  createForm(){
    this.formCourse = this._formBuilder.group({
      startDate: [''],
      endDate: ['']
    }); 
  }

  loadData(){
    // Lấy id của course được click 'more' (detail)
    const id = +this.activatedRouteService.snapshot.paramMap.get('id');
    // Lấy thông tin của course được click 'more'
    this.subscription = this.courseService.getCourse(id).subscribe((data: Course)=> {
      this.course = data;
      console.log(this.course);
    });

    //Lấy danh sách sinh viên đã đăng ký khóa học
    this.subscription = this.courseService.getStudentsInCourse(id).subscribe((data: Student[]) =>{
      this.studentsInCourse = data;
      console.log(this.studentsInCourse);
    });
  }

  onGoBack(){
    this.location.back();
  }

  onClickCheckBox(id: number){
    if(this.listIdStudentSelectedTemp.has(id)){
      this.listIdStudentSelectedTemp.delete(id);
    } else {
      this.listIdStudentSelectedTemp.add(id);
    }

    console.log(this.listIdStudentSelectedTemp);

    this.listIdStudentSelected = Array.from(this.listIdStudentSelectedTemp.values());
    console.log(this.listIdStudentSelected);
  }

  editInfoCourse(){
    if(this.formCourse.controls['startDate'].value){
      this.course.startDate = this.dateFormat(this.formCourse.controls['startDate'].value);
    }
    if(this.formCourse.controls['endDate'].value){
      this.course.endDate = this.dateFormat(this.formCourse.controls['endDate'].value);
    }
  }

  onEditCourse(){
    this.editInfoCourse();
    
    // Xóa sinh viên khỏi khóa học
    for(let id of this.listIdStudentSelected){
      let courseId = this.course.courseId;
      let studentId = id;

      this.subscription2 = this.courseService.deleteStudentInCourse(courseId, studentId).subscribe(data => {
        console.log(data);
      });
    }

    // Sửa thông tin cá nhân
    this.subscription = this.courseService.updateCourse(this.course).subscribe((data: Course) => {
      this.course = data;
      console.log(this.course);

      alert("Cập nhật thông tin khóa học thành công");
      this.router.navigateByUrl('/courses/' + this.course.courseId + "/detail");
    });
  }

  dateFormat(date: Date){
    let dateString = date.toDateString();
    return dateString;
  }
}
