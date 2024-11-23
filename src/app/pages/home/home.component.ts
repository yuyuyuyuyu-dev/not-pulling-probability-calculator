import { Component } from '@angular/core';
import { CalculateNonDrawingProbabilityManagerService } from './managers/calculate-non-drawing-probability-manager.service';
import { InputFieldsComponent } from '../../components/input-fields/input-fields.component';
import { InputValues } from '../../components/input-fields/types/InputValues';
import { CalculateResultsComponent } from '../../components/calculate-results/calculate-results.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [InputFieldsComponent, CalculateResultsComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  notPulling = 100;
  pulling = 0;

  constructor(
    private calculateManager: CalculateNonDrawingProbabilityManagerService,
  ) {}

  onInputValueChanged(inputValues: InputValues) {
    this.calculateManager.calculate({
      odds: inputValues.odds,
      pulls: inputValues.pulls,
      onSucceed: (probability) => {
        this.notPulling = probability.notPulling * 100;
        this.pulling = probability.pulling * 100;
      },
    });
  }
}
