package com.wayon.domain.transaction;

import com.wayon.dtos.TransactionDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String fromAccount;
    public String toAccount;
    public double valueTransaction;
    public double moneyFee;
    public double percentFee;
    public LocalDate scheduleDateTransaction;

    public Transaction(TransactionDto transactionDto, double moneyFee, double percentFee) {
        this.fromAccount = transactionDto.fromAccount;
        this.toAccount = transactionDto.toAccount;
        this.valueTransaction = transactionDto.valueTransaction;
        this.moneyFee = moneyFee;
        this.percentFee = percentFee;
        this.scheduleDateTransaction = transactionDto.transactionDate;
    }
}
