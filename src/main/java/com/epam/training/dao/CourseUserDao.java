package com.epam.training.dao;

import com.epam.training.pool.ConnectionPool;
import com.epam.training.entity.CourseUser;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseUserDao extends AbstractDao<CourseUser> {
    private static Logger logger = Logger.getLogger(CourseUser.class);
    private static final String SQL_SELECT_COURSE_USER_BY_USER_COURSE_ID = "SELECT * FROM COURSE_USER WHERE ID_USER=? AND ID_COURSE=?";
    private static final String SQL_INSERT_COURSE_USER = "INSERT INTO COURSE_USER(ID_USER,ID_COURSE) VALUES(?,?)";
    private static final String SQL_DELETE_COURSE_USER = "DELETE FROM COURSE_USER WHERE ID_USER=? AND ID_COURSE=?";

    public boolean findByUserCourseId(int userID, int courseID) {
        boolean exist = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSE_USER_BY_USER_COURSE_ID)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, courseID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    exist = true;
                }
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the course_user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return exist;
    }

    @Override
    public List<CourseUser> findAll() {
        return null;
    }

    @Override
    public CourseUser findById(int id) {
        return null;
    }

    @Override
    public boolean delete(CourseUser entity) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_COURSE_USER)) {
            preparedStatement.setInt(1, entity.getIdUser());
            preparedStatement.setInt(2, entity.getIdCourse());
            preparedStatement.execute();
            deleted = true;
            logger.info("Record from course_user was deleted.");
        } catch (SQLException e) {
            logger.error("Record from course_user wasn't deleted. " + e);
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return deleted;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean insert(CourseUser courseUser) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_COURSE_USER)) {
            preparedStatement.setInt(1, courseUser.getIdUser());
            preparedStatement.setInt(2, courseUser.getIdCourse());
            preparedStatement.executeUpdate();
            logger.info("Created new course user record");
            inserted = true;
        } catch (SQLException e) {
            logger.error("New course user record wasn't inserted to db. " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public boolean update(CourseUser entity) {
        return false;
    }
}
