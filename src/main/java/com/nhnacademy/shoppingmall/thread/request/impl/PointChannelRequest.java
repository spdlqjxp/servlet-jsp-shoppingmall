package com.nhnacademy.shoppingmall.thread.request.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class PointChannelRequest extends ChannelRequest {

    private final String userId;
    private final int point;
    public PointChannelRequest(String userId, int point) {
        this.userId = userId;
        this.point = point;
    }
    @Override
    public void execute() {
        DbConnectionThreadLocal.initialize();
        //todo#14-5 포인트 적립구현, connection은 point적립이 완료되면 반납합니다.
        log.debug("pointChannel execute");

        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = """
                update users set user_point = user_point + ?
                where user_id = ?
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, point);
            statement.setString(2, userId);

            int result = statement.executeUpdate();
            if (result < 1) {
                throw new RuntimeException("포인트 적립 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DbConnectionThreadLocal.reset();
        }
    }
}
