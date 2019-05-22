import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataShareService {
  private data: Subject<any> = new Subject();
  
  constructor() { }

  public getData(){
    return this.data;
  }

  public sendData(value){
    this.data.next(value);
  }
}
