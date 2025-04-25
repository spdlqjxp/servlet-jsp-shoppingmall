package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.repository.ProductCategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {
    @Override
    public int save(String productId, String categoryId) {
        String sql = """
                INSERT INTO category_product values (?, ?)
                    """;
        try(Connection connection = DbConnectionThreadLocal.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productId);
            statement.setString(2, categoryId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
