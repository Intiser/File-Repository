import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ClipboardService } from 'ngx-clipboard';
import { pipe } from 'rxjs';
import { FileInfo } from 'src/app/models/file-info';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})
export class UploadComponent implements OnInit {

  isLoading = false;

  formGroup:FormGroup = new FormGroup({
    message: new FormControl('', Validators.required)
  });
  
  file: File = new File([],"");

  uploadedFile: FileInfo| null = null;

  copied = false;

  constructor(
      private formBuilder:FormBuilder,
      private fileService: FileService,
      private clipboardApi: ClipboardService,
    ) {
    
  }

  ngOnInit(): void {
    this.copied = false;
  }

  handleFileInput(event: any) {
    this.file =  event.target.files[0];
  }

  upload(){
    this.isLoading = true;
    this.fileService.uploadFile(this.file)
    .pipe((result)=>{ 
      this.isLoading = false;
      return result;
    })
    .subscribe((result)=>{
      console.log(result);
        this.uploadedFile = result;
    })
    this.copied = false;
  }

  copyText(){
    if(this.uploadedFile?.downloadLink == null) return;
    this.clipboardApi.copyFromContent(this.uploadedFile?.downloadLink);
    this.copied = true;
  }

}
