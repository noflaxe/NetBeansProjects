/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO.Interfaces;

import exceptions.DAOException;
import java.util.List;
import model.CourseEntry;
import model.Instructor;
import model.Student;

/**
 *
 * @author noflaxe
 */
public interface CourseEntryCRUD extends CRUD<CourseEntry> {
    
    public List<CourseEntry> getByInstructor(Instructor instructor) throws DAOException;
    public List<CourseEntry> getByInstructorActive(Instructor instructor) throws DAOException;
    public List<CourseEntry> getByStudent(Student student) throws DAOException;
    public List<CourseEntry> getByStudentArchive(Student student) throws DAOException;
    public CourseEntry getByStudentCourseId(Student student,int id) throws DAOException;
}
