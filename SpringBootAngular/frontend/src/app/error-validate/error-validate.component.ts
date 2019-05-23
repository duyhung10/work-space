import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-error-validate',
  templateUrl: './error-validate.component.html',
  styleUrls: ['./error-validate.component.css']
})
export class ErrorValidateComponent implements OnInit {

  @Input('control') control;
  @Input('name-control') controlName;

  constructor() { }

  ngOnInit() {
  }

  get message() {
    for(let err in this.control.errors){
      if(this.control.touched){
        return this.getErrorMessage(err, this.control.errors[err]);
      }
    }
  }

  getErrorMessage(err, value){
    let messages = {
      'required' : `${this.controlName} không được bỏ trống`,
      'minlength' : `${this.controlName} tối thiểu ${value.requiredLength} character`,
      'maxlength' : `${this.controlName} tối đa ${value.requiredLength} character`,
      'pattern' : `${this.controlName} không đúng định dạng`,
      'errorNumberId': `${this.controlName} không đúng định dạng, chứng minh thư có 9 hoặc 12 số`,
      'errorConfirmpassword': 'Mật khẩu không trùng khớp'
    }
    return messages[err];
  }

}
