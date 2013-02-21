/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO;

import CRUDDAO.Interfaces.InstructorCRUD;
import exceptions.DAOException;
import innerStrings.SQLQueries;
import java.sql.SQLException;
import java.util.List;
import model.Instructor;
import model.User;
import org.apache.log4j.Logger;

/**
 *
 * @author noflaxe
 */
public class InstructorDAO extends SQLDAO implements InstructorCRUD{

    
    public InstructorDAO(Logger log){
       super(log);
    }
    
    @Override
    public List<Instructor> getAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Instructor findByUser(User user) throws DAOException {
       Instructor instructor = null;
            int id = user.getId();
     try {     
            Object args[] = {id};
            command.makeConnection();
            resultSet = command.getItems(SQLQueries.getSQLCommand("FindInstructorByUser"), args);
            if(resultSet.next())
            {
                instructor = new Instructor();
            instructor.setId(resultSet.getInt("Id"));
            instructor.setSpeciality(resultSet.getString("Speciality"));
            instructor.setName(resultSet.getString("Name"));
            instructor.setSurname(resultSet.getString("Surname")); 
            instructor.setUser(user);
            }
           
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
            logger.error("Exception thrown!",ex);
        }
      return instructor;
    }

    @Override
    public boolean delete(Instructor entity) throws DAOException {
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getId()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("deleteInstructor"), args);
       if(resultValue > 0){
           return true;
       } else {
           return false;
       }
       
    }

    @Override
    public Instructor findById(int id) throws DAOException {
       Instructor instructor = null;
        try {
            command.makeConnection();
            Object[] args = {id};
            resultSet = command.getItems(SQLQueries.getSQLCommand("FindInstructorById"), args);
            if(resultSet.next()){     
                 instructor = new Instructor();
                 instructor.setId(id);
                 instructor.setSpeciality(resultSet.getString("Speciality"));
                 instructor.setName(resultSet.getString("Name"));
                 instructor.setSurname(resultSet.getString("Surname")); 
                 UserDAO userdao = new UserDAO(logger);
                 User user = userdao.findById(resultSet.getInt("Id_UserData"));
                 instructor.setUser(user);
            }
            command.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
           logger.error("Exception thrown!",ex);
        }
        return instructor;
    }

    @Override
    public boolean update(Instructor entity) throws DAOException {
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getSpeciality(),entity.getId()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("updateInstructor"), args);
       command.closeConnection();
      
       if(resultValue > 0){
           return true;
       } else {
           return false;
       }
    }

    @Override
    public int save(Instructor entity) throws DAOException {
        
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getId(),entity.getName(),entity.getSurname(),entity.getSpeciality(),
       entity.getUser().getId()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("saveInstitute"), args);
       command.closeConnection();
      
       if(resultValue > 0){
         return resultValue;
       } else {
           return 0;
       }
    }
    
}
