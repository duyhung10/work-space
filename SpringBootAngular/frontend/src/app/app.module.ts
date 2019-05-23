import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// add-user-form
import { MatCardModule } from '@angular/material';
import { MatDividerModule } from '@angular/material';
import { MatFormFieldModule } from '@angular/material';
import { MatInputModule } from '@angular/material';
import { MatDatepickerModule } from '@angular/material';
import { MatNativeDateModule } from '@angular/material';
import { MatRadioModule } from '@angular/material';
import { MatSelectModule } from '@angular/material';
import { MatIconModule } from '@angular/material';
import { MatCheckboxModule } from '@angular/material';
import { MatButtonModule } from '@angular/material';

// employee-detail
import { MatDialogModule} from '@angular/material';

// employee-search
import { MatChipsModule } from '@angular/material';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddUserFormComponent } from './add-user-form/add-user-form.component';
import { HomeComponent } from './home/home.component';
import { EmployeeComponent } from './employee/employee.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { ErrorValidateComponent } from './error-validate/error-validate.component';
import { EmployeeDetailComponent } from './employee-detail/employee-detail.component';
import { EmployeeSearchComponent } from './employee-search/employee-search.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    AddUserFormComponent,
    HomeComponent,
    EmployeeComponent,
    EmployeeListComponent,
    ErrorValidateComponent,
    EmployeeDetailComponent,
    EmployeeSearchComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatDividerModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRadioModule,
    MatSelectModule,
    MatIconModule,
    MatCheckboxModule,
    MatButtonModule,
    MatDialogModule,
    MatChipsModule
  ],
  providers: [],
  entryComponents: [EmployeeDetailComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
