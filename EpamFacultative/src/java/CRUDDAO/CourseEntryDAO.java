/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO;

import CRUDDAO.Interfaces.CourseEntryCRUD;
import exceptions.DAOException;
import innerStrings.SQLQueries;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import model.Course;
import model.CourseEntry;
import model.GradeEnum;
import model.Instructor;
import model.Student;
import org.apache.log4j.Logger;

/**
 *
 * @author noflaxe
 */
public class CourseEntryDAO extends SQLDAO implements CourseEntryCRUD{

    
    public CourseEntryDAO(Logger log){
        super(log);
    }
    @Override
    public List<CourseEntry> getByInstructor(Instructor instructor) throws DAOException {
        List<CourseEntry> courseEntryList = new LinkedList<CourseEntry>();
        try {    
            command.makeConnection();
            Object args[] = {instructor.getId()};
            resultSet = command.getItems(SQLQueries.getSQLCommand("getArchiveCourseEntriesByInstructor"), args); 
             StudentDAO studentdao = new StudentDAO(logger);
             CourseDAO coursedao = new CourseDAO(logger);     
            while(resultSet.next())
            {
            CourseEntry courseEntry = new CourseEntry();
                 courseEntry.setId(resultSet.getInt("Id"));            
                 Student student = studentdao.findById(resultSet.getInt("Id_Student"));
                 courseEntry.setStudent(student);
                 courseEntry.setGrade(GradeEnum.getGradeFromString(resultSet.getString("Grade")));        
                 Course course = coursedao.findById(resultSet.getInt("Id_Course"));
                 courseEntry.setCourse(course);
                 courseEntryList.add(courseEntry);
            }
           
        } catch (SQLException ex) {
             logger.info("Sql exception thrown");
             logger.error("Exception thrown!",ex);
        }
         return courseEntryList;
    }

    @Override
    public List<CourseEntry> getByStudent(Student student) throws DAOException {
          List<CourseEntry> courseEntryList = new LinkedList<CourseEntry>();
        try {    
            command.makeConnection();
            Object args[] = {student.getId()};
            resultSet = command.getItems(SQLQueries.getSQLCommand("getActiveCourseEntriesByStudent"), args);            
             CourseDAO coursedao = new CourseDAO(logger);     
            while(resultSet.next())
            {
            CourseEntry courseEntry = new CourseEntry();
                 courseEntry.setId(resultSet.getInt("Id"));                      
                 courseEntry.setStudent(student);
                 courseEntry.setGrade(GradeEnum.getGradeFromString(resultSet.getString("Grade")));        
                 Course course = coursedao.findById(resultSet.getInt("Id_Course"));
                 courseEntry.setCourse(course);
                 courseEntryList.add(courseEntry);
            }
           
        } catch (SQLException ex) {
             logger.info("Sql exception thrown");
          logger.error("Exception thrown!",ex);
        }
         return courseEntryList;
    }

    @Override
    public List<CourseEntry> getByStudentArchive(Student student) throws DAOException {
          List<CourseEntry> courseEntryList = new LinkedList<CourseEntry>();
        try {    
            command.makeConnection();
            Object args[] = {student.getId()};
            resultSet = command.getItems(SQLQueries.getSQLCommand("getArchiveCourseEntriesByStudent"), args);            
            CourseDAO coursedao = new CourseDAO(logger);     
            while(resultSet.next())
            {
            CourseEntry courseEntry = new CourseEntry();
                 courseEntry.setId(resultSet.getInt("Id"));                      
                 courseEntry.setStudent(student);
                 courseEntry.setGrade(GradeEnum.getGradeFromString(resultSet.getString("Grade")));        
                 Course course = coursedao.findById(resultSet.getInt("Id_Course"));
                 courseEntry.setCourse(course);
                 courseEntryList.add(courseEntry);
            }
           
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
           logger.error("Exception thrown!",ex);
        }
         return courseEntryList;
    }

