import { TestBed } from '@angular/core/testing';

import { BalanceteService } from './balancete.service';

describe('BalanceteService', () => {
  let service: BalanceteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BalanceteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
