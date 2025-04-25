package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public Optional<Product> findById(String productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = """
                select * from product where product_id=?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Product product = new Product(
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("product_price"),
                        rs.getInt("product_quantity"),
                        rs.getString("product_description"),
                        rs.getString("product_image")
                );
                return Optional.of(product);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = """
                insert into product(product_id, product_name, product_price, product_quantity, product_description, product_image)
                values (?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductId());
            statement.setString(2, product.getProductName());
            statement.setInt(3, product.getPrice());
            statement.setInt(4, product.getProductQuantity());
            statement.setString(5, product.getProductDescription());
            statement.setString(6, product.getProductImage());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByProductId(String productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = """
                delete from product where product_id=?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = """
                update product set product_name=?, product_price=?, product_quantity=?, product_description=?, product_image=?
                where product_id=?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getPrice());
            statement.setInt(3, product.getProductQuantity());
            statement.setString(4, product.getProductDescription());
            statement.setString(5, product.getProductImage());
            statement.setString(6, product.getProductId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateProductQuantity(String productId, int quantity) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = """
                update product set product_quantity=product_quantity+? where product_id=?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setString(2, productId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByProductId(String productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = """
                select count(*) from product where product_id=?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
