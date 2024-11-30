import { TestBed } from '@angular/core/testing';
import { CalculateNotPullingProbabilityService } from './calculate-not-pulling-probability.service';

describe('CalculateNotPullingProbabilityService', () => {
  let service: CalculateNotPullingProbabilityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalculateNotPullingProbabilityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
