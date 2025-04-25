package com.nhnacademy.shoppingmall.product.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String productId) {
        super(String.format("product not found : %s", productId));
    }
}
