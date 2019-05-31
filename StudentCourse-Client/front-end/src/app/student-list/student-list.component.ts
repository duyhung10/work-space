import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Location } from '@angular/common';
import { StudentService } from '../services/student.service';
import { Student } from '../models/Student';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit, OnDestroy {

  public subscription: Subscription;
  // public students: {};
  public dataSource;
  public displayedColumns: string[] = ['index', 'name', 'email', 'action'];

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    public studentService: StudentService,
    public location: Location
    ) { }

  ngOnInit() {
    this.subscription = this.studentService.getAllStudent().subscribe((data: Student[]) => {
      // this.students = data;
      // console.log(this.students);
      this.dataSource = new MatTableDataSource<Student>(data);
      this.dataSource.paginator = this.paginator;
    });
  }

  ngOnDestroy(): void {
    if(this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  onDeleteStudent(id: number){
    this.subscription = this.studentService.deleteStudent(id).subscribe(data => {
      alert("Xóa sinh viên thành công");
      // Gọi lại OnInit để cập nhật lại danh sách Student,
      this.ngOnInit();
    });
  }
}
