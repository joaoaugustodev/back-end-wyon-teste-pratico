package com.wayon.services;

import com.wayon.domain.fee.Fee;
import com.wayon.domain.transaction.Transaction;
import com.wayon.dtos.TransactionDto;
import com.wayon.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    public Integer getDiffDay(LocalDate dateOne, LocalDate dateTwo) {
        return Math.toIntExact(ChronoUnit.DAYS.between(dateOne, dateTwo));
    }

    public List<Transaction> allTransactionByIdUser(Long idUser) {
        return this.repository.getAllTransactionByAccount(idUser);
    }

    public Transaction makeTransaction(TransactionDto transactionDto, Fee fee) {
        Transaction newTransaction = new Transaction(transactionDto, fee.getMoneyFee(), fee.getPercentFee());
        return this.repository.save(newTransaction);
    }
}
