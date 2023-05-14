import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  
  isLoading = false;

  formGroup  :FormGroup =  new FormGroup({
    emailFormControl: new FormControl('', [Validators.required, Validators.email]),
    passwordControl: new FormControl('',[Validators.required]),
  })
  
  user: User = {} as User;


  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  login(){
    this.isLoading = true;
    this.user.email = this.formGroup.controls['emailFormControl'].value;
    this.user.password = this.formGroup.controls['passwordControl'].value;
    this.authenticationService.login(this.user)
      .pipe(
         (value)=>{ 
          this.isLoading = false
          return value;
        },
        catchError((error)=>{
          console.log(error.errorMessage);
          return error;
        })
        
      )
      .subscribe((result)=>{
          //var header = result.headers.get('Authorization');
          console.log(result.header);
          this.authenticationService.setHeaders(result.headers)
         
          if(result.headers.get('Authorization') != null) this.router.navigate(['dashboard']);
         
          this.authenticationService.setLoginStatus(true);
          
      });
      
  }



}
