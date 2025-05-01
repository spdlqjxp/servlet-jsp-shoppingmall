package com.nhnacademy.shoppingmall.controller.order;

import com.nhnacademy.shoppingmall.cart.Cart;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.order.repository.Impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.Impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductCategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.request.impl.PointChannelRequest;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/order.do")
public class OrderPostController implements BaseController {
    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl(), new ProductRepositoryImpl(), new UserRepositoryImpl());
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl(), new ProductCategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();

        List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
        if (cartList == null || cartList.isEmpty()) {
            return "redirect:/mypage/cart.do";
        }
        RequestChannel requestChannel = (RequestChannel) req.getServletContext().getAttribute("requestChannel");
        for (Cart cart : cartList) {
            String productId = cart.getProductId();
            int quantity = cart.getQuantity();
            Product product = productService.getProduct(productId);
            int price = product.getPrice() * quantity;
            orderService.createOrder(user.getUserId(), productId, quantity, price);

            try {
                requestChannel.addRequest(new PointChannelRequest(userId, price / 10));
            } catch (InterruptedException e) {
                log.error("포인트 적립 실패");
                throw new RuntimeException(e);
            }
        }
        user.setUserPoint(user.getUserPoint() - cartList.stream().mapToInt(cart -> productService.getProduct(cart.getProductId()).getPrice() * cart.getQuantity()).sum());
        session.setAttribute("user", user);
        cartList.clear();
        session.setAttribute("cartList", cartList);


        return "shop/page/order/order_complete";
    }
}
