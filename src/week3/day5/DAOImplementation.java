/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week3.day5;

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
    private ConnectionPool pool;
    
    public void setUpConnectionPool(String url, String userName, String password) throws SQLException {
        pool = new ConnectionPool(url, userName, password);
    }
    
    public void create(String query) throws SQLException{
        Connection connection = null;
        try{
          connection = pool.getConnection();
        
        Statement statement = connection.createStatement();
        
        statement.executeUpdate("create "+query);
          
        }
        finally{
            if(connection != null){
                pool.releaseConnection(connection);
            }
        }        
        
    }
    
    public ResultSet select(String query, Object... params) throws SQLException{
        PreparedStatement retrievalQuery = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = pool.getConnection();
            
            retrievalQuery = connection.prepareStatement("select "+query);
            
            for (int i=0;i<params.length; i++) {
                retrievalQuery.setObject(i+1, params[i]);            
            }
            resultSet = retrievalQuery.executeQuery();           
            
            
            
        } finally {
            if(connection != null){
                pool.releaseConnection(connection);
            }
        }
        return resultSet;
    }
    
    public Integer insert(String query, Object... params) throws SQLException{
        Connection connection = null;
        PreparedStatement insertionQuery = null;
        Integer rowsAffected = 0;
        try{
            connection = pool.getConnection();
            insertionQuery = connection.prepareStatement("insert "+query);
            for(int i =0;i<params.length; i++){
                insertionQuery.setObject(i+1, params[i]);
            }
            rowsAffected = insertionQuery.executeUpdate();
        }
        finally{
            if(insertionQuery!=null){
                insertionQuery.close();
            }
            if(connection != null){
                pool.releaseConnection(connection);
            }
        }
        return rowsAffected;    
    }
    public ResultSet insert(String query, Integer constant, Object... params) throws SQLException{
        Connection connection = null;
        PreparedStatement insertionQuery = null;
        ResultSet resultSet = null;
        Integer rowsAffected = 0;
        try{
            connection = pool.getConnection();
            insertionQuery = connection.prepareStatement("insert "+query,constant);
            for(int i =0;i<params.length; i++){
                insertionQuery.setObject(i+1, params[i]);
            }
            rowsAffected = insertionQuery.executeUpdate();
            System.out.println(rowsAffected +" rows affected");
            resultSet = insertionQuery.getGeneratedKeys();
        }
        finally{
            if(connection != null){
                pool.releaseConnection(connection);
            }
        }
        return resultSet;    
    }
    public Integer update(String query, Object... params) throws SQLException{
        Connection connection = null;
        PreparedStatement updationQuery = null;
        Integer rowsAffected = 0;
        try{
            connection = pool.getConnection();
            updationQuery = connection.prepareStatement("update "+query);
            for(int i =0;i<params.length; i++){
                updationQuery.setObject(i+1, params[i]);
            }
            System.out.println(updationQuery.toString());
            rowsAffected = updationQuery.executeUpdate();
        }
        finally{
            if(updationQuery!=null){
                updationQuery.close();
            }
            if(connection != null){
                pool.releaseConnection(connection);
            }
        }
        return rowsAffected;    
    }
    public Integer delete(String query, Object... params)throws SQLException{
        Connection connection = null;
        PreparedStatement deletionQuery = null;
        Integer rowsAffected = 0;
        try{
            connection = pool.getConnection();
            deletionQuery = connection.prepareStatement("delete "+query);
            for(int i =0;i<params.length; i++){
                deletionQuery.setObject(i+1, params[i]);
            }
            rowsAffected = deletionQuery.executeUpdate();
        }
        finally{
            if(deletionQuery!=null){
                deletionQuery.close();
            }
            if(connection != null){
                pool.releaseConnection(connection);
            }
        }
        return rowsAffected;    
    }
    public Integer drop(String query, Object... params) throws SQLException{
        Connection connection = null;
        PreparedStatement queryStatement = null;
        Integer rowsAffected = 0;
        try{
            connection = pool.getConnection();
            queryStatement = connection.prepareStatement("drop "+query);
            for(int i =0;i<params.length; i++){
                queryStatement.setObject(i+1, params[i]);
            }
            rowsAffected = queryStatement.executeUpdate();
        }
        finally{
            if(queryStatement!=null){
                queryStatement.close();
            }
            if(connection != null){
                pool.releaseConnection(connection);
            }
        }
        return rowsAffected;
    }
}
