package com.epam.training.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.epam.training.util.PropertyFileLoader;
import org.apache.log4j.*;

public class ConnectionPool {
    private static final int DEFAULT_POOL_SIZE = 5;
    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool connectionPool = ConnectionPool.init();

    private final BlockingQueue<Connection> CONNECTION_QUEUE;

    private ConnectionPool(String url, String user, String password, int poolSize) throws SQLException {

        CONNECTION_QUEUE = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Connection connection = DriverManager.getConnection(url, user, password);
            CONNECTION_QUEUE.offer(connection);
        }
    }

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    private static ConnectionPool init() {
        ConnectionPool instance = null;
        Properties properties = PropertyFileLoader.load("database.properties");
        String driver = properties.getProperty("db.driverClassName");
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String poolSizeStr = properties.getProperty("db.poolSize");
        int poolSize = (poolSizeStr != null) ? Integer.parseInt(poolSizeStr) : DEFAULT_POOL_SIZE;
        try {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                logger.error("Can't find driver for JDBC MySql", e);
            }
            instance = new ConnectionPool(url, user, password, poolSize);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Connection pool is not initialized " + e.getMessage());
        }
        return instance;
    }

    public static void dispose() throws SQLException {
        if (connectionPool != null) {
            connectionPool.clearConnectionQueue();
            connectionPool = null;
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = CONNECTION_QUEUE.take();
        } catch (InterruptedException e) {
            logger.error("InterruptedException! It's impossible to take connection! " + e.getMessage());
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                if (!CONNECTION_QUEUE.offer(connection)) {
                    logger.info("Connection not added. Possible 'leakage' of connections!");
                }
            } else {
                logger.info("Trying to release closed connection. Possible 'leakage' of connections!");
            }
        } catch (SQLException e) {
            logger.error("SQLException at connection isClosed () checking.  Connection not added! " + e.getMessage());
        }
    }

    private void clearConnectionQueue() throws SQLException {
        Connection connection;
        while ((connection = CONNECTION_QUEUE.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();
        }
    }
}