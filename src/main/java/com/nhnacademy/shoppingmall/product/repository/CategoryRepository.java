package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.product.domain.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();

    List<Category> findCategoriesByProductId(String productId);
}
