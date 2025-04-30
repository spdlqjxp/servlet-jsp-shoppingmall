package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.CategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductCategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.CategoryServiceImpl;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/product/view.do")
public class ProductViewController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl(), new ProductCategoryRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String productId = req.getParameter("product_id");
        if (productId == null || productId.isEmpty()) {
            return "redirect:/index.do";
        }
        Product product = productService.getProduct(productId);
        List<Category> categories = categoryService.getCategoriesByProductId(productId);

        product.getCategories().addAll(categories);
        log.debug("product categories : {}", product.getCategories().toArray());

        req.setAttribute("product", product);

        HttpSession session = req.getSession();

        Queue<Product> recentProducts = (Queue<Product>) session.getAttribute("recentProducts");
        if(recentProducts == null) {
            recentProducts = new ArrayDeque<>();
        }
        if(recentProducts.size() >= 5) {
            recentProducts.poll();
        }
        if(!recentProducts.contains(product)) {
            recentProducts.add(product);
        }
        session.setAttribute("recentProducts", recentProducts);

        return "shop/main/product_view";
    }
}
