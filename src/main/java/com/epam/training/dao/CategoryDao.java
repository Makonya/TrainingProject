package com.epam.training.dao;

import com.epam.training.pool.ConnectionPool;
import com.epam.training.entity.Category;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends AbstractDao<Category> {
    private static final Logger LOGGER = Logger.getLogger(CategoryDao.class);
    private static final String SQL_SELECT_CATEGORY_BY_LOCALE_ID = "SELECT * FROM CATEGORY WHERE ID_LOCALE=?";
    private static final String SQL_SELECT_CATEGORY_BY_NAME = "SELECT * FROM CATEGORY WHERE CATEGORY_NAME=?";
    private static final String SQL_INSERT_CATEGORY = "INSERT INTO CATEGORY(ID_LOCALE, CATEGORY_NAME, ID_CATEGORY) VALUES(?, ?, ?)";
    private static final String SQL_SELECT_LAST_CATEGORY_ID = "SELECT MAX(ID_CATEGORY) AS ID_CATEGORY FROM category;";

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    public List<Category> findAllByIdLocale(int idLocale) {
        List<Category> categories = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CATEGORY_BY_LOCALE_ID)) {
            preparedStatement.setInt(1, idLocale);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Category category = new Category();
                    category.setId(resultSet.getInt("ID_CATEGORY"));
                    category.setCategoryName(resultSet.getString("CATEGORY_NAME"));
                    category.setIdLocale(resultSet.getInt("ID_LOCALE"));
                    categories.add(category);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the category table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return categories;
    }

    public Category findAllByName(String categoryName) {
        Category category = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CATEGORY_BY_NAME)) {
            preparedStatement.setString(1, categoryName);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    category = new Category();
                    category.setId(resultSet.getInt("ID_CATEGORY"));
                    category.setCategoryName(resultSet.getString("CATEGORY_NAME"));
                    category.setIdLocale(resultSet.getInt("ID_LOCALE"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the category table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return category;
    }

    @Override
    public boolean delete(Category entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean insert(Category entity) {
        return false;
    }

    @Override
    public boolean update(Category entity) {
        return false;
    }

    public boolean insert(List<Category> categories) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_CATEGORY)) {
            connection.setAutoCommit(false);
            for (Category category : categories) {
                preparedStatement.setInt(1, category.getIdLocale());
                preparedStatement.setString(2, category.getCategoryName());
                preparedStatement.setInt(3, category.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            LOGGER.info("Created new category record");
            inserted = true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error("Errors occurred while rollback process." + e1.getMessage());
            }
            LOGGER.error("New category was not inserted to db. " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    public int getLastId(){
        int id = 0;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_LAST_CATEGORY_ID)) {
            while (resultSet.next()) {
                id = resultSet.getInt("ID_CATEGORY");
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the category table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return id;
    }
}
