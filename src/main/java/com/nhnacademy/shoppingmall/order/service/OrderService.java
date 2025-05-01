package com.nhnacademy.shoppingmall.order.service;

public interface OrderService {
    void createOrder(String userId, String productId, int quantity, int price);

    void cancelOrder(String orderId);
}
