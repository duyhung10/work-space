import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { StudentComponent } from './student/student.component';
import { StudentListComponent } from './student-list/student-list.component';
import { StudentAddComponent } from './student-add/student-add.component';
import { TableTestComponent } from './table-test/table-test.component';

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
      }
    ]
  },
  {
    path: 'home', 
    component: HomeComponent,
  },
  {
    path: 'test', 
    component: TableTestComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
