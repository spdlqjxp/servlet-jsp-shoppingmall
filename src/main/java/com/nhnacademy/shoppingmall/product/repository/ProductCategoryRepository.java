package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;

public interface ProductCategoryRepository {
    int save(String productId, String categoryId);
    List<Product> findAllIncludeCategory();
}
