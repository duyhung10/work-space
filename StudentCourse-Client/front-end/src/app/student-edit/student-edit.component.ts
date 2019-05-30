import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Subscription } from 'rxjs';
import { StudentService } from '../services/student.service';
import { Student } from '../models/Student';
import { Course } from '../models/Course';
import { Enrolment } from '../models/Enrolment';
@Component({
  selector: 'app-student-edit',
  templateUrl: './student-edit.component.html',
  styleUrls: ['./student-edit.component.css']
})
export class StudentEditComponent implements OnInit {
  public subscription: Subscription;
  public subscription2: Subscription;
  public student: Student;
  public coursesOfStudent: Course[];

  public listIdCourseSelectedTemp = new Set();
  public listIdCourseSelected = [];

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
    if(this.subscription2) {
      this.subscription2.unsubscribe();
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

  onEditStudent(){

    // Hủy đăng kí môn học
    for(let id of this.listIdCourseSelected){
      let course = new Course();
      course.courseId = id;
      let enrolment = new Enrolment();
      enrolment.student = this.student;
      enrolment.course = course;
      
      this.subscription2 = this.studentService.cancelRegister(enrolment).subscribe(data => {
        console.log(data);
      });
    }

    // Sửa thông tin cá nhân
    this.subscription = this.studentService.updateStudent(this.student).subscribe((data: Student) => {
      this.student = data;
      console.log(this.student);

      alert("Cập nhật thông tin sinh viên thành công");
      this.router.navigateByUrl('/students/' + this.student.studentId + "/detail");
    });
  }
}
