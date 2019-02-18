package com.epam.training.dao;

import com.epam.training.pool.ConnectionPool;
import com.epam.training.entity.Feedback;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDao extends AbstractDao<Feedback> {
    private static Logger logger = Logger.getLogger(FeedbackDao.class);
    private static final String SQL_SELECT_FEEDBACK_BY_COURSE_ID = "SELECT * FROM FEEDBACK WHERE ID_COURSE=?";
    private static final String SQL_INSERT_FEEDBACK = "INSERT INTO FEEDBACK(INFO, FEEDBACK_DATE, ID_COURSE, ID_USER) VALUES(?,?,?,?)";

    @Override
    public List<Feedback> findAll() {
        return null;
    }

    @Override
    public Feedback findById(int id) {
        return null;
    }

    @Override
    public boolean delete(Feedback entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean insert(Feedback comment) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_FEEDBACK)) {
            setFeedbackParameters(preparedStatement, comment).executeUpdate();
            logger.info("Inserted new feedback comment");
            inserted = true;
        } catch (SQLException e) {
            logger.error("New feedback comment wasn't inserted to db. " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public boolean update(Feedback entity) {
        return false;
    }

    public List<Feedback> findByCourseId(int courseId){
        List<Feedback> feedback = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FEEDBACK_BY_COURSE_ID)) {
            preparedStatement.setInt(1, courseId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Feedback comment = getFeedbackParameters(resultSet);
                    feedback.add(comment);
                }
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return feedback;
    }
    private Feedback getFeedbackParameters(ResultSet resultSet) throws SQLException {
        Feedback comment = new Feedback();
        comment.setId(resultSet.getInt("ID_FEEDBACK"));
        comment.setFeedbackText(resultSet.getString("INFO"));
        comment.setFeedbackDate(resultSet.getDate("FEEDBACK_DATE"));
        comment.setIdCourse(resultSet.getInt("ID_COURSE"));
        comment.setIdUser(resultSet.getInt("ID_USER"));
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
