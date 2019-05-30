import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Subscription } from 'rxjs';
import { Student } from '../models/Student';
import { Course } from '../models/Course';
import { CourseService } from '../services/course.service';

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css']
})
export class CourseDetailComponent implements OnInit, OnDestroy {

  public subscription: Subscription;
  public course: Course;
  public studentsInCourse: Student[];

  constructor(private router: Router,
    private location: Location,
    private courseService: CourseService,
    private activatedRouteService: ActivatedRoute
    ) { }


    ngOnInit() {
      this.course = new Course();
      this.loadData();
    }
  
    ngOnDestroy(): void {
      if(this.subscription) {
        this.subscription.unsubscribe();
      }
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

}
