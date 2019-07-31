import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SpotifySignInComponent } from './spotify-sign-in/spotify-sign-in.component';
import { HostDashboardComponent } from './host-dashboard/host-dashboard.component';


const routes: Routes = [
  { path: '', redirectTo: 'signin'},
  { path: 'signin', component: SpotifySignInComponent },
  { path: 'party', component: HostDashboardComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PartyHostRoutingModule { }
