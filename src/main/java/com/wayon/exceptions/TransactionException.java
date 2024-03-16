package com.wayon.exceptions;

public class TransactionException extends RuntimeException {
    public  TransactionException() {
        super("Transferência não concluída.");
    }
}
