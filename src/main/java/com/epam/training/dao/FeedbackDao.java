package com.epam.training.dao;

import com.epam.training.pool.ConnectionPool;
import com.epam.training.entity.Feedback;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDao extends AbstractDao<Feedback> {
    private static final Logger LOGGER = Logger.getLogger(FeedbackDao.class);
    private static final String SQL_SELECT_FEEDBACK_BY_COURSE_ID = "SELECT * FROM FEEDBACK f LEFT JOIN USER u ON f.ID_USER = u.ID_USER WHERE f.ID_COURSE=?";
    private static final String SQL_SELECT_FEEDBACK_BY_COURSE_USER_ID = "SELECT * FROM FEEDBACK WHERE ID_COURSE=? AND ID_USER=?";
    private static final String SQL_INSERT_FEEDBACK = "INSERT INTO FEEDBACK(INFO, FEEDBACK_DATE, ID_COURSE, ID_USER) VALUES(?,?,?,?)";

    @Override
    public List<Feedback> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Feedback findById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Feedback entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean insert(Feedback comment) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_FEEDBACK)) {
            setFeedbackParameters(preparedStatement, comment).executeUpdate();
            LOGGER.info("Inserted new feedback comment");
            inserted = true;
        } catch (SQLException e) {
            LOGGER.error("New feedback comment wasn't inserted to db. " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public boolean update(Feedback entity) {
        throw new UnsupportedOperationException();
    }

    public List<Feedback> findByCourseId(int courseId) {
        List<Feedback> feedback = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FEEDBACK_BY_COURSE_ID)) {
            preparedStatement.setInt(1, courseId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Feedback comment = getFeedbackParameters(resultSet);
                    feedback.add(comment);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return feedback;
    }

    public boolean findByCourseUserId(int courseId, int userId) {
        boolean exist = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FEEDBACK_BY_COURSE_USER_ID)) {
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(1, courseId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return exist;
    }

    private Feedback getFeedbackParameters(ResultSet resultSet) throws SQLException {
        Feedback comment = new Feedback();
        comment.setId(resultSet.getInt("ID_FEEDBACK"));
        comment.setFeedbackText(resultSet.getString("INFO"));
        comment.setFeedbackDate(resultSet.getDate("FEEDBACK_DATE"));
        comment.setIdCourse(resultSet.getInt("ID_COURSE"));
        comment.setIdUser(resultSet.getInt("ID_USER"));
        comment.setUserFullName(resultSet.getString("u.NAME") + " " + resultSet.getString("u.SURNAME"));
        return comment;
    }

    private PreparedStatement setFeedbackParameters(PreparedStatement preparedStatement, Feedback comment) throws SQLException {
        preparedStatement.setString(1, comment.getFeedbackText());
        preparedStatement.setDate(2, (Date) comment.getFeedbackDate());
        preparedStatement.setInt(3, comment.getIdCourse());
        preparedStatement.setInt(4, comment.getIdUser());
        return preparedStatement;
    }
}