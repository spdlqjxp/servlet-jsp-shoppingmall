package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product getProduct(String productId);
    List<Product> getAllProducts();
    void saveProduct(Product product, List<String> categoryIds);
    void updateProduct(Product product, List<String> categoryIds);
    void deleteProduct(String productId);
    void updateProductQuantity(String productId, int quantity);
    void pickProduct(String productId, int quantity);
    void addProduct(String productId, int quantity);
    boolean isProductExist(String productId);
    void updateProductImage(String productId, String imagePath);
}
