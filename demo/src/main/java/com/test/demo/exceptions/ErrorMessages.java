package com.test.demo.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product not found"),
    PRODUCT_NOT_VALID("Product not valid"),
    PRODUCT_NAME_NOT_VALID("Product name not valid"),
    PRODUCT_DESCRIPTION_NOT_VALID("Product description not valid"),
    PRODUCT_PRICE_NOT_VALID("Product price not valid"),;


    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }
}
