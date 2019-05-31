import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Location } from '@angular/common';
import { CourseService } from '../services/course.service';
import { Course } from '../models/Course';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit, OnDestroy {
  public subscription: Subscription;
  public courses: {};
  public dataSource;
  public displayedColumns: string[] = ['index', 'name', 'fee', 'startDate', 'endDate', 'action'];

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    public courseService: CourseService,
    public location: Location
    ) { }

  ngOnInit() {
    this.subscription = this.courseService.getAllCourse().subscribe((data: Course[]) => {
      // this.courses = data;
      // console.log(this.courses);
      this.dataSource = new MatTableDataSource<Course>(data);
      this.dataSource.paginator = this.paginator;
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
