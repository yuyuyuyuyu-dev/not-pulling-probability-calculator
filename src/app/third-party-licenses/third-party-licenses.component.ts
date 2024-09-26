import { Component } from '@angular/core';
import {
  License,
  NgxMatThirdPartyLicensesListViewComponent,
} from 'ngx-mat-third-party-licenses-list-view';
import _licenses from '../../../public/third-party-licenses.json';

@Component({
  selector: 'app-third-party-licenses',
  standalone: true,
  imports: [NgxMatThirdPartyLicensesListViewComponent],
  templateUrl: './third-party-licenses.component.html',
  styleUrl: './third-party-licenses.component.scss',
})
export class ThirdPartyLicensesComponent {
  licenses = _licenses as License[];
}
