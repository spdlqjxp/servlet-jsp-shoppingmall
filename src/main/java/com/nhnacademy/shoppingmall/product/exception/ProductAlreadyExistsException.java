package com.nhnacademy.shoppingmall.product.exception;

public class ProductAlreadyExistsException extends Exception {
    public ProductAlreadyExistsException(String productId) {
        super(String.format("product already exist : %s ", productId));
    }
}
