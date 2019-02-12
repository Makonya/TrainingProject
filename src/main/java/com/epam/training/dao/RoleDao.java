package com.epam.training.dao;

import com.epam.training.connectionpool.ConnectionPool;
import com.epam.training.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.*;

public class RoleDao extends AbstractDao<Role> {
    private static final String SQL_SELECT_ALL_ROLES = "SELECT * FROM ROLE";
    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT * FROM ROLE WHERE ID_ROLE=?";
    private static final String SQL_INSERT_ROLE = "INSERT INTO ROLE VALUES(ID_ROLE, ?)";
    private Logger logger = Logger.getLogger(RoleDao.class);

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ROLES)) {
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("ID_ROLE"));
                role.setName(resultSet.getString("NAME"));
                roles.add(role);
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the role table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return roles;
    }

    @Override
    public Role findById(int id) {
        Role role = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ROLE_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    role = getRoleParameters(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return role;
    }

    @Override
    public boolean delete(Role entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean insert(Role entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ROLE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                resultSet.next();
                entity.setId(resultSet.getInt(1));
            }
            logger.info("Created new role where id = " + entity.getId());
            inserted = true;
        } catch (SQLException e) {
            logger.error("New role wasn't inserted to db. " + e);
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public boolean update(Role entity) {
        throw new UnsupportedOperationException();
    }

    private Role getRoleParameters(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt("ID_ROLE"));
        role.setName(resultSet.getString("NAME"));
        return role;
    }
}
