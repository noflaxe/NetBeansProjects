/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO.Interfaces;

import exceptions.DAOException;
import java.util.List;

import model.Instructor;
import model.User;

/**
 *
 * @author noflaxe
 */
public interface InstructorCRUD extends CRUD<Instructor> {
      public List<Instructor> getAll() throws DAOException;
       public Instructor findByUser(User user) throws DAOException;
}
