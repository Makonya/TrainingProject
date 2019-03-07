package com.epam.training.dao;

import com.epam.training.pool.ConnectionPool;
import com.epam.training.entity.CourseUser;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseUserDao extends AbstractDao<CourseUser> {
    private static final Logger LOGGER = Logger.getLogger(CourseUser.class);
    private static final String SQL_SELECT_ALL_COURSE_USER = "SELECT * FROM COURSE_USER";
    private static final String SQL_SELECT_COURSE_USER_BY_USER_COURSE_ID = "SELECT * FROM COURSE_USER WHERE ID_USER=? AND ID_COURSE=?";
    private static final String SQL_SELECT_COURSE_USER_BY_USER_ID = "SELECT * FROM COURSE_USER c_u " +
            "LEFT JOIN USER u ON c_u.ID_USER=u.ID_USER " +
            "LEFT JOIN MARK m ON m.ID_COURSE=c_u.ID_COURSE AND c_u.ID_USER=m.ID_USER " +
            "LEFT JOIN COURSE c ON c.ID_COURSE=c_u.ID_COURSE where c_u.ID_USER=?";
    private static final String SQL_SELECT_COURSE_USER_BY_COURSE_ID = "SELECT * FROM COURSE_USER c_u " +
            "LEFT JOIN MARK m ON c_u.ID_COURSE=m.ID_COURSE AND c_u.ID_USER=m.ID_USER " +
            "LEFT JOIN USER u ON c_u.ID_USER=u.ID_USER " +
            "WHERE c_u.ID_COURSE=?";
    private static final String SQL_INSERT_COURSE_USER = "INSERT INTO COURSE_USER(ID_USER,ID_COURSE) VALUES(?,?)";
    private static final String SQL_DELETE_COURSE_USER = "DELETE FROM COURSE_USER WHERE ID_USER=? AND ID_COURSE=?";

    public List<CourseUser> findByCourseId(int courseId) {
        List<CourseUser> courseUsers = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSE_USER_BY_COURSE_ID)) {
            preparedStatement.setInt(1, courseId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CourseUser courseUser = new CourseUser();
                    courseUser.setIdCourse(resultSet.getInt("ID_COURSE"));
                    courseUser.setIdUser(resultSet.getInt("ID_USER"));
                    courseUser.setTempStudentName(resultSet.getString("u.NAME"));
                    courseUser.setTempStudentSurname(resultSet.getString("u.SURNAME"));
                    courseUser.setTempMark(resultSet.getInt("m.MARK"));
                    courseUsers.add(courseUser);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the course_user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return courseUsers;
    }

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
            LOGGER.error("Errors occurred while accessing the course_user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return exist;
    }

    public List<CourseUser> findByUserId(int userID) {
        List<CourseUser> courseUsers = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSE_USER_BY_USER_ID)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CourseUser courseUser = new CourseUser();
                    courseUser.setIdCourse(resultSet.getInt("ID_COURSE"));
                    courseUser.setIdUser(resultSet.getInt("ID_USER"));
                    courseUser.setTempStartDate(resultSet.getDate("START_DATE"));
                    courseUser.setTempEndDate(resultSet.getDate("END_DATE"));
                    courseUser.setTempMark(resultSet.getInt("MARK"));
                    courseUser.setTempCourseName(resultSet.getString("COURSE_NAME"));
                    courseUsers.add(courseUser);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the course_user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return courseUsers;
    }
    @Override
    public List<CourseUser> findAll() {
        List<CourseUser> courseUserList = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_COURSE_USER)) {
            while (resultSet.next()) {
                CourseUser courseUser = new CourseUser();
                courseUser.setIdCourse(resultSet.getInt("ID_COURSE"));
                courseUser.setIdUser(resultSet.getInt("ID_USER"));
                courseUserList.add(courseUser);
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the course_user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return courseUserList;
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
            LOGGER.info("Record from course_user was deleted.");
        } catch (SQLException e) {
            LOGGER.error("Record from course_user wasn't deleted. " + e);
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
            LOGGER.info("Created new course user record");
            inserted = true;
        } catch (SQLException e) {
            LOGGER.error("New course user record wasn't inserted to db. " + e.getMessage());
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
