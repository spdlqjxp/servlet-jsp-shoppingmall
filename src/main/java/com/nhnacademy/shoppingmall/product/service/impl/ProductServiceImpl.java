package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.product.exception.ProductQuantityNotEnoughException;
import com.nhnacademy.shoppingmall.product.repository.ProductCategoryRepository;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    public ProductServiceImpl(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public Product getProduct(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElse(null);

    }

    @Override
    public Page<Product> getAllProducts(int page, int pageSize) {
        return productCategoryRepository.findAllIncludeCategory(page, pageSize);
    }

    @Override
    public void saveProduct(Product product, List<String> categoryIds) {
        if (isProductExist(product.getProductId())) {
            log.debug("save failed");
            throw new ProductAlreadyExistsException(product.getProductId());
        }
        int result1 = productRepository.save(product);
        log.debug("product save : {}", product.getProductName());
        for(String categoryId : categoryIds) {
            int result2 = productCategoryRepository.save(product.getProductId(), categoryId.trim());
            if (result2 < 1) {
                log.debug("category save failed");
                throw new CategoryNotFoundException(categoryId);
            }
        }
        log.debug("category save : {}", categoryIds);
        if(result1 < 1) {
            log.debug("save failed");
            throw new ProductAlreadyExistsException(product.getProductId());
        }
    }

    @Override
    public void updateProduct(Product product, List<String> categoryIds) {
        if (!isProductExist(product.getProductId())) {
            log.debug("update failed");
            throw new ProductNotFoundException(product.getProductId());
        }
        int result = productRepository.update(product);
        int result2 = productCategoryRepository.delete(product.getProductId());
        if (result2 < 1) {
            log.debug("category update failed");
            throw new CategoryNotFoundException(product.getCategories().toString());
        }

        for(String categoryId : categoryIds) {
            int result3 = productCategoryRepository.save(product.getProductId(), categoryId.trim());
            if (result3 < 1) {
                log.debug("category update failed");
                throw new CategoryNotFoundException(categoryId);
            }
        }
        log.debug("category update : {}", categoryIds);
        if (result < 1) {
            log.debug("update failed");
            throw new ProductNotFoundException(product.getProductId());
        }
    }

    @Override
    public void deleteProduct(String productId) {
        if(!isProductExist(productId)) {
            log.debug("delete failed");
            throw new ProductNotFoundException(productId);
        }
        int result = productRepository.deleteByProductId(productId);
        if (result < 1) {
            log.debug("delete failed");
            throw new ProductNotFoundException(productId);
        }
    }

    @Override
    public void updateProductQuantity(String productId, int quantity) {
        if(!isProductExist(productId)) {
            log.debug("quantity update failed");
            throw new ProductNotFoundException(productId);
        }
        int result = productRepository.updateProductQuantity(productId, quantity);
        if (result < 1) {
            log.debug("quantity update failed");
            throw new ProductNotFoundException(productId);
        }
    }

    @Override
    public void pickProduct(String productId, int quantity) {
        if (!isProductExist(productId)) {
            log.debug("pick failed");
            throw new ProductNotFoundException(productId);
        }
        int totalCount = productRepository.countQuantityByProductId(productId);
        if (totalCount - quantity < 0) {
            log.debug("quantity more than total count");
            throw new ProductQuantityNotEnoughException(productId);
        }
        int result = productRepository.updateProductQuantity(productId, totalCount - quantity);
        if (result < 1) {
            log.debug("pick failed");
            throw new ProductNotFoundException(productId);
        }
    }

    @Override
    public void addProduct(String productId, int quantity) {
        if(!isProductExist(productId)) {
            log.debug("add failed");
            throw new ProductNotFoundException(productId);
        }
        int totalCount = productRepository.countQuantityByProductId(productId);
        int result = productRepository.updateProductQuantity(productId, totalCount + quantity);
        if(result < 1) {
            log.debug("add failed");
            throw new ProductNotFoundException(productId);
        }
    }

    @Override
    public boolean isProductExist(String productId) {
        int count = productRepository.countByProductId(productId);
        return count > 0;
    }

    @Override
    public void updateProductImage(String productId, String imagePath) {
        if (!isProductExist(productId)) {
            log.debug("image update failed");
            throw new ProductNotFoundException(productId);
        }
        int result = productRepository.updateProductImage(productId, imagePath);
        if (result < 1) {
            log.debug("image update failed");
            throw new ProductNotFoundException(productId);
        }
    }
}
