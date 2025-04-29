package com.nhnacademy.shoppingmall.cart;

public class Cart {
    private String productId;
    private int quantity;

    public Cart(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }
}
