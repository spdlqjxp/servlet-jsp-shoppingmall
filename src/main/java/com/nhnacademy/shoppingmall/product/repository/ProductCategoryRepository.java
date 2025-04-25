package com.nhnacademy.shoppingmall.product.repository;

public interface ProductCategoryRepository {
    int save(String productId, String categoryId);
}
