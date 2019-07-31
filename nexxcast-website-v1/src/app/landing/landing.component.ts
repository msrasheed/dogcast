import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {

  @ViewChild('goToPartyForm', {static: false}) goToPartyForm: FormGroup;

  constructor(public router: Router) {
  }

  ngOnInit() {
  }

  goToParty() {
    console.log(this.goToPartyForm.controls.partyId.value);
    this.router.navigate(['party']);
  }

  goToHost() {
    this.router.navigate(['host']);
  }
}
