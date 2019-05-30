import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { StudentComponent } from './student/student.component';
import { StudentListComponent } from './student-list/student-list.component';
import { StudentAddComponent } from './student-add/student-add.component';
import { StudentDetailComponent } from './student-detail/student-detail.component';
import { StudentEditComponent } from './student-edit/student-edit.component';
import { CourseComponent } from './course/course.component';
import { CourseListComponent } from './course-list/course-list.component';
import { CourseAddComponent } from './course-add/course-add.component';
import { CourseDetailComponent } from './course-detail/course-detail.component';
import { CourseEditComponent } from './course-edit/course-edit.component';

const routes: Routes = [
  { 
    path: '', 
    component: HomeComponent
  },
  { 
    path: 'students', 
    component: StudentComponent,
    children: [
      {
        path: '',
        component: StudentListComponent,
      },
      { 
        path: 'create',
        component: StudentAddComponent
      },
      {
        path: ':id/detail',
        component: StudentDetailComponent
      },
      {
        path: ':id/edit',
        component: StudentEditComponent
      }
    ]
  },
  { 
    path: 'courses', 
    component: CourseComponent,
    children: [
      {
        path: '',
        component: CourseListComponent,
      },
      { 
        path: 'create',
        component: CourseAddComponent
      },
      {
        path: ':id/detail',
        component: CourseDetailComponent
      },
      {
        path: ':id/edit',
        component: CourseEditComponent
      }
    ]
  },
  {
    path: 'home', 
    component: HomeComponent,
  }
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
