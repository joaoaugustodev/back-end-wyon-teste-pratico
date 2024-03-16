package com.wayon.exceptions;

public class NoBalanceException extends RuntimeException {
    public NoBalanceException() {
        super("Sem saldo suficiente ou valor de tranferência menor que o valor da taxa.");
    }
}
