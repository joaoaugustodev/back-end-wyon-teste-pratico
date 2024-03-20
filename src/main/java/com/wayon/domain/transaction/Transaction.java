package com.wayon.domain.transaction;

import com.wayon.dtos.TransactionDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String fromAccount;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String toAccount;
    private double valueTransaction;
    private double moneyFee;
    private double percentFee;
    private LocalDate scheduleDateTransaction;
    private Long sender;
    private Long receiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public double getValueTransaction() {
        return valueTransaction;
    }

    public void setValueTransaction(double valueTransaction) {
        this.valueTransaction = valueTransaction;
    }

    public double getMoneyFee() {
        return moneyFee;
    }

    public void setMoneyFee(double moneyFee) {
        this.moneyFee = moneyFee;
    }

    public double getPercentFee() {
        return percentFee;
    }

    public void setPercentFee(double percentFee) {
        this.percentFee = percentFee;
    }

    public LocalDate getScheduleDateTransaction() {
        return scheduleDateTransaction;
    }

    public void setScheduleDateTransaction(LocalDate scheduleDateTransaction) {
        this.scheduleDateTransaction = scheduleDateTransaction;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public Transaction(TransactionDto transactionDto, double moneyFee, double percentFee) {
        this.fromAccount = transactionDto.fromAccount;
        this.toAccount = transactionDto.toAccount;
        this.valueTransaction = transactionDto.valueTransaction;
        this.moneyFee = moneyFee;
        this.percentFee = percentFee;
        this.scheduleDateTransaction = transactionDto.transactionDate;
        this.sender = transactionDto.sender;
        this.receiver = transactionDto.receiver;
    }
}
