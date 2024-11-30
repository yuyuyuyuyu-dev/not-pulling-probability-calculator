import { Injectable } from '@angular/core';
import { CalculateNotPullingProbabilityService } from '../domain/calculate-not-pulling-probability.service';
import { Probability } from '../domain/types/Probability';

@Injectable({
  providedIn: 'root',
})
export class CalculateNotPullingProbabilityUseCaseService {
  constructor(private service: CalculateNotPullingProbabilityService) {}

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
