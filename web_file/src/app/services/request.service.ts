import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RequestInfo } from '../models/request-info';
import { BaseURL } from '../utils/baseURL';
import { ServiceConstants } from './service-constants';

// const httpOptions = {
//   headers: new HttpHeaders({
//     'Content-Type':  'application/json',
//      Authorization: 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmNAbWFpbC5jb20iLCJleHAiOjE2NTc4OTY4MTN9.NAdi8Fo4xiyKcTF22ntU8TMwh2EVSebhNg9u1HQDcU_qEmTRLGjBtTv3CGLnyFhImN1_m2uhj_EIDwAGYc5FJA'
//   }),
// };

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
       Authorization:  this.getAuthToken()
    }),
  };

  constructor(private httpClient: HttpClient) { 

  }

  getRequests():Observable<RequestInfo[]>{
    return this.httpClient.get<RequestInfo[]>(BaseURL.BASE_URL+"requests", this.httpOptions);
  }

  sendRequest(request: RequestInfo):Observable<RequestInfo>{
    console.log(request);
    return this.httpClient.post<RequestInfo>(BaseURL.BASE_URL+"requests", request, this.httpOptions);
  }

  decline(request: RequestInfo):Observable<RequestInfo>{
    console.log(request);
    return this.httpClient.put<RequestInfo>(BaseURL.BASE_URL+"requests/decline", request, this.httpOptions);
  }

  block(request: RequestInfo):Observable<RequestInfo>{
    console.log(request);
    return this.httpClient.put<RequestInfo>(BaseURL.BASE_URL+"requests/block", request, this.httpOptions);
  }

  unblock(request: RequestInfo):Observable<RequestInfo>{
    console.log(request);
    return this.httpClient.put<RequestInfo>(BaseURL.BASE_URL+"requests/unblock", request, this.httpOptions);
  }

  getAuthToken(): string{
    var header = localStorage.getItem(ServiceConstants.AUTHORIZATION);
    if(header == null) header = "";
    return header;
  }

}
