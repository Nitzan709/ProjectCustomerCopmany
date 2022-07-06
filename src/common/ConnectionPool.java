package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final int SIZE = 2;
    public static final String DATABASE_NAME = "cs";
    public static final String DATABASE_URL
            = String.format("jdbc:mysql://localhost:3306/%s", DATABASE_NAME);
    public static final String USERNAME = "root";
    public static final String PASSWORD = "password";

    private static ConnectionPool instance;
    private final BlockingQueue<Connection> queue;

    private ConnectionPool() {
        queue = new ArrayBlockingQueue<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            queue.add(createConnection());
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    private static Connection createConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Unable to insert connection: %s", e.getMessage()));
        }
    }

    public Connection take() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while trying to take a connection.");
        }
    }

    public void put(Connection connection) {
        try {
            queue.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while trying to put a connection.");
        }
    }

    public void close() {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Unable to access the database while trying to close the pool");
            }
        }
    }

}
