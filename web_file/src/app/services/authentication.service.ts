import { HttpClient, HttpClientModule, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, ReplaySubject, Subject, tap } from 'rxjs';
import { User } from '../models/user';
import { BaseURL } from '../utils/baseURL';
import { ServiceConstants } from './service-constants';

// const httpOptions = {
//   headers: new HttpHeaders({
//     'Content-Type':  'application/json',
//      Authorization: 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmNAbWFpbC5jb20iLCJleHAiOjE2NTc4OTY4MTN9.NAdi8Fo4xiyKcTF22ntU8TMwh2EVSebhNg9u1HQDcU_qEmTRLGjBtTv3CGLnyFhImN1_m2uhj_EIDwAGYc5FJA'
//   })
// };



@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
       Authorization:  this.getAuthToken()
    })
  };

  
httpOptions1 = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
     Authorization: this.getAuthToken()
  })
};
  

  constructor(private httpClient:HttpClient) {
    //BaseURL.BASE_URL
  }

  getString(): Observable<any>{
    
     return this.httpClient.get<any>(BaseURL.BASE_URL+"users", this.httpOptions);
  }

  createUser(user:User): Observable<any>{
    return this.httpClient.post<any>(BaseURL.BASE_URL+"users", user);
  }

  login(user:User):Observable<any>{
    return this.httpClient.post<any>(BaseURL.BASE_URL+"users/login",user, 
        {observe: 'response'})
            .pipe(
            

            
            );
  }

  setHeaders(headers: any){
    if(headers == undefined) return;
    if(headers == null) return;
    var header = headers.get('Authorization');
    var admin = headers.get('SuperAdmin');
    this.setToken(header);
    this.setAdmin(admin);
    console.log(admin);
  }

  logout(){
    this.setToken("");
    this.setAdmin("");
    this.setLoginStatus(false)
  }

  isLoggedIn():boolean{
     if(this.getAuthToken() == "") return false
     return true;
  }

  private setToken(token: string){
    localStorage.setItem(ServiceConstants.AUTHORIZATION, token);
  }

  getAuthToken(): string{
    var header = localStorage.getItem(ServiceConstants.AUTHORIZATION);
    if(header == null) header = "";
    return header;
  }

  private setAdmin(token: string){
    localStorage.setItem(ServiceConstants.ADMIN, token);
  }

  isAdmin(): boolean{
    var admin = localStorage.getItem(ServiceConstants.ADMIN);
    if(admin == null) return false;
    if(admin == "true") return true;
    console.log(admin);
    return false;
  }


  private loggedIn: Subject<boolean> = new ReplaySubject<boolean>(1);

  setLoginStatus(status: boolean){
    this.loggedIn.next(status)
  }

  loginStatusChange(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

}
