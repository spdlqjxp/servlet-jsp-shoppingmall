package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    List<Category> getCategoriesByProductId(String productId);
    Page<Product> getAllProductsByCategoryId(String categoryId, int page, int size);
}
