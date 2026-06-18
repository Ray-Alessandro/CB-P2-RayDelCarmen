import { TransactionType } from './transaction-type.enum';

export interface TransactionRequest {
  accountId: number;
  type: TransactionType;
  amount: number;
}

export interface Transaction {
  id: number;
  type: TransactionType;
  amount: number;
  createdAt: string;
  accountId: number;
}
