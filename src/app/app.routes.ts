import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ThirdPartyLicensesComponent } from './third-party-licenses/third-party-licenses.component';

export const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "third-party-licenses", component: ThirdPartyLicensesComponent},
];
