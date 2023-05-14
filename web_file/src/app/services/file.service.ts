import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, ReplaySubject } from 'rxjs';
import { FileDownloadInfo } from '../models/file-downloads';
import { FileInfo } from '../models/file-info';
import { BaseURL } from '../utils/baseURL';
import { ServiceConstants } from './service-constants';

// const httpOptions = {
//   headers: new HttpHeaders({
//     'Content-Type':  'application/json',
//      Authorization: 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmNAbWFpbC5jb20iLCJleHAiOjE2NTc4OTY4MTN9.NAdi8Fo4xiyKcTF22ntU8TMwh2EVSebhNg9u1HQDcU_qEmTRLGjBtTv3CGLnyFhImN1_m2uhj_EIDwAGYc5FJA'
//   })
// };

// const httpOptionsFile = {
//   headers: new HttpHeaders({
    
//      Authorization: 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmNAbWFpbC5jb20iLCJleHAiOjE2NTc4OTY4MTN9.NAdi8Fo4xiyKcTF22ntU8TMwh2EVSebhNg9u1HQDcU_qEmTRLGjBtTv3CGLnyFhImN1_m2uhj_EIDwAGYc5FJA'
//   })
// };

@Injectable({
  providedIn: 'root'
})
export class FileService{

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
       Authorization:  this.getAuthToken()
    })
  };
  
  httpOptionsFile = {
    headers: new HttpHeaders({
      
       Authorization: this.getAuthToken()
    })
  };

  constructor(private httpClient: HttpClient) { 
    
  }

  uploadFile(file: File ): Observable<FileInfo>{
    const formData = new FormData();
    formData.append("file", file, file.name);
    return this.httpClient.post<FileInfo>(BaseURL.BASE_URL+"files/upload", formData,  this.httpOptionsFile);
  }

  getAllFiles(): Observable<FileInfo[]>{
    return  this.httpClient.get<FileInfo[]>(BaseURL.BASE_URL+"files",  this.httpOptions);
  }

  deleteFile(id: number): Observable<any>{
    var value:string = "files/delete/" + id;
    return this.httpClient.delete<any>(BaseURL.BASE_URL+ value,  this.httpOptionsFile);
  } 

  getFilesByPath(key: string): Observable<FileInfo>{
    var add= "files/" + key;
    return this.httpClient.get<FileInfo>(BaseURL.BASE_URL+ add,  this.httpOptions);
  }

  getFileByInfo(file: FileInfo): Observable<FileDownloadInfo>{
    
    return this.httpClient.post<FileDownloadInfo>(BaseURL.BASE_URL+"files/download", file, this.httpOptions);
  }

  private fileSubject = new ReplaySubject<FileInfo>(1);
  private fileSnapshot: FileInfo| null = null;

    setUploadedFile(file: FileInfo){
        this.fileSnapshot = file;
        this.fileSubject.next(this.fileSnapshot);
    }

    getFile(): Observable<FileInfo> {
        return this.fileSubject;
    }

    getAuthToken(): string{
      var header = localStorage.getItem(ServiceConstants.AUTHORIZATION);
      if(header == null) header = "";
      return header;
    }

}
