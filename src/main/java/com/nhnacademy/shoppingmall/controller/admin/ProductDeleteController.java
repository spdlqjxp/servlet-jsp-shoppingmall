package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.repository.ProductCategoryRepository;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductCategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/product/delete.do")
public class ProductDeleteController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl(), new ProductCategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String productId = req.getParameter("product_id");
        productService.deleteProduct(productId);
        return "redirect:/admin/product.do";
    }
}
