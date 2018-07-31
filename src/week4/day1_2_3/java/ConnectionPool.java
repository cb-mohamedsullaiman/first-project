package portal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cb-mohamedsullaiman
 */
public class ConnectionPool {

    private static LinkedList<Connection> availableConnections = new LinkedList<>();
    private static HashSet<Connection> usedConnections = new HashSet<>();

    private static final Integer MAX_CONNECTIONS = 5;

    public static void setUpConnectionPool(String url, String userName, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("before setting up connection pool");
        System.out.println(url + "\t" + userName + "\t" + password);
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            availableConnections.add(DriverManager.getConnection(url, userName, password));
        }
        System.out.println(url + "\t" + userName + "\t" + password);
        System.out.println("Connection pool established");
        System.out.println(availableConnections.size());
    }

    public static Connection getConnection() {
        if (availableConnections.size() == 0) {
            System.out.println("All connections are used already!!");
            return null;
        }
        Connection connection = availableConnections.removeFirst();
        usedConnections.add(connection);
        return connection;
    }

    public static Boolean releaseConnection(Connection connection) {
        if (connection == null && !usedConnections.remove(connection)) {
            return false;
        }
        availableConnections.addLast(connection);
        return true;
    }

    public static Integer getAvailableConnectionsCount() {
        return availableConnections.size();
    }

    public static Integer getUsedConnectionsCount() {
        return usedConnections.size();
    }

}
