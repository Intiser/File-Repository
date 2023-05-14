import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './pages/admin/admin.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { DownloadComponent } from './pages/download/download.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { PagesRoutingModule } from './pages/pages-routing.module';
import { RequestComponent } from './pages/request/request.component';
import { SingupComponent } from './pages/singup/singup.component';
import { SuccessComponent } from './pages/success/success.component';
import { UploadComponent } from './pages/upload/upload.component';


const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: SingupComponent,
  },
  {
    path: 'upload',
    component: UploadComponent,
  },
  {
    path: 'admin',
    component: AdminComponent,
  },
  {
    path: 'download/:some_data',
    component: DownloadComponent,
    pathMatch: 'full'
  },
  {
    path: 'request',
    component: RequestComponent,
  },
  {
    path: 'success',
    component: SuccessComponent,
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
  },
  {
    path: '',
    component: HomeComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    PagesRoutingModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
