package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;

public interface ProductCategoryRepository {
    int save(String productId, String categoryId);
    Page<Product> findAllIncludeCategory(int page, int pageSize);
    int delete(String productId);
    long totalCount();
    Page<Product> findByKeyword(String keyword, int page, int pageSize);
}
