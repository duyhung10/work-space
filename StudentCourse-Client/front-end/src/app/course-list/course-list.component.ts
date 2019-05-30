import { Component, OnInit, OnDestroy } from '@angular/core';

import { Subscription } from 'rxjs';
import { Location } from '@angular/common';
import { CourseService } from '../services/course.service';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit, OnDestroy {
  public subscription: Subscription;
  public courses: {};

  constructor(
    public courseService: CourseService,
    public location: Location
    ) { }

  ngOnInit() {
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

  onDeleteCourse(id: number){
    this.subscription = this.courseService.deleteCourse(id).subscribe(data => {
      alert("Xóa khóa học thành công");
      // Gọi lại OnInit để cập nhật lại danh sách Student,
      this.ngOnInit();
    });
  }
}
