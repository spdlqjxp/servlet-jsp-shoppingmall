package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.order.repository.Impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.Impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/order/history.do")
public class OrderHistoryController implements BaseController {
    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl(), new ProductRepositoryImpl(), new UserRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.debug("주문 내역 조회");
        Page<Product> products = orderService.getOrderHistory("userId", 1, 10);
        req.setAttribute("orderProductList", products);

        return "shop/page/order/order_history";
    }
}
