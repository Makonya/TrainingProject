package com.epam.training.dao;

import com.epam.training.pool.ConnectionPool;
import com.epam.training.entity.Locale;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocaleDao extends AbstractDao<Locale> {
    private static Logger logger = Logger.getLogger(LocaleDao.class);
    private static final String SQL_SELECT_LOCALE_BY_NAME = "SELECT * FROM LOCALE WHERE LOCALE_NAME=?";

    @Override
    public List<Locale> findAll() {
        return null;
    }

    @Override
    public Locale findById(int id) {
        return null;
    }

    @Override
    public boolean delete(Locale entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean insert(Locale entity) {
        return false;
    }

    @Override
    public boolean update(Locale entity) {
        return false;
    }

    public Locale findByLocaleName(String localeName){
        Locale locale = new Locale();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_LOCALE_BY_NAME)) {
            preparedStatement.setString(1, localeName);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    locale.setId(resultSet.getInt("ID_LOCALE"));
                    locale.setLocaleName(resultSet.getString("LOCALE_NAME"));
                }
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return locale;
    }
}
