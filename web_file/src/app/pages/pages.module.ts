import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { SingupComponent } from './singup/singup.component';
import { UploadComponent } from './upload/upload.component';
import {RouterModule } from '@angular/router';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatRippleModule } from '@angular/material/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatExpansionModule} from '@angular/material/expansion';
import { DownloadComponent } from './download/download.component';
import { RequestComponent } from './request/request.component';
import { RequestModule } from './request/request.module';
import { ComponentComponent } from './component/component.component';
import { SuccessComponent } from './success/success.component';
import { LoadingComponent } from './loading/loading.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ClipboardModule } from 'ngx-clipboard';
import { HomeComponent } from './home/home.component';


@NgModule({
  declarations: [
    LoginComponent,
    SingupComponent,
    UploadComponent,
    DownloadComponent,
    ComponentComponent,
    SuccessComponent,
    LoadingComponent,
    RequestComponent,
    DashboardComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    RequestModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    BrowserAnimationsModule,
    MatCheckboxModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRippleModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    ClipboardModule
  ],
  exports: [
    LoginComponent,
    SingupComponent,
    UploadComponent,
    DownloadComponent,
    LoadingComponent,
    RequestComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PagesModule { }
