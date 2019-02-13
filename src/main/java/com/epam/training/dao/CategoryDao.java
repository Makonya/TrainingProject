package com.epam.training.dao;

import com.epam.training.connectionpool.ConnectionPool;
import com.epam.training.entity.Category;
import com.epam.training.entity.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends AbstractDao<Category> {
    private static Logger logger = Logger.getLogger(CategoryDao.class);
    private static final String SQL_SELECT_CATEGORY_BY_LOCALE_ID = "SELECT * FROM CATEGORY WHERE ID_LOCALE=?";

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
            logger.error("Errors occurred while accessing the category table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return categories;
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
}
