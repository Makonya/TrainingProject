package com.epam.training.dao;

import com.epam.training.connectionpool.ConnectionPool;
import com.epam.training.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

public class UserDao extends AbstractDao<User> {
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM USER";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM USER WHERE ID_USER=?";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM USER WHERE LOGIN=?";
    private static final String SQL_SELECT_USER_BY_LOGIN_PASSWORD = "SELECT * FROM USER WHERE LOGIN=? AND PASSWORD=?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM USER WHERE ID=?";
    private static final String SQL_INSERT_USER = "INSERT INTO USER(ID_USER,LOGIN,PASSWORD,NAME,SURNAME,EMAIL,ID_ROLE) VALUES(ID_USER, ?,?,?,?,?,?)";
    private static final String SQL_UPDATE_USER = "UPDATE USER SET LOGIN = ?, PASSWORD = ?, NAME = ?, SURNAME = ?, EMAIL = ?, ID_ROLE = ? WHERE ID_USER = ?";
    private static Logger logger = Logger.getLogger(UserDao.class);

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS)) {
            while (resultSet.next()) {
                users.add(getUserParameters(resultSet));
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
        User user = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    user = getUserParameters(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    public boolean findByLogin(String login) {
        boolean exist = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return exist;
    }

    @Override
    public boolean delete(User entity) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
            deleted = true;
            logger.info("User was deleted. USer ID = " + entity.getId());
        } catch (SQLException e) {
            logger.error("User wasn't deleted. " + e);
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
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
            logger.info("User was deleted. USer ID = " + id);
        } catch (SQLException e) {
            logger.error("User wasn't deleted. " + e);
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return deleted;
    }

    @Override
    public boolean insert(User entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setUserParameters(preparedStatement, entity).executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                resultSet.next();
                entity.setId(resultSet.getInt(1));
            }
            logger.info("Created new user where id = " + entity.getId());
            inserted = true;
        } catch (SQLException e) {
            logger.error("New user wasn't inserted to db. " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public boolean update(User entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            preparedStatement.setInt(7,entity.getId());
            setUserParameters(preparedStatement, entity).executeUpdate();
            updated = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return updated;
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    user = getUserParameters(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    private PreparedStatement setUserParameters(PreparedStatement preparedStatement, User entity) throws SQLException{
        preparedStatement.setString(1,entity.getLogin());
        preparedStatement.setString(2,entity.getPassword());
        preparedStatement.setString(3,entity.getName());
        preparedStatement.setString(4,entity.getSurname());
        preparedStatement.setString(5,entity.getEmail());
        preparedStatement.setInt(6,entity.getIdRole());
        return preparedStatement;
    }

    private User getUserParameters(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("ID_USER"));
        user.setLogin(resultSet.getString("LOGIN"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setName(resultSet.getString("NAME"));
        user.setSurname(resultSet.getString("SURNAME"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setIdRole(resultSet.getInt("ID_ROLE"));
        return user;
    }
}
