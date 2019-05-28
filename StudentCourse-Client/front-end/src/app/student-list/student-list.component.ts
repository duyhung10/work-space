import { Component, OnInit, OnDestroy } from '@angular/core';

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
  public students: {};

  constructor(
    public studentService: StudentService,
    public location: Location
    ) { }

  ngOnInit() {
    this.subscription = this.studentService.getAllStudent().subscribe(data => {
      this.students = data;
      console.log(this.students);
    });
  }

  ngOnDestroy(): void {
    if(this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
