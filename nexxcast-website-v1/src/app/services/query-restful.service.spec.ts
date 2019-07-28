import { TestBed } from '@angular/core/testing';

import { QueryRestfulService } from './query-restful.service';

describe('QueryRestfulService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: QueryRestfulService = TestBed.get(QueryRestfulService);
    expect(service).toBeTruthy();
  });
});
