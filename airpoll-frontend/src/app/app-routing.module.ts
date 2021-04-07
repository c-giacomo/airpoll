import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { WhoamiComponent } from './component/whoami/whoami.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'whoami', component: WhoamiComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
