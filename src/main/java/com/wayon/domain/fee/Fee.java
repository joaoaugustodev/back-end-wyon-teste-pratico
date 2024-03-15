package com.wayon.domain.fee;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fees")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Integer fromDate;
    public Integer toDate;
    public double moneyFee;
    public double percentFee;
}
