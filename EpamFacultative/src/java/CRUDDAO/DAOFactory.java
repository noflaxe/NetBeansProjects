/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO;

import CRUDDAO.Interfaces.*;
import org.apache.log4j.Logger;

/**
 *
 * @author noflaxe
 */
public class DAOFactory {
    
    private Logger logger;
    
    public DAOFactory(Logger log){
    logger = log;
    }
    
    public CourseCRUD getCourseDAO(){
    return new CourseDAO(logger);
    }
    
    public CourseEntryCRUD getCourseEntryDAO(){
        return new CourseEntryDAO(logger);
    }
    
    public InstructorCRUD getInstructorDAO(){
        return new InstructorDAO(logger);
    }
    
    public StudentCRUD getStudentDAO(){
        return new StudentDAO(logger);
    }
    
    public UserCRUD getUserDAO(){
        return new UserDAO(logger);
    }
    
}
