package com.wayon.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDto {
    public String fromAccount;
    public String toAccount;
    public double valueTransaction;
    public LocalDate transactionDate;
}
