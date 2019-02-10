package com.epam.training.dao;

import com.epam.training.connectionpool.ConnectionPool;
import com.epam.training.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

public class UserDao extends AbstractDao<User> {
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM USER";
    public static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM USER WHERE ID=?";
    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM USER WHERE ID=?";
    public static final String SQL_INSERT_USER = "INSERT INTO USER () VALUES()";
    private static Logger logger = Logger.getLogger(UserDao.class);

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("ID_USER"));
                user.setLogin(resultSet.getString("LOGIN"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setName(resultSet.getString("NAME"));
                user.setSurname(resultSet.getString("SURNAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setIdRole(resultSet.getInt("ID_ROLE"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = new User();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("ID_USER"));
                    user.setLogin(resultSet.getString("LOGIN"));
                    user.setPassword(resultSet.getString("PASSWORD"));
                    user.setName(resultSet.getString("NAME"));
                    user.setSurname(resultSet.getString("SURNAME"));
                    user.setEmail(resultSet.getString("EMAIL"));
                    user.setIdRole(resultSet.getInt("ID_ROLE"));
                }
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public boolean delete(User entity) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
            deleted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            deleted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }

    @Override
    public boolean insert(User entity) {
        boolean inserted = false;

        return inserted;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }
}
