package com.bakend.CB_BE_RayDelCarmen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bakend.CB_BE_RayDelCarmen.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
