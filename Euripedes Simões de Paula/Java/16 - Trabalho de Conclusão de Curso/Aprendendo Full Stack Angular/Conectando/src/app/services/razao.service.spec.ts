import { TestBed } from '@angular/core/testing';

import { RazaoService } from './razao.service';

describe('RazaoService', () => {
  let service: RazaoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RazaoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
