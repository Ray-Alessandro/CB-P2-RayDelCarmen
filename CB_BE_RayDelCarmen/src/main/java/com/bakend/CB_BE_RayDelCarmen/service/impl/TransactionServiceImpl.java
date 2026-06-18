package com.bakend.CB_BE_RayDelCarmen.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bakend.CB_BE_RayDelCarmen.exception.InsufficientBalanceException;
import com.bakend.CB_BE_RayDelCarmen.exception.InvalidAmountException;
import com.bakend.CB_BE_RayDelCarmen.model.Account;
import com.bakend.CB_BE_RayDelCarmen.model.Transaction;
import com.bakend.CB_BE_RayDelCarmen.model.TransactionType;
import com.bakend.CB_BE_RayDelCarmen.repository.AccountRepository;
import com.bakend.CB_BE_RayDelCarmen.repository.TransactionRepository;
import com.bakend.CB_BE_RayDelCarmen.service.AccountService;
import com.bakend.CB_BE_RayDelCarmen.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private final AccountService accountService;
	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;

	@Override
	@Transactional
	public Transaction registerTransaction(Long accountId, TransactionType type, BigDecimal amount) {
		validateAmount(amount);

		Account account = accountService.getAccountById(accountId);
		applyBalanceChange(account, type, amount);

		Transaction transaction = Transaction.builder()
				.type(type)
				.amount(amount)
				.account(account)
				.build();

		accountRepository.save(account);
		return transactionRepository.save(transaction);
	}

	private void validateAmount(BigDecimal amount) {
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new InvalidAmountException();
		}
	}

	private void applyBalanceChange(Account account, TransactionType type, BigDecimal amount) {
		if (type == TransactionType.DEPOSIT) {
			account.setBalance(account.getBalance().add(amount));
			return;
		}

		if (account.getBalance().compareTo(amount) < 0) {
			throw new InsufficientBalanceException(account.getId());
		}

		account.setBalance(account.getBalance().subtract(amount));
	}
}
