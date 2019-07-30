import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SpotifySignInComponent } from './spotify-sign-in/spotify-sign-in.component';


const routes: Routes = [
  { path: '', redirectTo: 'signin'},
  { path: 'signin', component: SpotifySignInComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PartyHostRoutingModule { }
