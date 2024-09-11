import { TestBed } from '@angular/core/testing';

import { UserStaorageService } from './user-staorage.service';

describe('UserStaorageService', () => {
  let service: UserStaorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserStaorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
