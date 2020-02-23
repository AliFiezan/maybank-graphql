package com.alifiezan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alifiezan.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
 
}
