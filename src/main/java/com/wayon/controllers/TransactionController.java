package com.wayon.controllers;

import com.wayon.domain.fee.Fee;
import com.wayon.domain.transaction.Transaction;
import com.wayon.domain.user.User;
import com.wayon.dtos.TransactionDto;
import com.wayon.exceptions.NoBalanceException;
import com.wayon.exceptions.TransactionException;
import com.wayon.services.FeeService;
import com.wayon.services.TransactionService;
import com.wayon.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    public TransactionService transactionService;
    @Autowired
    public FeeService feeService;
    @Autowired
    public UserService userService;

    @GetMapping("/{idUser}")
    public ResponseEntity<List<Transaction>> getAllTransactionById(@PathVariable @Valid Long idUser) {
        List<Transaction> transactions = this.transactionService.allTransactionByIdUser(idUser);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody @Valid TransactionDto transactionDto) {
        Integer diff = transactionService.getDiffDay(LocalDate.now(), transactionDto.transactionDate);
        Fee fee = feeService.getFeeFromDiffDay(diff);

        User sender = userService.getUserByAccount(transactionDto.fromAccount);
        User receiver = userService.getUserByAccount(transactionDto.toAccount);

        boolean hasBalance = sender.getBalance() >= transactionDto.valueTransaction;
        boolean isReceiverSender = transactionDto.fromAccount.equals(transactionDto.toAccount);

        if (fee == null || isReceiverSender) {
            throw new TransactionException();
        }

        boolean isLessThanMoneyFee = transactionDto.valueTransaction > fee.getMoneyFee();

        if (!hasBalance || !isLessThanMoneyFee) {
            throw new NoBalanceException();
        }

        double receiverBalance = transactionDto.valueTransaction * (fee.getPercentFee() / 100) + fee.getMoneyFee();
        sender.setBalance(sender.getBalance() - transactionDto.valueTransaction);
        receiver.setBalance(receiver.getBalance() + (transactionDto.valueTransaction - receiverBalance));
        transactionDto.receiver = receiver.getId();

        userService.updateUser(sender);
        userService.updateUser(receiver);

        Transaction response = transactionService.makeTransaction(transactionDto, fee);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
