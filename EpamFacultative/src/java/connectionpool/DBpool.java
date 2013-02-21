/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionpool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author noflaxe
 */
public class DBpool {
      public static DataSource datasource;
      private DBpool(){

}
      
       public static  DataSource getInstance()  {
         if (datasource == null) {
            try {
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                datasource = (DataSource) envCtx.lookup("jdbc/facultative");
            } catch (NamingException ex) {
                //Logger.getLogger(DELETE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return datasource;
    }
}
