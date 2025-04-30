package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;

import com.nhnacademy.shoppingmall.common.page.Page;
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
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
@Slf4j
@RequestMapping(method = RequestMapping.Method.GET,value = {"/index.do"})
public class IndexController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl(), new ProductCategoryRepositoryImpl());
    private final CategoryService categoryService = new CategoryServiceImpl(new CategoryRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        int size = req.getParameter("size") == null ? 9 : Integer.parseInt(req.getParameter("size"));

        log.info("page: {}, size: {}", page, size);

        String categoryId = req.getParameter("category_id");
        Page<Product> productPage;
        if (categoryId == null) {
            productPage = productService.getAllProducts(page, size);

        } else {
            productPage = categoryService.getAllProductsByCategoryId(categoryId, page, size);
        }
        req.setAttribute("productPage", productPage);
        req.setAttribute("page", page);
        req.setAttribute("size", size);

        List<Category> categoryList = categoryService.getAllCategories();
        req.setAttribute("categoryList", categoryList);
        return "shop/main/index";
    }
}