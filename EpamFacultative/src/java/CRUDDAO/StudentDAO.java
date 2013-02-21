/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO;

import CRUDDAO.Interfaces.StudentCRUD;
import exceptions.DAOException;
import innerStrings.SQLQueries;
import java.sql.SQLException;
import java.util.List;
import model.Student;
import model.User;
import org.apache.log4j.Logger;

/**
 *
 * @author noflaxe
 */
public class StudentDAO extends SQLDAO implements StudentCRUD{

    public StudentDAO(Logger log){
        super(log);
    }
    
    @Override
    public List<Student> getAll() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Student findByUser(User user) throws DAOException {        
            int id = user.getId();
            Student student = null;
     try {     
            Object args[] = {id};
            command.makeConnection();
            resultSet = command.getItems(SQLQueries.getSQLCommand("FindStudentByUser"), args);
            if(resultSet.next())
            {
            student = new Student();    
            student.setName(resultSet.getString("Name"));
            student.setSurname(resultSet.getString("Surname")); 
            student.setInstitute(resultSet.getString("Institute"));
            student.setId(resultSet.getInt("Id"));
            student.setUser(user);
            }
           
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
           logger.error("Exception thrown!",ex);
        }
      return student;
    }

    @Override
    public boolean delete(Student entity) throws DAOException {
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getId()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("deleteStudent"), args);
       if(resultValue > 0){
           return true;
       } else {
           return false;
       }
       
    }

    @Override
    public Student findById(int id) throws DAOException {
        Student student = new Student();
        try {
            command.makeConnection();
            Object[] args = {id};
            resultSet = command.getItems(SQLQueries.getSQLCommand("FindStudentById"), args);
            if(resultSet.next()){
                 student = new Student();
                 student.setId(id);
                 student.setInstitute(resultSet.getString("Institute"));
                 student.setName(resultSet.getString("Name"));
                 student.setSurname(resultSet.getString("Surname")); 
                 UserDAO userdao = new UserDAO(logger);
                 User user = userdao.findById(resultSet.getInt("Id_UserData"));
                 student.setUser(user);
            }
            command.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
           logger.error("Exception thrown!",ex);
        }
        return student;
    }

    @Override
    public boolean update(Student entity) throws DAOException {
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getInstitute(),entity.getId()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("updateStudent"), args);
       command.closeConnection();
      
       if(resultValue > 0){
           return true;
       } else {
           return false;
       }
    }

    @Override
    public int save(Student entity) throws DAOException {
        
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getId(),entity.getName(),entity.getSurname(),entity.getInstitute(),
       entity.getUser().getId()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("saveStudent"), args);
       command.closeConnection();
      
       if(resultValue > 0){
         return resultValue;
       } else {
           return 0;
       }
    }
    
}
