import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PartyGuestDashComponent } from './party-guest-dash/party-guest-dash.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', component: PartyGuestDashComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PartyGuestRoutingModule { }
