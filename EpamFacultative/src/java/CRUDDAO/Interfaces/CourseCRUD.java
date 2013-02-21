/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO.Interfaces;

import exceptions.DAOException;
import java.util.List;
import model.Course;

/**
 *
 * @author noflaxe
 */
public interface CourseCRUD extends CRUD<Course> {

 //   public Course findByName(String name)
 //           throws  DAOException;
    public List<Course> getAllCourses() throws DAOException;
  
}
