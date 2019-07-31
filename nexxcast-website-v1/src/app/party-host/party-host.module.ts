import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SpotifySignInComponent } from './spotify-sign-in/spotify-sign-in.component';
import { PartyHostRoutingModule } from './party-host-routing.module';


@NgModule({
  declarations: [SpotifySignInComponent],
  imports: [
    CommonModule,
    PartyHostRoutingModule
  ]
})
export class PartyHostModule { }
