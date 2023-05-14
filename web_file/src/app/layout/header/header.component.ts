import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Output() menuButtonClick = new EventEmitter<string>();

  constructor(
    private router: Router, 
    private authenticationService: AuthenticationService) { }

  login: boolean = false;
  admin: boolean = false;

  ngOnInit(): void {
    this.reload();
    this.authenticationService.loginStatusChange()
      .subscribe(()=>{
        this.reload();
      })  
  }

  reload(){
    this.login = this.authenticationService.isLoggedIn();
    this.admin = this.authenticationService.isAdmin();
  }

  clicked(event: any){
    this.menuButtonClick.emit(event);
  }

  logout(){
     this.authenticationService.logout();
     this.router.navigate(['login']);
     this.reload();
  }

}
