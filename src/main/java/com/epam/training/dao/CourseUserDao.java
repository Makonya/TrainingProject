package com.epam.training.dao;

import com.epam.training.connectionpool.ConnectionPool;
import com.epam.training.entity.CourseUser;
import com.epam.training.entity.Entity;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseUserDao extends AbstractDao {
    private static Logger logger = Logger.getLogger(CourseUser.class);
    private static final String SQL_SELECT_COURSE_USER_BY_COURSE_ID = "SELECT * FROM COURSE_USER WHERE ID_COURSE=?";
    private static final String SQL_SELECT_COURSE_USER_BY_USER_ID = "SELECT * FROM COURSE_USER WHERE ID_USER=?";

    public boolean findByUserId(int userID){
        boolean exist = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COURSE_USER_BY_USER_ID)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
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
    public List findAll() {
        return null;
    }

    @Override
    public Entity findById(int id) {
        return null;
    }

    @Override
    public boolean delete(Entity entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean insert(Entity entity) {
        return false;
    }

    @Override
    public boolean update(Entity entity) {
        return false;
    }
}
