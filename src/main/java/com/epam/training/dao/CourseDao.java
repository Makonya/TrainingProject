package com.epam.training.dao;

import com.epam.training.pool.ConnectionPool;
import com.epam.training.entity.Course;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao extends AbstractDao<Course> {
    private static Logger logger = Logger.getLogger(CourseDao.class);
    private static final String SQL_SELECT_ALL_COURSES = "SELECT * FROM COURSE";
    private static final String SQL_SELECT_COURSE_BY_ID = "SELECT * FROM COURSE c LEFT JOIN USER u ON c.ID_USER=u.ID_USER WHERE ID_COURSE=?";
    private static final String SQL_SELECT_COURSES_BY_CATEGORY = "SELECT * FROM COURSE WHERE ID_CATEGORY=?";

    @Override
    public List<Course> findAll() {
        List<Course> users = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_COURSES)) {
            while (resultSet.next()) {
                users.add(getCourseParameters(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the course table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return users;
    }

    @Override
    public Course findById(int idCourse) {
        Course course = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSE_BY_ID)) {
            preparedStatement.setInt(1, idCourse);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    course = getCourseParameters(resultSet);
                    course.setTeacherFullName(resultSet.getString("u.NAME") + " " +resultSet.getString("u.SURNAME"));
                }
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return course;
    }

    @Override
    public boolean delete(Course entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean insert(Course entity) {
        return false;
    }

    @Override
    public boolean update(Course entity) {
        return false;
    }

    public List<Course> findByCategoryId(int categoryId) {
        List<Course> users = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSES_BY_CATEGORY)) {
            preparedStatement.setInt(1, categoryId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(getCourseParameters(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Errors occurred while accessing the course table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return users;
    }

    private Course getCourseParameters(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getInt("ID_COURSE"));
        course.setCourseName(resultSet.getString("COURSE_NAME"));
        course.setDescription(resultSet.getString("DESCRIPTION"));
        course.setStartDate(resultSet.getDate("START_DATE"));
        course.setEndDate(resultSet.getDate("END_DATE"));
        course.setIdUser(resultSet.getInt("ID_USER"));
        course.setIdCategory(resultSet.getInt("ID_CATEGORY"));
        return course;
    }
}
