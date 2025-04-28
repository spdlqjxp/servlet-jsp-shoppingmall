package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product getProduct(String productId);
    List<Product> getAllProducts();
    void saveProduct(Product product, String categoryId);
    void updateProduct(Product product);
    void deleteProduct(String productId);
    void updateProductQuantity(String productId, int quantity);
    void pickProduct(String productId, int quantity);
    void addProduct(String productId, int quantity);
    boolean isProductExist(String productId);
}
