package com.epam.training.dao;

import com.epam.training.entity.Mark;
import com.epam.training.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MarkDao extends AbstractDao<Mark> {
    private static Logger logger = Logger.getLogger(MarkDao.class);
    private static String SQL_INSERT_MARKS = "INSERT INTO MARK(MARK, ID_COURSE, ID_USER) VALUES(?, ?, ?)";
    private static String SQL_SELECT_BY_COURSE_USER_ID = "SELECT * FROM MARK WHERE ID_COURSE=? AND ID_USER=?";
    private static final String SQL_UPDATE_MARK = "UPDATE MARK SET MARK = ? WHERE ID_COURSE = ? AND ID_USER = ?";


    @Override
    public List<Mark> findAll() {
        return null;
    }

    public boolean findByCourseUserId(Mark mark) {
        boolean exist = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_COURSE_USER_ID)) {
            preparedStatement.setInt(1, mark.getIdCourse());
            preparedStatement.setInt(2, mark.getIdUser());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
    public Mark findById(int id) {
        return null;
    }

    @Override
    public boolean delete(Mark entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean insert(Mark entity) {
        return false;
    }

    public boolean insert(List<Mark> marks) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_MARKS)) {
            for (Mark mark : marks) {
                preparedStatement.setInt(1, mark.getTotal());
                preparedStatement.setInt(2, mark.getIdCourse());
                preparedStatement.setInt(3, mark.getIdUser());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            logger.info("Created new mark records");
            inserted = true;
        } catch (SQLException e) {
            logger.error("New mark records were not inserted to db. " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public boolean update(Mark entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_MARK)) {
            preparedStatement.setInt(1, entity.getTotal());
            preparedStatement.setInt(2, entity.getIdCourse());
            preparedStatement.setInt(3, entity.getIdUser());
            preparedStatement.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return updated;
    }
}
