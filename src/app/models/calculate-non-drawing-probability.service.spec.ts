import { TestBed } from '@angular/core/testing';

import { CalculateNonDrawingProbabilityService } from './calculate-non-drawing-probability.service';

describe('CalculateNonDrawingProbabilityService', () => {
  let service: CalculateNonDrawingProbabilityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalculateNonDrawingProbabilityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
