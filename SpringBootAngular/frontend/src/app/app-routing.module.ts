import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { EmployeeComponent } from './employee/employee.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { AddUserFormComponent } from './add-user-form/add-user-form.component';
import { AuthGuard } from './services/guards/auth.guard';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { 
    path: '', 
    component: HomeComponent
  },
  { 
    path: 'employee', 
    component: EmployeeComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        component: EmployeeListComponent,
      },
      { 
        path: "add",
        component: AddUserFormComponent
      }
    ]
  },
  {
    path: 'home', 
    component: HomeComponent,
    children: [
      { 
        path: "login",
        component: LoginComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
