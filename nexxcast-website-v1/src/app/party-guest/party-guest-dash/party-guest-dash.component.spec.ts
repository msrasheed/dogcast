import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PartyGuestDashComponent } from './party-guest-dash.component';

describe('PartyGuestDashComponent', () => {
  let component: PartyGuestDashComponent;
  let fixture: ComponentFixture<PartyGuestDashComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PartyGuestDashComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartyGuestDashComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
