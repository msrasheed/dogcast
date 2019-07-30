import { TestBed } from '@angular/core/testing';

import { SpotifySignInService } from './spotify-sign-in.service';

describe('SpotifySignInService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SpotifySignInService = TestBed.get(SpotifySignInService);
    expect(service).toBeTruthy();
  });
});
