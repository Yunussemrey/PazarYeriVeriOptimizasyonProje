package com.yazilimciAkademisi.marketplace.exception;

public class ProductNotFoundException  extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
