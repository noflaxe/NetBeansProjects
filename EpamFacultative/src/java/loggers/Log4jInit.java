package loggers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class Log4jInit extends HttpServlet {

@Override
public void init() throws ServletException {
    //  System.out.println(config.getInitParameter("logfile"));
       String logfilename = getInitParameter("logfile");
       String pref;
       pref = getServletContext().getRealPath("/");
       String filepath = pref+logfilename;
        
    //    System.out.println("!!!!!!!!"+filepath);
       PropertyConfigurator.configure(filepath);
       
  
       Logger daolog = Logger.getLogger("Daologger");
       Logger globallog = Logger.getLogger("Log");
       getServletContext().setAttribute(new String("Daologger"), daolog);
       getServletContext().setAttribute(new String("Log"), globallog); 
     
       
       daolog.info("Load-onstart-up Servlet");

}
}