import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SuccessValues } from 'src/app/models/success-values';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { CommonService } from 'src/app/services/common.service';

@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.scss']
})
export class SingupComponent implements OnInit {

  isLoading = false;

  formGroup =   new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email ] ),
    password: new FormControl('', [Validators.required, Validators.minLength(8)]),
    confirm_password: new  FormControl('', [Validators.required, Validators.minLength(8)]),
    agreed_to_terms: new FormControl('', Validators.required)
  }
  );

  user: User = {} as User;

  constructor(
    private authenticationService: AuthenticationService,
    private commonService: CommonService,
    private router:Router,
  ) 
  { }

  ngOnInit(): void {
      // this.authenticationService.getString().subscribe((result)=>{
      //     console.log(result);
      // })
  }

  registerClicked(){
      this.isLoading = true;
      const user = {
          firstName: this.formGroup.controls['firstName'].value,
          lastName: this.formGroup.controls['lastName'].value,
          email: this.formGroup.controls['email'].value,
          password: this.formGroup.controls['password'].value
      };
      console.log(user);
      this.authenticationService.createUser(user)
        .subscribe((result)=>{
           this.commonService.setSuccessType({ type: SuccessValues.Registered });
           this.router.navigate(['success']);
            console.log(result);
            this.isLoading = false;
        });
  }

  checkPasswords(): boolean {
     const pass1:string =  this.formGroup.controls['password'].value;
     const pass2:string = this.formGroup.controls['confirm_password'].value;
     
     //console.log(pass1 === pass2);
     return pass1===pass2;
  }

 

}
