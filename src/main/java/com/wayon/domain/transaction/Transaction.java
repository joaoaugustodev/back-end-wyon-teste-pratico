package com.wayon.domain.transaction;

import com.wayon.dtos.TransactionDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    public String fromAccount;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    public String toAccount;
    @NotBlank
    public double valueTransaction;
    @NotBlank
    public double moneyFee;
    @NotBlank
    public double percentFee;
    @NotBlank
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
