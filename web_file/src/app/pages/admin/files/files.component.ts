import { Component, OnInit } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import { FileService } from 'src/app/services/file.service';
import { FileInfo } from 'src/app/models/file-info';

@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class FilesComponent implements OnInit {

  dataSource:FileInfo[] = [];//ELEMENT_DATA;
  columnsToDisplay = ['name', 'size', 'user', 'blocked'];
  columnsToDisplayWithExpand = [...this.columnsToDisplay, 'expand'];
  expandedElement: FileInfo | null = null;

  constructor(private fileService:FileService ) {

  }

  ngOnInit(): void {
    this.reload();
  }

  reload(){
    this.fileService.getAllFiles().subscribe((result)=>{
      this.dataSource = result;  
      console.log(result);
        result.forEach((file)=>{
          console.log(file.id);
        })
    })
  }

  deleteClick(id:number){
    this.fileService.deleteFile(id)
      .subscribe((result)=>{
          console.log(result);
          // this.dataSource.filter((file)=>{file.id == id}).forEach((file)=>{ 
              
          //     var val = this.dataSource.findIndex(file);
          //     this.dataSource.splice();
          // } )
          this.reload();
      })
  }

}