import { TestBed } from '@angular/core/testing';

import CarDataServiceService from './car-data.service';

describe('CarDataServiceService', () => {
  let service: CarDataServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CarDataServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
