import { CurrencyPipe } from '@angular/common';
import { Component, Input } from '@angular/core';

import { Account } from '../../models/account.model';

@Component({
  selector: 'app-account-view',
  imports: [CurrencyPipe],
  templateUrl: './account-view.component.html',
  styleUrl: './account-view.component.css'
})
export class AccountViewComponent {

  @Input() account: Account | null = null;
  @Input() loading = false;
  @Input() errorMessage = '';
}
