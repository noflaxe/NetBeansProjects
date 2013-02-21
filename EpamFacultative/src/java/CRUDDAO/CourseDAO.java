/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO;

import CRUDDAO.Interfaces.CourseCRUD;
import exceptions.DAOException;
import innerStrings.SQLQueries;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Course;
import model.Instructor;
import org.apache.log4j.Logger;


/**
 *
 * @author noflaxe
 */
public class CourseDAO extends SQLDAO implements CourseCRUD {

    public CourseDAO(Logger log){
    super(log);
    }
    
    @Override
    public List<Course> getAllCourses() throws DAOException {
         List<Course> CourseList = new LinkedList<Course>();
        try {    
            command.makeConnection();
            Object args[] = {};
            resultSet = command.getItems(SQLQueries.getSQLCommand("getAllCourses"), args);
            InstructorDAO instrucrordao = new InstructorDAO(logger);
            while(resultSet.next())
            {
            Course course = new Course();
            course.setHours(resultSet.getInt("Hours"));
            course.setName(resultSet.getString("Name"));
            course.setId(resultSet.getInt("Id"));
            Instructor instructor = instrucrordao.findById(resultSet.getInt("Id_Instructor"));
            course.setInstructor(instructor);
            CourseList.add(course);
            }
           
        } catch (SQLException ex) {
           logger.info("Sql exception thrown");
           logger.error("Exception thrown!",ex);         
        }
         return CourseList;
    }

    @Override
    public boolean delete(Course entity) throws DAOException {
         int resultValue;
       command.makeConnection();
       Object args[] = {entity.getId()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("deleteCourse"), args);
       if(resultValue > 0){
           return true;
       } else {
           return false;
       }
       
    }

    @Override
    public Course findById(int id) throws DAOException {
       Course course = null;
        try {
            command.makeConnection();
            Object[] args = {id};
            resultSet = command.getItems(SQLQueries.getSQLCommand("FindCourseById"), args);
            if(resultSet.next()){
                 course = new Course();
                 course.setId(id);
                 course.setHours(resultSet.getInt("Hours"));
                 course.setName(resultSet.getString("Name"));        
                 InstructorDAO instrucrordao = new InstructorDAO(logger);
                 Instructor instructor = instrucrordao.findById(resultSet.getInt("Id_Instructor"));
                 course.setInstructor(instructor);
            }
            command.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
           logger.error("Exception thrown!",ex);
        }
        return course;
    }

    @Override
    public boolean update(Course entity) throws DAOException {
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getInstructor().getId(),entity.getId()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("updateCourseInstructor"), args);
       command.closeConnection();
      
       if(resultValue > 0){
           return true;
       } else {
           return false;
       }
    }

    @Override
    public int save(Course entity) throws DAOException {
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getId(),entity.getName(),entity.getHours(),entity.getInstructor().getId()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("saveCourse"), args);
       command.closeConnection();
      
       if(resultValue > 0){
         return resultValue;
       } else {
           return 0;
       }
    }
    
}
