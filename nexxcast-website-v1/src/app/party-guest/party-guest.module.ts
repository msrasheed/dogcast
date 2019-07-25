import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PartyGuestRoutingModule } from './party-guest-routing.module';
import { PartyGuestDashComponent } from './party-guest-dash/party-guest-dash.component';



@NgModule({
  declarations: [PartyGuestDashComponent],
  imports: [
    CommonModule,
    PartyGuestRoutingModule
  ]
})
export class PartyGuestModule { }
