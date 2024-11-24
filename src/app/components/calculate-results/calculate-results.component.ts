import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-calculate-results',
    imports: [],
    templateUrl: './calculate-results.component.html',
    styleUrl: './calculate-results.component.scss'
})
export class CalculateResultsComponent {
  @Input() notPulling = 100;
  @Input() pulling = 0;
}
