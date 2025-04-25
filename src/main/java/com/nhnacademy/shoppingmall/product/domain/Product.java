package com.nhnacademy.shoppingmall.product.domain;

import java.util.Objects;

public class Product {
    private String productId;
    private String productName;
    private int price;
    private int productQuantity;
    private String productDescription = null;
    private String productImage = null;

    public Product(String productId, String productName, int price, int productQuantity, String productDescription, String productImage) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.productQuantity = productQuantity;
        this.productDescription = productDescription;
        this.productImage = productImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(productId, product.getProductId()) &&
                Objects.equals(productName, product.getProductName()) &&
                productQuantity == product.getProductQuantity() &&
                price == product.getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productQuantity, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", productQuantity=" + productQuantity +
                '}';
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
