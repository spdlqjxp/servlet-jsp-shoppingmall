package com.nhnacademy.shoppingmall.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productId) {
        super(String.format("product not found : %s", productId));
    }
}
