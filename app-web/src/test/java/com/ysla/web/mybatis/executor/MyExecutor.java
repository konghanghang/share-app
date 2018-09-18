package com.ysla.web.mybatis.executor;

import com.ysla.web.mybatis.model.User;

import java.sql.*;

public class MyExecutor implements IMyExecutor {

    private final String URL = "jdbc:mysql://127.0.0.1:3306/test?useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "2222";

    @Override
    public <T> T query(String statement) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String sql = statement;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            return (T) user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
