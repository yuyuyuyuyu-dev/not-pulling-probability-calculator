import { Injectable } from '@angular/core';
import { CalculateNonDrawingProbabilityService } from '../../../models/calculate-non-drawing-probability.service';
import { Probability } from '../../../models/types/NonDrawingProbability';

@Injectable({
  providedIn: 'root',
})
export class CalculateNonDrawingProbabilityManagerService {
  constructor(private service: CalculateNonDrawingProbabilityService) {}

  calculate({
    odds,
    pulls,
    onSucceed,
  }: {
    odds: number;
    pulls: number;
    onSucceed: (_: Probability) => void;
  }) {
    const probability = this.service.calculate({
      odds: odds,
      pulls: pulls,
    });

    onSucceed(probability);
  }
}
