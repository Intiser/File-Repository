import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FileInfo } from 'src/app/models/file-info';
import { RequestInfo } from 'src/app/models/request-info';
import { SuccessValues } from 'src/app/models/success-values';
import { CommonService } from 'src/app/services/common.service';
import { FileService } from 'src/app/services/file.service';
import { RequestService } from 'src/app/services/request.service';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.scss']
})
export class RequestComponent implements OnInit {

  isLoading = false;

  formGroup: FormGroup = new FormGroup({
     title: new  FormControl('', Validators.required ),
     description: new FormControl('', Validators.required),
  });

  file:FileInfo = {} as FileInfo;
  request:RequestInfo = {} as RequestInfo;

  constructor(
    private fileService:FileService,
    private requestService:RequestService,
    private commonService: CommonService,
    private router:Router,
    )
     {
    this.fileService.getFile().subscribe((file)=> {
      this.file = file;
      this.request.file = this.file;
      console.log(file);
    }
    ); 
  }

  ngOnInit(): void {
    this.request.file = this.file;
  }

  submit(){
    this.isLoading = true;
    this.request.file =this.file
    this.request.title = this.formGroup.controls['title'].value;
    this.request.description = this.formGroup.controls['description'].value;
    this.requestService.sendRequest(this.request)
      .subscribe((result)=>{
        console.log(result);
        this.isLoading = false;
        this.commonService.setSuccessType({ type: SuccessValues.Request })
        this.router.navigate(['success']);
      });
  }

}
