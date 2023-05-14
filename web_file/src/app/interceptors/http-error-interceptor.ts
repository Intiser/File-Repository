import {

    HttpEvent,
   
    HttpInterceptor,
   
    HttpHandler,
   
    HttpRequest,
   
    HttpResponse,
   
    HttpErrorResponse
   
   } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
   
   import { Observable, throwError } from 'rxjs';
   
   import { retry, catchError } from 'rxjs/operators';


@Injectable({
    providedIn: 'root'
})
export class HttpErrorInterceptor implements HttpInterceptor {

      constructor(private router: Router){

      }
   
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
   
      return next.handle(request)
   
        .pipe(
   
          //retry(1),
   
          catchError((error: HttpErrorResponse) => {
   
            let errorMessage = '';
   
            if (error.error instanceof ErrorEvent) {
   
              // client-side error
   
              errorMessage = error.error.message;
   
            } else {
   
              // server-side error
   
              errorMessage = error.message
              
            }

            switch (error.status) {
              case 401:      //login
                  window.alert("Not Authorized/ User or Password are not Matched");
                  this.router.navigate(["login"]);
                  break;
              case 403:     //forbidden
                  window.alert("Not Authorized/ User or Password are not Matched");
                  this.router.navigate(["login"]);
                  break;
              case  500:     //forbidden
                  window.alert("Server Error: Please report the problem");
                  //this.router.navigate(["login"]);
                  break;

          }


   
            //window.alert(errorMessage);
   
            return throwError(errorMessage);
   
          })
   
        )
   
    }
   
   }