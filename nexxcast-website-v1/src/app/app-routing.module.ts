import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LandingComponent } from './landing/landing.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', component: LandingComponent },
  {
    path: 'party',
    loadChildren: './party-guest/party-guest.module#PartyGuestModule'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
