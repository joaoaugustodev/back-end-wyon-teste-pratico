package com.wayon.controllers;

import com.wayon.domain.fee.Fee;
import com.wayon.domain.transaction.Transaction;
import com.wayon.domain.user.User;
import com.wayon.dtos.TransactionDto;
import com.wayon.services.FeeService;
import com.wayon.services.TransactionService;
import com.wayon.services.UserService;
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

    @GetMapping("/{account}")
    public ResponseEntity<List<Transaction>> getAllTransactionByAccount(@PathVariable String account) {
        List<Transaction> transactions = this.transactionService.allTransactionByAccount(account);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody TransactionDto transactionDto) throws Exception {
        Integer diff = transactionService.getDiffDay(LocalDate.now(), transactionDto.transactionDate);
        Fee fee = feeService.getFeeFromDiffDay(diff);
        User sender = userService.getUserByAccount(transactionDto.fromAccount);
        User receiver = userService.getUserByAccount(transactionDto.toAccount);
        boolean hasBalance = sender.balance >= transactionDto.valueTransaction;
        boolean isReceiverSender = transactionDto.fromAccount.equals(transactionDto.toAccount);

        if (fee == null || isReceiverSender) {
            throw new Exception("Transferência não concluída.");
        }

        if (!hasBalance || !(transactionDto.valueTransaction > fee.moneyFee)) {
            throw new Exception("Sem saldo suficiente ou valor de tranferência menor que o valor da taxa.");
        }

        double receiverBalance = transactionDto.valueTransaction * (fee.percentFee / 100) + fee.moneyFee;
        sender.balance = sender.balance - transactionDto.valueTransaction;
        receiver.balance = receiver.balance + (transactionDto.valueTransaction - receiverBalance);
        userService.updateUser(sender);
        userService.updateUser(receiver);
        Transaction response = transactionService.makeTransaction(transactionDto, fee);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
