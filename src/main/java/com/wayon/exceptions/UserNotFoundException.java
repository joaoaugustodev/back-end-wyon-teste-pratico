package com.wayon.exceptions;

public class UserNotFoundException  extends RuntimeException {
    public  UserNotFoundException() {
        super("Usuário não encontrado.");
    }
}
