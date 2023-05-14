import { Component, OnInit } from '@angular/core';
import { SuccessType } from 'src/app/models/success-type';
import { CommonService } from 'src/app/services/common.service';
import { SuccessValues } from 'src/app/models/success-values';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.scss']
})
export class SuccessComponent implements OnInit {

  success: SuccessType = {} as SuccessType;

  request = SuccessValues.Request;
  registration = SuccessValues.Registered;

  constructor(private commonService: CommonService) {
    this.commonService.getSuccess()
      .subscribe((type)=>{
        
        this.success = type;
      })
  }

  ngOnInit(): void {
    
  }

}
