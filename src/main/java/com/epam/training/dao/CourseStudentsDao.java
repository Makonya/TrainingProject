package com.epam.training.dao;

import com.epam.training.entity.CourseStudents;
import com.epam.training.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseStudentsDao extends AbstractDao<CourseStudents> {
    private static final Logger LOGGER = Logger.getLogger(CourseStudentsDao.class);
    private static final String SQL_SELECT_COURSE_STUDENTS_BY_COURSE_ID = "SELECT * FROM COURSE_STUDENTS WHERE ID_COURSE=?";

    public List<CourseStudents> findByCourseId(int courseId){
        List<CourseStudents> courseStudentsList = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSE_STUDENTS_BY_COURSE_ID)) {
            preparedStatement.setInt(1, courseId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CourseStudents courseStudents = new CourseStudents();
                    courseStudents.setIdCourse(resultSet.getInt("ID_COURSE"));
                    courseStudents.setStudentName(resultSet.getString("NAME"));
                    courseStudents.setStudentSurname(resultSet.getString("SURNAME"));
                    courseStudentsList.add(courseStudents);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the course_user table! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return  courseStudentsList;
    }

    @Override
    public List<CourseStudents> findAll() {
        return null;
    }

    @Override
    public CourseStudents findById(int id) {
        return null;
    }

    @Override
    public boolean delete(CourseStudents entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean insert(CourseStudents entity) {
        return false;
    }

    @Override
    public boolean update(CourseStudents entity) {
        return false;
    }
}
