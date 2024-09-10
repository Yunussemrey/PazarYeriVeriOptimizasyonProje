package com.yazilimciAkademisi.marketplace.exception;

public class SelfParentCategoryException  extends RuntimeException {
    public SelfParentCategoryException(String message) {
        super(message);
    }
}
