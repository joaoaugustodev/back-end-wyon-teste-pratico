package com.wayon.domain.user;

import com.wayon.dtos.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[0-9]{10}$")
    @NotBlank
    public String account;
    @NotBlank
    public String name;
    @NotBlank
    public double balance;

    public User(UserDto userDto) {
        this.name = userDto.name;
        this.account = userDto.account;
        this.balance = userDto.balance;
    }
}
