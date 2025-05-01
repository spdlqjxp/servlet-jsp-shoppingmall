package com.nhnacademy.shoppingmall.controller.admin;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductCategoryRepositoryImpl;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.product.utils.ProductIdAutoIncreasement;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/admin/product/register.do")
public class ProductRegisterPostController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl(), new ProductCategoryRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.debug("Product register page requested");
        String productId = ProductIdAutoIncreasement.nextId();
        String productName = req.getParameter("product_name");
        String productPrice = req.getParameter("product_price");
        String productQuantity = req.getParameter("product_quantity");
        String productDescription = req.getParameter("product_description");
        String productImage = "/resources/no-image.png";

        Product product = new Product(productId, productName, Integer.parseInt(productPrice), Integer.parseInt(productQuantity), productDescription, productImage);
        List<String> categoryIds = new ArrayList<>();

        for(String categoryId : req.getParameterValues("category_id")) {
            if (categoryId == null || categoryId.trim().isEmpty()) {
                continue; // 빈 값 스킵
            }
            categoryIds.add(categoryId.trim());
        }
        log.debug("categoryIds : {}", categoryIds);
        productService.saveProduct(product, categoryIds);
        return "redirect:/admin/product.do";
    }
}
