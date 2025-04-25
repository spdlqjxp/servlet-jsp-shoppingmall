package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.product.exception.ProductQuantityNotEnoughException;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElse(null);

    }

    @Override
    public void saveProduct(Product product) {
        if (isProductExist(product.getProductId())) {
            log.debug("save failed");
            throw new ProductAlreadyExistsException(product.getProductId());
        }
        int result = productRepository.save(product);
        if(result < 1) {
            log.debug("save failed");
            throw new ProductAlreadyExistsException(product.getProductId());
        }
    }

    @Override
    public void updateProduct(Product product) {
        if (!isProductExist(product.getProductId())) {
            log.debug("update failed");
            throw new ProductNotFoundException(product.getProductId());
        }
        int result = productRepository.update(product);
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
}
