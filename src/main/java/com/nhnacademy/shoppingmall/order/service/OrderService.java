package com.nhnacademy.shoppingmall.order.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;

public interface OrderService {
    void createOrder(String userId, String productId, int quantity, int price);

    void cancelOrder(String orderId);
    Page<Product> getOrderHistory(String userId, int page, int size);
}
