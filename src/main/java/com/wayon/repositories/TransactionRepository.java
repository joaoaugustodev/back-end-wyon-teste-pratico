package com.wayon.repositories;

import com.wayon.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM transactions WHERE receiver = ?1 OR sender = ?1", nativeQuery = true)
    List<Transaction> getAllTransactionByAccount(Long idUser);
}
