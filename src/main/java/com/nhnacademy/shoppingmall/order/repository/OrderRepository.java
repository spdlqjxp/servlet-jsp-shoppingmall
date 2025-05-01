package com.nhnacademy.shoppingmall.order.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;

public interface OrderRepository {
    int saveOrder(String orderId, String userId);
    int saveOrderProducts(String orderId, String productId, int quantity);

    Page<Product> findOrderProductsByUserId(String userId);
}
