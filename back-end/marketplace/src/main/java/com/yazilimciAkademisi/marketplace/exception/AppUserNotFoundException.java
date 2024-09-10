package com.yazilimciAkademisi.marketplace.exception;

public class AppUserNotFoundException   extends RuntimeException {
    public AppUserNotFoundException(String message) {
        super(message);
    }
}
