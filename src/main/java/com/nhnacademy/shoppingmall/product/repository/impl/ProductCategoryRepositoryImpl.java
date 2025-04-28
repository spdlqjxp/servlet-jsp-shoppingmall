package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductCategoryRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {
    @Override
    public int save(String productId, String categoryId) {
        String sql = """
                INSERT INTO category_product (category_id, product_id) values (?, ?)
                    """;
        Connection connection = DbConnectionThreadLocal.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoryId);
            statement.setString(2, productId);
            log.debug("sql : {}", statement);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAllIncludeCategory() {
        String sql = """
                select * from product p
                inner join category_product cp on p.product_id = cp.product_id
                inner join category c on cp.category_id = c.category_id
                """;
        Connection connection = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            Map<String, Product> productMap = new HashMap<>();
            while (rs.next()) {
                String id = rs.getString("product_id");
                Product product = productMap.get(id);
                if (Objects.isNull(product)) {
                    product = new Product(
                            rs.getString("product_id"),
                            rs.getString("product_name"),
                            rs.getInt("product_price"),
                            rs.getInt("product_quantity"),
                            rs.getString("product_description"),
                            rs.getString("product_image")
                    );
                    productMap.put(id, product);
                }
                List<Category> categories = product.getCategories();
                categories.add(new Category(
                        rs.getString("category_id"),
                        rs.getString("category_name")
                ));
            }
            return new ArrayList<>(productMap.values());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(String productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = """
                DELETE FROM category_product WHERE product_id = ? ;
                """;
        log.debug("sql : {}", sql);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productId);
            log.debug("sql : {}", statement);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
