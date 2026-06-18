import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

import { AccountViewComponent } from '../../components/account-view/account-view.component';
import { TransactionFormComponent } from '../../components/transaction-form/transaction-form.component';
import { Account } from '../../models/account.model';
import { ErrorResponse } from '../../models/error-response.model';
import { TransactionRequest } from '../../models/transaction.model';
import { AccountService } from '../../services/account.service';
import { TransactionService } from '../../services/transaction.service';

@Component({
  selector: 'app-banking-page',
  imports: [ReactiveFormsModule, AccountViewComponent, TransactionFormComponent],
  templateUrl: './banking-page.component.html',
  styleUrl: './banking-page.component.css'
})
export class BankingPageComponent implements OnInit {

  private readonly formBuilder = inject(FormBuilder);
  private readonly accountService = inject(AccountService);
  private readonly transactionService = inject(TransactionService);

  account: Account | null = null;
  accountLoading = false;
  accountErrorMessage = '';

  transactionSubmitting = false;
  transactionErrorMessage = '';
  transactionSuccessMessage = '';

  readonly searchForm = this.formBuilder.group({
    accountId: [1, [Validators.required, Validators.min(1)]]
  });

  ngOnInit(): void {
    this.searchAccount();
  }

  searchAccount(): void {
    if (this.searchForm.invalid) {
      this.searchForm.markAllAsTouched();
      return;
    }

    const accountId = Number(this.searchForm.value.accountId);
    this.loadAccount(accountId);
  }

  clearTransactionMessages(): void {
    this.transactionErrorMessage = '';
    this.transactionSuccessMessage = '';
  }

  onTransactionSubmitted(request: TransactionRequest): void {
    this.transactionSubmitting = true;
    this.transactionErrorMessage = '';
    this.transactionSuccessMessage = '';

    this.transactionService.registerTransaction(request).subscribe({
      next: () => {
        this.transactionSubmitting = false;
        this.transactionSuccessMessage = 'Transaction registered successfully.';
        this.loadAccount(request.accountId);
      },
      error: (error: HttpErrorResponse) => {
        this.transactionSubmitting = false;
        this.transactionErrorMessage = this.resolveErrorMessage(error);
      }
    });
  }

  private loadAccount(accountId: number): void {
    this.accountLoading = true;
    this.accountErrorMessage = '';
    this.transactionErrorMessage = '';
    this.transactionSuccessMessage = '';

    this.accountService.getAccountById(accountId).subscribe({
      next: (account) => {
        this.account = account;
        this.accountLoading = false;
      },
      error: (error: HttpErrorResponse) => {
        this.account = null;
        this.accountLoading = false;
        this.accountErrorMessage = this.resolveErrorMessage(error);
      }
    });
  }

  private resolveErrorMessage(error: HttpErrorResponse): string {
    const apiError = error.error as ErrorResponse | null;

    if (apiError?.message) {
      return apiError.message;
    }

    return 'An unexpected error occurred. Please try again.';
  }
}
