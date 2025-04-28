package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.repository.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public List<Category> findAll() {
        String sql = """
                select * from category
                """;
        Connection connection = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            return getCategories(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> findCategoriesByProductId(String productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = """
                select c.category_id, c.category_name from category_product cp
                inner join category c on cp.category_id = c.category_id
                where cp.product_id = ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productId);
            return getCategories(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Category> getCategories(PreparedStatement statement) throws SQLException {
        ResultSet rs = statement.executeQuery();
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            String categoryId = rs.getString("category_id");
            String categoryName = rs.getString("category_name");
            Category category = new Category(categoryId, categoryName);
            categories.add(category);
        }
        return categories;
    }
}
