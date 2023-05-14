import { Component, OnInit, ɵɵqueryRefresh } from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import { RequestInfo } from 'src/app/models/request-info';
import { RequestService } from 'src/app/services/request.service';
import { catchError } from 'rxjs';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class RequestListComponent implements OnInit {

  dataSource:RequestInfo[] = [];//ELEMENT_DATA;
  columnsToDisplay = ['title', 'isResolved'];
  columnsToDisplayWithExpand = [...this.columnsToDisplay, 'expand'];
  expandedElement: RequestInfo | null = null;

  constructor(private requestService:RequestService) { }

  ngOnInit(): void {
    this.refresh();
  }

  refresh(){
    this.requestService.getRequests()
      .subscribe((result)=>{
        console.log(result);
        this.dataSource = result;
      })
  }

  blockClick(info: RequestInfo){
     this.requestService.block(info)
      .subscribe((result)=>{
        console.log(result);
        this.refresh();
      });
  }

  declineClick(info: RequestInfo){
    this.requestService.decline(info)
      .subscribe((result)=>{
        console.log(result);
        this.refresh();
      });
  }

  unblockClick(info: RequestInfo){
    this.requestService.unblock(info)
     .subscribe((result)=>{
       console.log(result);
       this.refresh();
     });
 }

}
