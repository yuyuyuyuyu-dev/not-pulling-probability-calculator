import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ThirdPartyLicensesComponent } from './pages/third-party-licenses/third-party-licenses.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'third-party-licenses', component: ThirdPartyLicensesComponent },
];