    @Override
    public boolean delete(CourseEntry entity) throws DAOException {
         int resultValue;
       command.makeConnection();
       Object args[] = {entity.getId()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("deleteCourseEntry"), args);
       if(resultValue > 0){
           return true;
       } else {
           return false;
       }
       
    }

    @Override
    public CourseEntry findById(int id) throws DAOException {
        CourseEntry courseEntry = null;
        try {
            command.makeConnection();
            Object[] args = {id};
            resultSet = command.getItems(SQLQueries.getSQLCommand("FindCourseEntryById"), args);
            if(resultSet.next()){
                 courseEntry = new CourseEntry();
                 courseEntry.setId(id);
                 StudentDAO studentdao = new StudentDAO(logger);
                 Student student = studentdao.findById(resultSet.getInt("Id_Student"));
                 courseEntry.setStudent(student);
                 courseEntry.setGrade(GradeEnum.getGradeFromString(resultSet.getString("Grade")));
                 CourseDAO coursedao = new CourseDAO(logger);     
                 Course course = coursedao.findById(resultSet.getInt("Id_Course"));
                 courseEntry.setCourse(course);
                
            }
            command.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
          logger.error("Exception thrown!",ex);
        }
        return courseEntry;
    }

    @Override
    public boolean update(CourseEntry entity) throws DAOException {
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getGrade().name(),entity.getId()}; // check this if error happens
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("updateCourseEntryGrade"), args);
       command.closeConnection();
      
       if(resultValue > 0){
           return true;
       } else {
           return false;
       }
    }

    @Override
    public int save(CourseEntry entity) throws DAOException {
       int resultValue;
       command.makeConnection();
       Object args[] = {entity.getCourse().getId(),entity.getStudent().getId(),entity.getGrade().name()};
       resultValue = command.executeQuery(SQLQueries.getSQLCommand("saveCourseEntry"), args);
       command.closeConnection();
      
       if(resultValue > 0){
         return resultValue;
       } else {
           return 0;
       }
    }

    @Override
    public CourseEntry getByStudentCourseId(Student student, int id) throws DAOException {
           CourseEntry entry = null;
        try {
           
            command.makeConnection();
            Object args[] = {student.getId(),id};
            resultSet = command.getItems(SQLQueries.getSQLCommand("getCourseEntryByIdStudentCourse"), args);
             if(resultSet.next()){
             CourseDAO coursedao = new CourseDAO(logger);     
             Course course = coursedao.findById(id);
             entry = new CourseEntry();
             entry.setId(resultSet.getInt("Id"));
             entry.setCourse(course);              
             entry.setGrade(GradeEnum.getGradeFromString(resultSet.getString("Grade")));
             return entry;
             }
        } catch (SQLException ex) {
             logger.info("Sql exception thrown");
           logger.error("Exception thrown!",ex);
        }
        return entry;
    }

    @Override
    public List<CourseEntry> getByInstructorActive(Instructor instructor) throws DAOException {
         List<CourseEntry> courseEntryList = new LinkedList<CourseEntry>();
        try {    
            command.makeConnection();
            Object args[] = {instructor.getId()};
            resultSet = command.getItems(SQLQueries.getSQLCommand("getActiveCourseEntriesByInstructor"), args); 
             StudentDAO studentdao = new StudentDAO(logger);
              CourseDAO coursedao = new CourseDAO(logger);     
            while(resultSet.next())
            {
            CourseEntry courseEntry = new CourseEntry();
                 courseEntry.setId(resultSet.getInt("Id"));            
                 Student student = studentdao.findById(resultSet.getInt("Id_Student"));
                 courseEntry.setStudent(student);
                 courseEntry.setGrade(GradeEnum.getGradeFromString(resultSet.getString("Grade")));        
                 Course course = coursedao.findById(resultSet.getInt("Id_Course"));
                 courseEntry.setCourse(course);
                 courseEntryList.add(courseEntry);
            }
           
        } catch (SQLException ex) {
           logger.info("Sql exception thrown");
         logger.error("Exception thrown!",ex);
        }
         return courseEntryList;
    }
    
}
