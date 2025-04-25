package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(String productId);

    int save(Product product);

    int deleteByProductId(String productId);

    int update(Product product);

    int updateProductQuantity(String productId, int quantity);

    int countByProductId(String productId);
    int countQuantityByProductId(String productId);
}
