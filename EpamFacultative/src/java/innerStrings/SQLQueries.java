/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package innerStrings;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author noflaxe
 */
public class SQLQueries {
    private static final String filepath = "Properties\\SQLQueries.properties";
    private static final Properties prop;
    
      static {
        prop = new Properties();
        try {          
            prop.load(new FileInputStream(filepath));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
      
      
       public static String getSQLCommand(String param) {
        return prop.getProperty(param);
     }
      
}
