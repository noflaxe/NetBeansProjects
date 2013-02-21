/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO.Interfaces;

import exceptions.DAOException;
import model.User;

/**
 *
 * @author noflaxe
 */
public interface UserCRUD extends CRUD<User>{
    public User findByLogin(String login) throws DAOException;
}
