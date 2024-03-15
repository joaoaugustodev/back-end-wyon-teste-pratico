package com.wayon.domain.user;

import com.wayon.dtos.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(unique = true)
    public String account;
    public String name;
    public double balance;

    public User(UserDto userDto) {
        this.name = userDto.name;
        this.account = userDto.account;
        this.balance = userDto.balance;
    }
}
