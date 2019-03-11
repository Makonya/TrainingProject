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
    private static final Logger LOGGER = Logger.getLogger(MarkDao.class);
    private static final String SQL_INSERT_MARKS = "INSERT INTO MARK(MARK, ID_COURSE, ID_USER) VALUES(?, ?, ?)";
    private static final String SQL_SELECT_BY_COURSE_USER_ID = "SELECT * FROM MARK WHERE ID_COURSE=? AND ID_USER=?";
    private static final String SQL_UPDATE_MARK = "UPDATE MARK SET MARK = ? WHERE ID_COURSE = ? AND ID_USER = ?";


    @Override
    public List<Mark> findAll() {
        throw new UnsupportedOperationException();
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
            LOGGER.error("Errors occurred while accessing the mark table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return exist;
    }

    @Override
    public Mark findById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Mark entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean insert(Mark entity) {
        throw new UnsupportedOperationException();
    }

    public boolean insert(List<Mark> marks) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_MARKS)) {
            connection.setAutoCommit(false);
            for (Mark mark : marks) {
                setValues(preparedStatement, mark).addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            LOGGER.info("Created new mark records");
            inserted = true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error("Errors occurred while rollback process." + e1.getMessage());
            }
            LOGGER.error("New mark records were not inserted to db. " + e.getMessage());
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
            setValues(preparedStatement, entity).executeUpdate();
            updated = true;
            LOGGER.info("Updated mark record");
        } catch (SQLException e) {
            LOGGER.error("Mark record was not updated. " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return updated;
    }

    public boolean update(List<Mark> marks) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_MARK)) {
            connection.setAutoCommit(false);
            for (Mark mark : marks) {
                setValues(preparedStatement, mark).addBatch();
            }
            preparedStatement.executeUpdate();
            connection.commit();
            updated = true;
            LOGGER.info("Updated mark records");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error("Errors occurred while rollback process." + e1.getMessage());
            }
            LOGGER.error("Mark records were not updated. " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return updated;
    }

    private PreparedStatement setValues(PreparedStatement preparedStatement, Mark mark) throws SQLException {
        preparedStatement.setInt(1, mark.getTotal());
        preparedStatement.setInt(2, mark.getIdCourse());
        preparedStatement.setInt(3, mark.getIdUser());
        return preparedStatement;
    }
}
