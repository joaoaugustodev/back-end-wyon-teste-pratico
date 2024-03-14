package com.wayon.domain.transaction;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "transactions")
@Table(name = "transactions")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromAccount;
    private String toAccount;
    private double value;
    private LocalDate scheduleDateTransaction;
}
