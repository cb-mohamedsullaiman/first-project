package week3.day5;

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
    
    private LinkedList<Connection> availableConnections = new LinkedList<>();
    private HashSet<Connection> usedConnections = new HashSet<>();
    
    
    private final Integer MAX_CONNECTIONS = 5;
    
    public ConnectionPool(String url,String userName,String password) throws SQLException{
        
        for(int i=0;i<MAX_CONNECTIONS;i++){
            availableConnections.add(DriverManager.getConnection(url, userName, password));
        }
    }
    
    public Connection getConnection(){
        if(availableConnections.size()==0){
            System.out.println("All connections are used already!!");
            return null;
        }
        Connection connection = availableConnections.removeFirst();
        usedConnections.add(connection);
        return connection;
    }
    
    public Boolean releaseConnection(Connection connection){
        if(connection == null && !usedConnections.remove(connection)){
            return false;
        }
        availableConnections.addLast(connection);
        return true;
    }
    
    public Integer getAvailableConnectionCount(){
        return availableConnections.size();
    }
    
    
}
