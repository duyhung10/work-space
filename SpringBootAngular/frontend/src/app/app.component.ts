import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { DataShareService } from './services/data-share.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy{
  title = 'HumanResource-Management';
  
  private subscription: Subscription;
  public isLogin: boolean;

  constructor(
    private router: Router,
    private dataShareService: DataShareService
  ) {}
  
  ngOnInit() {
    if(sessionStorage.getItem('user')){
      
      this.isLogin = true;
    } else {
      this.isLogin = false;
    }

    this.subscription = this.dataShareService.getData().subscribe(data => {
      this.isLogin = data;
    });
  }

  ngOnDestroy() {
    if(sessionStorage.getItem('user')){
      sessionStorage.removeItem('user');
    }

    if(this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  onLogout(){
    if(sessionStorage.getItem('user')){
      sessionStorage.removeItem('user');
      this.router.navigate(['/home/login']);
      this.isLogin = false;
    }

  }

}
