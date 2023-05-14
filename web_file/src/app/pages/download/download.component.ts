import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { FileInfo } from 'src/app/models/file-info';
import { FileService } from 'src/app/services/file.service';

@Component({
  selector: 'app-download',
  templateUrl: './download.component.html',
  styleUrls: ['./download.component.scss']
})
export class DownloadComponent implements OnInit {

  key: string | null = null;
  file: FileInfo = {} as FileInfo;

  constructor(
    private route: ActivatedRoute,
    private router:Router,
    private fileService:FileService,
    ) { 
    this.route.paramMap.subscribe((params: ParamMap) => {
      console.log(params.get('some_data'));
      this.key = params.get('some_data');
    })
  }

  // TODO: not a valid link

  ngOnInit(): void {
    if(this.key != null)
      this.fileService.getFilesByPath(this.key)
        .subscribe((result)=>{
           console.log(result);
           this.file = result;
        })
  }

  getStringKB(size: any):string{
    var val = "";
    val = val + ( size / 1024 ).toFixed(2) + " KB";
    return val;
  }

  reportFile(){
    if(this.file == null) return;
    this.fileService.setUploadedFile(this.file);
    this.router.navigate(['request']);
  }

  download(){
      this.fileService.getFileByInfo(this.file)
        .subscribe((result)=>{
            console.log(result);
            let val = this.base64ToArrayBuffer(result.data)
            this.saveByteArray(result.name,val,result.fileType);
            console.log(val);
            console.log(val.length);
        })

  }

  base64ToArrayBuffer(base64: any) {
    var binaryString = window.atob(base64);
    var binaryLen = binaryString.length;
    var bytes = new Uint8Array(binaryLen);
    for (var i = 0; i < binaryLen; i++) {
       var ascii = binaryString.charCodeAt(i);
       bytes[i] = ascii;
    }
    return bytes;
  }

  saveByteArray(reportName:any, byte:any, ftype:any) {
    var blob = new Blob([byte], {type: ftype});
    var link = document.createElement('a');
    link.href = window.URL.createObjectURL(blob);
    var fileName = reportName;
    link.download = fileName;
    link.click();
  }

}
