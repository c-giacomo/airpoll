import { TestBed } from '@angular/core/testing';

import { AirpollService } from './airpoll.service';

describe('AirpollService', () => {
  let service: AirpollService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AirpollService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
