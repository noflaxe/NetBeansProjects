package CRUDDAO.Interfaces;

import exceptions.DAOException;


/**
 *
 * @author noflaxe
 */

public interface CRUD<T> {

    public boolean delete(T entity)
            throws  DAOException;

    public T findById(int id)
            throws DAOException;

    public boolean update(T entity)
            throws DAOException;

    public int save(T entity)
            throws DAOException;
}
