/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO;

import connectionpool.DBpool;
import exceptions.DAOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;


/**
 *
 * @author noflaxe
 */
public class BasicCRUD {
    
    private Connection connect;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet result;
    private Logger logger;
    
    public BasicCRUD(Logger log){
        logger = log;
    }
    
    public void makeConnection()
    {
        try {
            connect =  DBpool.getInstance().getConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
            logger.error("Exception thrown!",ex);
        }
    }
    
    public void insertValues(Object... args)    {
    for(int i=0;i<args.length;i++)
    {
            try {
                preparedStatement.setObject(i+1, args[i]);
            } catch (SQLException ex) {
                 logger.info("Sql exception thrown");
                 logger.error("Exception thrown!",ex);
            }
    }
    }
    
    public ResultSet getItems(String query,Object... args){
        try {
           preparedStatement = connect.prepareStatement(query);
           insertValues(args);
           result = preparedStatement.executeQuery();
        } catch (Exception ex) {
             logger.info("exception thrown");
           logger.error("Exception thrown!",ex);
        }
    return result;
    }
    
    public int executeQuery(String query,Object... args){
        int resultValue = -1;
        try {
            preparedStatement = connect.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            insertValues(args);
            resultValue = preparedStatement.executeUpdate();
            result = preparedStatement.getGeneratedKeys();
        } catch (SQLException ex) {
             logger.info("Sql exception thrown");
           logger.error("Exception thrown!",ex);
        }
        return resultValue;
    }
    
    public void closeConnection()
    {
        try {
     if (result != null) {
            
                result.close();
           
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
             } catch (SQLException ex) {
                  logger.info("Sql exception thrown");
                logger.error("Exception thrown!",ex);
            }
    }
    
}
