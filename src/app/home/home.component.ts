import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  protected readonly odds = signal(0);
  protected readonly times = signal(0);

  protected onOddsInput(event: Event) {
    this.odds.set(Number((event.target as HTMLInputElement).value));
  }

  protected onTimesInput(event: Event) {
    this.times.set(Number((event.target as HTMLInputElement).value));
  }

  protected calcNonDrawingProbability(odds: number, times: number) {
    return ((1 - (odds / 100)) ** times) * 100;
  }
}
