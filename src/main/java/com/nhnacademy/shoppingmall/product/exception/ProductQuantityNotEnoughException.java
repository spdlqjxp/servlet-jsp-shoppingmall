package com.nhnacademy.shoppingmall.product.exception;

public class ProductQuantityNotEnoughException extends RuntimeException {
    public ProductQuantityNotEnoughException(String productId) {
        super(String.format("product quantity not enough : %s", productId));
    }
}
