import { Injectable } from '@angular/core';
import { Observable, ReplaySubject } from 'rxjs';
import { SuccessType } from '../models/success-type';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor() { 

  }

  private Subject = new ReplaySubject<SuccessType>(1);
  private Snapshot: SuccessType| null = null;

    setSuccessType(file: SuccessType){
        this.Snapshot = file;
        this.Subject.next(this.Snapshot);
    }

    getSuccess(): Observable<SuccessType> {
        return this.Subject;
    }

}
