import { Routes } from '@angular/router';

import { BankingPageComponent } from './pages/banking-page/banking-page.component';

export const routes: Routes = [
  {
    path: '',
    component: BankingPageComponent
  },
  {
    path: '**',
    redirectTo: ''
  }
];
