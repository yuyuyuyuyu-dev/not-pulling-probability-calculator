import { Component, Input } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';

@Component({
  selector: 'app-my-appbar',
  standalone: true,
  imports: [
    RouterLink,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
  ],
  templateUrl: './my-appbar.component.html',
  styleUrl: './my-appbar.component.scss',
})
export class MyAppbarComponent {
  router = new Router();

  @Input({ required: true }) appName = '';
  @Input({ required: true }) thirdPartyLicensesLink = '';
  @Input() repositoryUrl?: string;
  @Input() licenseUrl?: string;
}
