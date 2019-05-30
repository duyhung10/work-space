import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Subscription } from 'rxjs';
import { StudentService } from '../services/student.service';
import { Student } from '../models/Student';
import { Course } from '../models/Course';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit, OnDestroy {
  public subscription: Subscription;
  public student: Student;
  public coursesOfStudent: Course[];

  constructor(private router: Router,
    private location: Location,
    private studentService: StudentService,
    private activatedRouteService: ActivatedRoute
    ) { }

  ngOnInit() {
    this.student = new Student();
    this.loadData();
  }

  ngOnDestroy(): void {
    if(this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  loadData(){
    // Lấy id của student được click 'more' (detail)
    const id = +this.activatedRouteService.snapshot.paramMap.get('id');
    // Lấy thông tin của student được click 'more'
    this.subscription = this.studentService.getStudent(id).subscribe((data: Student)=> {
      this.student = data;
      console.log(this.student);
    });

    //Lấy các khóa học đã đăng ký của student
    this.subscription = this.studentService.getCourseRegisteredOfStudent(id).subscribe((data: Course[]) =>{
      this.coursesOfStudent = data;
      console.log(this.coursesOfStudent);
    });
  }

  onGoBack(){
    this.location.back();
  }
}
