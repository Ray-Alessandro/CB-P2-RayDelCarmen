import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Transaction, TransactionRequest } from '../models/transaction.model';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private readonly apiUrl = '/api/transactions';

  constructor(private readonly http: HttpClient) {}

  registerTransaction(request: TransactionRequest): Observable<Transaction> {
    return this.http.post<Transaction>(this.apiUrl, request);
  }
}
