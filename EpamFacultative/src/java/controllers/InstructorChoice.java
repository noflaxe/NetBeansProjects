/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import CRUDDAO.DAOFactory;
import CRUDDAO.Interfaces.CourseEntryCRUD;
import exceptions.DAOException;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CourseEntry;
import model.Instructor;
import org.apache.log4j.Logger;
import static innerStrings.WebStrings.getWebParameter;

/**
 *
 * @author noflaxe
 */
public class InstructorChoice extends HttpServlet{
    
   
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException   {    
   HttpSession session = request.getSession(false);
     Logger logger = (Logger)getServletContext().getAttribute("Log");
    session.setAttribute("instructor_message", "");
   if(session.getAttribute("user") instanceof Instructor){
       DAOFactory factory = new DAOFactory(logger);
       Instructor instructor = (Instructor)session.getAttribute("user");
       try {
       if (request.getParameter("update") != null) {
            RequestDispatcher view = request.getRequestDispatcher(getWebParameter("InstructorGradeJSP"));
            CourseEntryCRUD entryDAO = factory.getCourseEntryDAO();
            List<CourseEntry> list = entryDAO.getByInstructorActive(instructor);
             request.setAttribute("datalist", list);
              view.forward(request, response);
          

    } else if (request.getParameter("archive") != null) {
          
               RequestDispatcher view = request.getRequestDispatcher(getWebParameter("InstructorArchiveJSP"));
                         CourseEntryCRUD entryDAO = factory.getCourseEntryDAO();
                         List<CourseEntry> list = entryDAO.getByInstructor(instructor);
                         request.setAttribute("datalist", list);
                         view.forward(request, response);
               view.forward(request, response);
           
           }
    } catch (DAOException ex) {
         RequestDispatcher view =  request.getRequestDispatcher(getWebParameter("ErrorJSP"));
           view.forward(request, response);
              logger.info("Exception thrown in InstructorChoice servlet");
               logger.error("Exception thrown!",ex);
   }
   } else {
       logger.info("unauthorized access attempt");
   RequestDispatcher view = request.getRequestDispatcher(getWebParameter("AccessDeniedJSP"));
          view.forward(request, response);
   }
       
   
       }
}
