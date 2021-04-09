import { TestBed } from '@angular/core/testing';

import { AirpollServiceService } from './airpoll-service.service';

describe('AirpollServiceService', () => {
  let service: AirpollServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AirpollServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
