/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO;

import CRUDDAO.Interfaces.UserCRUD;
import exceptions.DAOException;
import model.User;
import innerStrings.*;
import java.sql.SQLException;
import org.apache.log4j.Logger;


/**
 *
 * @author noflaxe
 */
public class UserDAO extends SQLDAO implements UserCRUD {

    public UserDAO(Logger log){
        super(log);
    }
    
    @Override
    public User findByLogin(String login) throws DAOException {
      User user = null;
        try {
            command.makeConnection();
            Object[] args = {login};
            resultSet = command.getItems(SQLQueries.getSQLCommand("FindUserByLogin"), args);
            if(resultSet.next()){
                 user = new User();
                 user.setId(resultSet.getInt("Id"));
                 user.setLogin(login);
                 user.setPassword(resultSet.getString("Password"));
                 if(resultSet.getInt("isInstructor") == 1){
                   user.setIsInstructor(true);
                 } else {
                     user.setIsInstructor(false);
                 }
            }
            command.closeConnection();
        } catch (SQLException ex) {
           logger.info("Sql exception thrown");
            logger.error("Exception thrown!",ex);
        }
        return user;
    }

    @Override
    public boolean delete(User entity) throws DAOException {
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getId()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("deleteUser"), args);
       command.closeConnection();
      
       if(resultValue > 0){
           return true;
       } else {
           return false;
       }
      
    }

    @Override
    public User findById(int id) throws DAOException {
        User user = null;
        try {
            command.makeConnection();
            Object[] args = {id};
            resultSet = command.getItems(SQLQueries.getSQLCommand("FindUserById"), args);
            if(resultSet.next()){
                 user = new User();
                 user.setId(id);
                 user.setLogin(resultSet.getString("Login"));
                 user.setPassword(resultSet.getString("Password"));
                 if(resultSet.getInt("isInstructor") == 1){
                   user.setIsInstructor(true);
                 } else {
                     user.setIsInstructor(false);
                 }
            }
            command.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
           logger.error("Exception thrown!",ex);
        }
        return user;
    }

    @Override
    public boolean update(User entity) throws DAOException {
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getPassword(),entity.getId()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("updateUser"), args);
       command.closeConnection();
      
       if(resultValue > 0){
           return true;
       } else {
           return false;
       }
    }

    @Override
    public int save(User entity) throws DAOException {
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getId(),entity.getLogin(),entity.getPassword(),entity.getIsInstructor()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("updateUser"), args);
       command.closeConnection();
      
       if(resultValue > 0){
         return resultValue;
       } else {
           return 0;
       }
    }
    
}
