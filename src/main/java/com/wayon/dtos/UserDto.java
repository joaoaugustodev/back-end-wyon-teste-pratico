package com.wayon.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDto {
    public String name;
    public String account;
    public double balance;
}
