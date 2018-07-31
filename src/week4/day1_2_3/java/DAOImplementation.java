/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author cb-mohamedsullaiman
 */
public class DAOImplementation {

    public static void create(String query) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getConnection();

            Statement statement = connection.createStatement();

            statement.executeUpdate("create " + query);

        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }

    }

    public static ResultSet select(String query, Object... params) throws SQLException {
        PreparedStatement retrievalQuery = null;
        ResultSet resultSet = null;
        Connection connection = null;
        System.out.println("Before getting connection in dao implementation");
        try {
            connection = ConnectionPool.getConnection();
            System.out.println("After getting connection");
            retrievalQuery = connection.prepareStatement("select " + query);
            System.out.println("select " + query);
            for (int i = 0; i < params.length; i++) {
                retrievalQuery.setObject(i + 1, params[i]);
            }
            resultSet = retrievalQuery.executeQuery();

        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return resultSet;
    }

    public static Integer insert(String query, Object... params) throws SQLException {
        Connection connection = null;
        PreparedStatement insertionQuery = null;
        Integer rowsAffected = 0;
        try {
            connection = ConnectionPool.getConnection();
            insertionQuery = connection.prepareStatement("insert " + query);
            for (int i = 0; i < params.length; i++) {
                insertionQuery.setObject(i + 1, params[i]);
            }
            rowsAffected = insertionQuery.executeUpdate();
        } finally {
            if (insertionQuery != null) {
                insertionQuery.close();
            }
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return rowsAffected;
    }

    public static ResultSet insert(String query, Integer constant, Object... params) throws SQLException {
        Connection connection = null;
        PreparedStatement insertionQuery = null;
        ResultSet resultSet = null;
        Integer rowsAffected = 0;
        try {
            connection = ConnectionPool.getConnection();
            insertionQuery = connection.prepareStatement("insert " + query, constant);
            for (int i = 0; i < params.length; i++) {
                insertionQuery.setObject(i + 1, params[i]);
            }
            rowsAffected = insertionQuery.executeUpdate();
            System.out.println(rowsAffected + " rows affected");
            resultSet = insertionQuery.getGeneratedKeys();
        } finally {
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return resultSet;
    }

    public static Integer update(String query, Object... params) throws SQLException {
        Connection connection = null;
        PreparedStatement updationQuery = null;
        Integer rowsAffected = 0;
        try {
            connection = ConnectionPool.getConnection();
            updationQuery = connection.prepareStatement("update " + query);
            for (int i = 0; i < params.length; i++) {
                updationQuery.setObject(i + 1, params[i]);
            }
            System.out.println(updationQuery.toString());
            rowsAffected = updationQuery.executeUpdate();
        } finally {
            if (updationQuery != null) {
                updationQuery.close();
            }
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return rowsAffected;
    }

    public static Integer delete(String query, Object... params) throws SQLException {
        Connection connection = null;
        PreparedStatement deletionQuery = null;
        Integer rowsAffected = 0;
        try {
            connection = ConnectionPool.getConnection();
            deletionQuery = connection.prepareStatement("delete " + query);
            for (int i = 0; i < params.length; i++) {
                deletionQuery.setObject(i + 1, params[i]);
            }
            rowsAffected = deletionQuery.executeUpdate();
        } finally {
            if (deletionQuery != null) {
                deletionQuery.close();
            }
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return rowsAffected;
    }

    public static Integer drop(String query, Object... params) throws SQLException {
        Connection connection = null;
        PreparedStatement queryStatement = null;
        Integer rowsAffected = 0;
        try {
            connection = ConnectionPool.getConnection();
            queryStatement = connection.prepareStatement("drop " + query);
            for (int i = 0; i < params.length; i++) {
                queryStatement.setObject(i + 1, params[i]);
            }
            rowsAffected = queryStatement.executeUpdate();
        } finally {
            if (queryStatement != null) {
                queryStatement.close();
            }
            if (connection != null) {
                ConnectionPool.releaseConnection(connection);
            }
        }
        return rowsAffected;
    }
}
