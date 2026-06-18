import { Component, DestroyRef, EventEmitter, Input, OnInit, Output, inject } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

import { TransactionType } from '../../models/transaction-type.enum';
import { TransactionRequest } from '../../models/transaction.model';

@Component({
  selector: 'app-transaction-form',
  imports: [ReactiveFormsModule],
  templateUrl: './transaction-form.component.html',
  styleUrl: './transaction-form.component.css'
})
export class TransactionFormComponent implements OnInit {

  private readonly formBuilder = inject(FormBuilder);
  private readonly destroyRef = inject(DestroyRef);

  @Input() accountId: number | null = null;
  @Input() submitting = false;
  @Input() errorMessage = '';
  @Input() successMessage = '';

  @Output() transactionSubmitted = new EventEmitter<TransactionRequest>();
  @Output() messagesReset = new EventEmitter<void>();

  readonly transactionTypes = TransactionType;

  readonly transactionOptions = [
    { label: 'Deposit', value: TransactionType.DEPOSIT },
    { label: 'Withdrawal', value: TransactionType.WITHDRAWAL }
  ];

  readonly form = this.formBuilder.group({
    type: [TransactionType.DEPOSIT, Validators.required],
    amount: [null as number | null, [Validators.required, Validators.min(0.01)]]
  });

  ngOnInit(): void {
    this.form.valueChanges
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe(() => {
        if (this.errorMessage || this.successMessage) {
          this.messagesReset.emit();
        }
      });
  }

  selectType(type: TransactionType): void {
    this.form.controls.type.setValue(type);
  }

  isSelected(type: TransactionType): boolean {
    return this.form.controls.type.value === type;
  }

  blockInvalidKeys(event: KeyboardEvent): void {
    if (['-', '+', 'e', 'E'].includes(event.key)) {
      event.preventDefault();
    }
  }

  submit(): void {
    if (this.accountId === null || this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const { type, amount } = this.form.getRawValue();

    this.transactionSubmitted.emit({
      accountId: this.accountId,
      type: type as TransactionType,
      amount: Number(amount)
    });
  }
}
