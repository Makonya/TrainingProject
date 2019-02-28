package com.epam.training.dao;

import com.epam.training.pool.ConnectionPool;
import com.epam.training.entity.Locale;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LocaleDao extends AbstractDao<Locale> {
    private static Logger logger = Logger.getLogger(LocaleDao.class);
    private static final String SQL_SELECT_LOCALE_BY_NAME = "SELECT * FROM LOCALE WHERE LOCALE_NAME=?";
    private static final String SQL_SELECT_ALL_LOCALES = "SELECT * FROM LOCALE";

    @Override
    public List<Locale> findAll() {
        List<Locale> locales = new LinkedList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_LOCALES)) {
            while (resultSet.next()) {
                Locale locale = new Locale();
                locale.setId(resultSet.getInt("ID_LOCALE"));
                locale.setLocaleName(resultSet.getString("LOCALE_NAME"));
                locales.add(locale);
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return locales;
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
        Locale locale = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_LOCALE_BY_NAME)) {
            preparedStatement.setString(1, localeName);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    locale = new Locale();
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
