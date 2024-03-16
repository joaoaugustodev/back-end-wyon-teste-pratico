package com.wayon.infra;

import org.springframework.http.HttpStatus;

public class CustomErrorMessage {
    private HttpStatus status;
    private String message;

    public CustomErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
