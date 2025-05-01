package com.nhnacademy.shoppingmall.order.repository.Impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.product.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public int saveOrder(String orderId, String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = """
                INSERT INTO nhn_academy_16.order (order_id, user_id, order_created_at)
                VALUES (?, ?, now())
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, orderId);
            statement.setString(2, userId);

            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int saveOrderProducts(String orderId, String productId, int quantity) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = """
                INSERT INTO order_items (order_id, product_id, order_quantity)
                VALUES (?, ?, ?)
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, orderId);
            statement.setString(2, productId);
            statement.setInt(3, quantity);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<Product> findOrderProductsByUserId(String userId, int page, int size) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = """
                select p.product_id, p.product_name, p.product_price, oi.order_quantity
                 from order_items oi
                 inner join nhn_academy_16.order o on oi.order_id = o.order_id
                 inner join product p on oi.product_id = p.product_id
                 where o.user_id = ?
                 order by o.order_created_at desc
                 limit ?, ?;
                 """;
        int offset = (page - 1) * size;
        int limit = size;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            statement.setInt(2, offset);
            statement.setInt(3, limit);
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("product_price"),
                        rs.getInt("order_quantity"),
                        "", ""
                );
                products.add(product);
            }
            long totalCount = 0;
            if (!products.isEmpty()) {
                totalCount = totalCount(userId);
            }
            return new Page<>(products, totalCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long totalCount(String userId) {
        String sql = """
                 select count(*)
                 from order_items oi
                 inner join nhn_academy_16.order o on oi.order_id = o.order_id
                 inner join product p on oi.product_id = p.product_id
                 where o.user_id = ?
                """;
        Connection connection = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
