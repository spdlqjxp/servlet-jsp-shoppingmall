package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.util.DbUtils;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        /*todo#3-1 회원의 아이디와 비밀번호를 이용해서 조회하는 코드 입니다.(로그인)
          해당 코드는 SQL Injection이 발생합니다. SQL Injection이 발생하지 않도록 수정하세요.
         */
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at from users where user_id=? and user_password =?";

        log.debug("sql:{}", sql);
        ResultSet rs = null;
        try (PreparedStatement psmt = connection.prepareStatement(sql);
        ) {
            psmt.setString(1, userId);
            psmt.setString(2, userPassword);
            rs = psmt.executeQuery();
            return findUser(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(String userId) {
        //todo#3-2 회원조회

        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql ="select user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at from users where user_id=?";

        log.debug("sql:{}",sql);
        ResultSet rs = null;
        try( PreparedStatement psmt = connection.prepareStatement(sql);
        ) {
            psmt.setString(1, userId);
            rs = psmt.executeQuery();
            return findUser(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<User> findUser(ResultSet rs) {
        try {
            if (rs.next()) {
                User user = new User(
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        rs.getString("user_birth"),
                        User.Auth.valueOf(rs.getString("user_auth")),
                        rs.getInt("user_point"),
                        Objects.nonNull(rs.getTimestamp("created_at")) ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                        Objects.nonNull(rs.getTimestamp("latest_login_at")) ? rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }



    @Override
    public int save(User user) {
        //todo#3-3 회원등록, executeUpdate()을 반환합니다.
        String sql = "insert into users values (?,?,?,?,?,?,?,?)";
        Connection connection = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getUserPassword());
            statement.setString(4, user.getUserBirth());
            statement.setString(5, String.valueOf(user.getUserAuth()));
            statement.setInt(6, user.getUserPoint());
            statement.setTimestamp(7, user.getCreatedAt() != null ? Timestamp.valueOf(user.getCreatedAt()) : null);
            statement.setTimestamp(8, user.getLatestLoginAt() != null ? Timestamp.valueOf(user.getLatestLoginAt()) : null);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        //todo#3-4 회원삭제, executeUpdate()을 반환합니다.
        String sql = "delete from users where user_id=?";
        Connection connection = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        //todo#3-5 회원수정, executeUpdate()을 반환합니다.
        String sql = "update users set user_name=?, user_password=?, user_birth=?, user_auth=?, user_point=? where user_id=?";
        Connection connection = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserPassword());
            statement.setString(3, user.getUserBirth());
            statement.setString(4, String.valueOf(user.getUserAuth()));
            statement.setInt(5, user.getUserPoint());
            statement.setString(6, user.getUserId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {
        //todo#3-6, 마지막 로그인 시간 업데이트, executeUpdate()을 반환합니다.
        String sql = "update users set latest_login_at=? where user_id=?";
        Connection connection = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, Timestamp.valueOf(latestLoginAt));
            statement.setString(2, userId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.
        String sql = "select count(*) from users where user_id=?";
        Connection connection = DbConnectionThreadLocal.getConnection();
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateUserPoint(String userId, int point) {
        String sql = "update users set user_point= + ? where user_id=?";
        Connection connection = DbConnectionThreadLocal.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, point);
            statement.setString(2, userId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
