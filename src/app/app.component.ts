import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MyAppbarComponent } from './my-appbar/my-appbar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    MyAppbarComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'non-drawing-probability-calculator';
}
