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
    private static final String SQL_SELECT_COURSE_USER_BY_COURSE_ID = "SELECT * FROM COURSE_STUDENTS WHERE ID_COURSE=?";

    @Override
    public List<CourseStudents> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CourseStudents findById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(CourseStudents entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean insert(CourseStudents entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(CourseStudents entity) {
        throw new UnsupportedOperationException();
    }

    public List<CourseStudents> findByCourseId(int idCourse){
        List<CourseStudents> courseStudentsList = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSE_USER_BY_COURSE_ID)) {
            preparedStatement.setInt(1, idCourse);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CourseStudents courseStudent = new CourseStudents();
                    courseStudent.setIdCourse(resultSet.getInt("ID_COURSE"));
                    courseStudent.setIdUser(resultSet.getInt("ID_USER"));
                    courseStudent.setStudentName(resultSet.getString("NAME"));
                    courseStudent.setStudentSurname(resultSet.getString("SURNAME"));
                    courseStudent.setStudentMark(resultSet.getInt("MARK"));
                    courseStudentsList.add(courseStudent);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Errors occurred while accessing the course_students view! " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return courseStudentsList;
    }
}