import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpotifySignInComponent } from './spotify-sign-in.component';

describe('SpotifySignInComponent', () => {
  let component: SpotifySignInComponent;
  let fixture: ComponentFixture<SpotifySignInComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpotifySignInComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpotifySignInComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
