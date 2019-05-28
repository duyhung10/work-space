import { Component, OnInit, OnDestroy } from '@angular/core';

import { DepartmentService } from '../services/department.service';
import { Subscription } from 'rxjs';
import { Location } from '@angular/common';

@Component({
  selector: 'app-department-list',
  templateUrl: './department-list.component.html',
  styleUrls: ['./department-list.component.css']
})
export class DepartmentListComponent implements OnInit, OnDestroy {

  public subscription: Subscription;
  
  public departmentDTOs: {};

  constructor(
    public departmentService: DepartmentService,
    public location: Location
    ) { }

  ngOnInit() {
    this.subscription = this.departmentService.getAllDepartmentFullInfo().subscribe((data) => {
      this.departmentDTOs = data;
      console.log(this.departmentDTOs);
    });
  }

  ngOnDestroy(): void {
    if(this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  onBack(){
    this.location.back();
  }
}
