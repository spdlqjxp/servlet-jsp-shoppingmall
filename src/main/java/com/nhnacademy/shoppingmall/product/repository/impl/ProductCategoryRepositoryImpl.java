package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;
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
    public Page<Product> findAllIncludeCategory(int page, int pageSize) {
        String sql = """
                select distinct p.product_id,
                                p.product_name,
                                p.product_price,
                                p.product_quantity,
                                p.product_description,
                                p.product_image,
                                GROUP_CONCAT(c.category_name) as category_name,
                                GROUP_CONCAT(c.category_id) as category_id
                from product p
                inner join category_product cp on p.product_id = cp.product_id
                inner join category c on cp.category_id = c.category_id
                group by p.product_id
                order by cast(substring(p.product_id, 2) as unsigned) desc
                limit ?, ?;
             
                """;
        int offset = (page - 1) * pageSize;
        int limit = pageSize;
        Connection connection = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, offset);
            statement.setInt(2, limit);

            ResultSet rs = statement.executeQuery();
            Map<String, Product> productMap = new LinkedHashMap<>();
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
                String[] categoryIds = rs.getString("category_id").split(",");
                String[] categoryNames = rs.getString("category_name").split(",");
                for (int i = 0; i < categoryIds.length; i++) {
                    categories.add(new Category(
                            categoryIds[i],
                            categoryNames[i]
                    ));
                }
            }
            long totalCount = 0;
            if (!productMap.isEmpty()) {
                totalCount = totalCount();
            }
            return new Page<>(new ArrayList<>(productMap.values()), totalCount);
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

    @Override
    public long totalCount() {
        String sql = """
                SELECT COUNT(*) FROM product;
                """;
        Connection connection = DbConnectionThreadLocal.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public Page<Product> findByKeyword(String keyword, int page, int pageSize) {
        String sql = """
                select * from product p
                inner join category_product cp on p.product_id = cp.product_id
                inner join category c on cp.category_id = c.category_id
                where p.product_name like ? or c.category_name like ?
                group by p.product_id
                order by cast(substring(p.product_id, 2) as unsigned) desc
                limit ?, ?;
                """;
        Connection connection = DbConnectionThreadLocal.getConnection();
        int offset = (page - 1) * pageSize;
        int limit = pageSize;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setInt(3, offset);
            statement.setInt(4, limit);
            ResultSet rs = statement.executeQuery();
            Map<String, Product> productMap = new LinkedHashMap<>();
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
            long totalCount = 0;
            if (!productMap.isEmpty()) {
                totalCount = totalCountWithKeyword(keyword);
            }
            return new Page<>(new ArrayList<>(productMap.values()), totalCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public long totalCountWithKeyword(String keyword) {
        String sql = """
                select count(*) from product p
                inner join category_product cp on p.product_id = cp.product_id
                inner join category c on cp.category_id = c.category_id
                where p.product_name like ? or c.category_name like ?
                """;
        Connection connection = DbConnectionThreadLocal.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
