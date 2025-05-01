package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Page<Product> findAllByCategoryId(String categoryId, int page, int size) {
        String sql = """
                select distinct * from product p
                inner join category_product cp on p.product_id=cp.product_id
                inner join category c on cp.category_id = c.category_id
                where cp.category_id = ?
                order by cast(substring(p.product_id, 2) as unsigned) desc
                limit ?, ?
                """;
        Connection connection = DbConnectionThreadLocal.getConnection();
        int offset = (page - 1) * size;
        int limit = size;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoryId);
            statement.setInt(2, offset);
            statement.setInt(3, limit);
            ResultSet rs = statement.executeQuery();
            Map<String, Product> productMap = new LinkedHashMap<>();
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("product_price"),
                        rs.getInt("product_quantity"),
                        rs.getString("product_description"),
                        rs.getString("product_image")
                );
                productMap.put(product.getProductId(), product);
                List<Category> categories = product.getCategories();
                categories.add(new Category(
                        rs.getString("category_id"),
                        rs.getString("category_name")
                ));
            }
            long totalCount = 0;
        if(!productMap.isEmpty()) {
            totalCount = totalCount(categoryId);
        }
            return new Page<>(new ArrayList<>(productMap.values()), totalCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long totalCount(String categoryId) {
        String sql = """
                select distinct count(*) from product p
                inner join category_product cp on p.product_id=cp.product_id
                inner join category c on cp.category_id = c.category_id
                where cp.category_id = ?
                """;
        Connection connection = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoryId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
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
