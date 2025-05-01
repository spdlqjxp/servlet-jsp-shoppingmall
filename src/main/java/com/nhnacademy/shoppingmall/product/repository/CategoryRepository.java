package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();

    List<Category> findCategoriesByProductId(String productId);

    Page<Product> findAllByCategoryId(String categoryId, int page, int size);

    long totalCount(String productId);

}
