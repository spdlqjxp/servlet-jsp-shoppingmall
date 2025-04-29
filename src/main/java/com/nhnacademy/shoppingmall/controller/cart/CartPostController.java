package com.nhnacademy.shoppingmall.controller.cart;

import com.nhnacademy.shoppingmall.cart.Cart;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductCategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/cart.do")
public class CartPostController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl(), new ProductCategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        if(session.getAttribute("user") == null) {
            return "redirect:/login.do";
        }
        String productId = req.getParameter("product_id");
        String productCount = req.getParameter("product_count");

        List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");


        boolean productExists = false;
        for (Cart cart : cartList) {
            if (cart.getProductId().equals(productId)) {

                cart.setQuantity(cart.getQuantity() + Integer.parseInt(productCount));
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            cartList.add(new Cart(productId, Integer.parseInt(productCount)));
        }
        productService.pickProduct(productId, Integer.parseInt(productCount));

        session.setAttribute("cartList", cartList);

        return "redirect:/mypage/cart.do";
    }
}
