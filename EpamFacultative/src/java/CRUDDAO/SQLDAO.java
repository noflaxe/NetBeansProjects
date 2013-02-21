/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;


/**
 *
 * @author noflaxe
 */
public abstract class SQLDAO {
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    protected BasicCRUD command;
    protected Logger logger;
    
    public SQLDAO(Logger log){
    command = new BasicCRUD(log);
    }
}
