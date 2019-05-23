import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataShareService } from '../services/data-share.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  error = false;

  constructor(
    private router: Router,
    private dataShareService: DataShareService
  ) { }

  ngOnInit() {
  }
  onLogin(username: string, password: string){
    let user = {
      username: username,
      password: password
    };

    if(username == 'admin' && password =='admin'){
      sessionStorage.setItem('user', JSON.stringify(user));
      this.router.navigate(['employee']);

      this.dataShareService.sendData(true);

    } else {
      this.error = true;
    }
  }
}
