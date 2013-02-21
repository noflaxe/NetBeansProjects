/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO.Interfaces;

import exceptions.DAOException;
import java.util.List;
import model.Student;
import model.User;

/**
 *
 * @author noflaxe
 */
public interface StudentCRUD extends CRUD<Student> {
      public List<Student> getAll() throws DAOException;
      public Student findByUser(User user) throws DAOException;
}
