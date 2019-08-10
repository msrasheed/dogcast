import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from 'core/core.module';
import { SpotifySignInComponent } from './spotify-sign-in/spotify-sign-in.component';
import { PartyHostRoutingModule } from './party-host-routing.module';
import { HostDashboardComponent } from './host-dashboard/host-dashboard.component';


@NgModule({
  declarations: [SpotifySignInComponent, HostDashboardComponent],
  imports: [
    CommonModule,
    PartyHostRoutingModule,
    CoreModule
  ]
})
export class PartyHostModule { }
