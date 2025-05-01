package com.nhnacademy.shoppingmall.order.service.Impl;

import com.nhnacademy.shoppingmall.order.exception.PointNotEnoughException;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.order.utils.OrderIdAutoIncreasement;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
    @Override
    public void createOrder(String userId, String productId, int quantity, int price) {
        String orderId = OrderIdAutoIncreasement.nextId();
        orderRepository.saveOrder(orderId, userId);
        orderRepository.saveOrderProducts(orderId, productId, quantity);
//        주문 -> 포인트 차감
        price = -price;
        int result = userRepository.updateUserPoint(userId, price);
        if (result < 1) {
            throw new PointNotEnoughException("포인트가 부족합니다.");
        }
        log.debug("주문 완료 : {}", orderId);
    }

    @Override
    public void cancelOrder(String orderId) {

    }
}
