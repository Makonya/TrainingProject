package com.epam.training.dao;

import com.epam.training.pool.ConnectionPool;
import com.epam.training.entity.Course;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CourseDao extends AbstractDao<Course> {
    private static final Logger LOGGER = Logger.getLogger(CourseDao.class);
    private static final String SQL_SELECT_ALL_COURSES = "SELECT * FROM COURSE";
    private static final String SQL_SELECT_COURSE_BY_ID = "SELECT * FROM COURSE c LEFT JOIN USER u ON c.ID_USER=u.ID_USER WHERE ID_COURSE=?";
    private static final String SQL_SELECT_COURSE_BY_USER_ID = "SELECT * FROM COURSE where ID_USER=?";
    private static final String SQL_SELECT_COURSES_BY_CATEGORY = "SELECT * FROM COURSE WHERE ID_CATEGORY=?";
    private static final String SQL_INSERT_COURSE = "INSERT INTO COURSE(COURSE_NAME,DESCRIPTION,START_DATE,END_DATE,ID_USER,ID_CATEGORY) VALUES(?,?,?,?,?,?)";
    private static final String SQL_DELETE_COURSE_BY_ID = "DELETE FROM COURSE WHERE ID_COURSE=?";
    private static final String SQL_UPDATE_COURSE = "UPDATE COURSE SET COURSE_NAME = ?, DESCRIPTION = ?, START_DATE = ?, END_DATE = ? WHERE ID_COURSE=?";

    @Override
    public List<Course> findAll() {
        List<Course> users = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_COURSES)) {
            while (resultSet.next()) {
                users.add(getCourseParameters(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the course table! " + e.getMessage());
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
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    course = getCourseParameters(resultSet);
                    course.setTeacherFullName(resultSet.getString("u.NAME") + " " + resultSet.getString("u.SURNAME"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return course;
    }

    @Override
    public boolean delete(Course entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_COURSE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            deleted = true;
            LOGGER.info("Course was deleted. Course ID = " + id);
        } catch (SQLException e) {
            LOGGER.error("Course wasn't deleted. " + e);
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return deleted;
    }

    @Override
    public boolean insert(Course entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_COURSE)) {
            preparedStatement.setInt(5, entity.getIdUser());
            preparedStatement.setInt(6, entity.getIdCategory());
            setValues(preparedStatement, entity).executeUpdate();
            inserted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public boolean update(Course entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COURSE)) {
            preparedStatement.setInt(5, entity.getId());
            setValues(preparedStatement, entity).executeUpdate();
            updated = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return updated;
    }

    public List<Course> findByCategoryId(int categoryId) {
        return findBySomeId(SQL_SELECT_COURSES_BY_CATEGORY, categoryId);
    }

    public List<Course> findByUserId(int userId) {
        return findBySomeId(SQL_SELECT_COURSE_BY_USER_ID, userId);
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

    private PreparedStatement setValues(PreparedStatement preparedStatement, Course entity) throws SQLException {
        preparedStatement.setString(1, entity.getCourseName());
        preparedStatement.setString(2, entity.getDescription());
        preparedStatement.setDate(3, (Date) entity.getStartDate());
        preparedStatement.setDate(4, (Date) entity.getEndDate());
        return preparedStatement;
    }

    private List<Course> findBySomeId(String statement, int id) {
        List<Course> courses = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courses.add(getCourseParameters(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the course table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return courses;
    }

    public List<Course> findByIds(Set<Integer> ids) {
        List<Course> courses = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSE_BY_ID)) {
            for (Integer id : ids) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        courses.add(getCourseParameters(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the course table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return courses;
    }
}