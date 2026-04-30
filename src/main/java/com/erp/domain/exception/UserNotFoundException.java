package com.erp.domain.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("Usuario no encontrado: " + email);
    }
}
