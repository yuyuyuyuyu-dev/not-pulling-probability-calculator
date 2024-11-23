import { TestBed } from '@angular/core/testing';

import { CalculateNonDrawingProbabilityManagerService } from './calculate-non-drawing-probability-manager.service';

describe('CalculateNonDrawingProbabilityManagerService', () => {
  let service: CalculateNonDrawingProbabilityManagerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CalculateNonDrawingProbabilityManagerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
