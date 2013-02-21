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
import model.GradeEnum;
import model.Instructor;
import org.apache.log4j.Logger;
import static innerStrings.WebStrings.getWebParameter;

/**
 *
 * @author noflaxe
 */
public class InstructorUpdate extends HttpServlet {
    
       
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response){
          Logger logger = (Logger)getServletContext().getAttribute("Log");
        HttpSession session = request.getSession(false);
   if(session.getAttribute("user") instanceof Instructor == false){
             try {
                 RequestDispatcher view = request.getRequestDispatcher(getWebParameter("AccessDeniedJSP")); 
               view.forward(request, response);
             } catch (ServletException ex) {
                 logger.info("Servlet exception thrown");
                logger.error("Exception thrown!",ex);
             } catch (IOException ex) {
                 
                logger.info("IOexception thrown");
              logger.error("Exception thrown!",ex);
             }
      } else {
             try {
                 session.setAttribute("instructor_message", "Grade successfully edited");
              int id = Integer.parseInt(request.getParameter("id"));
        //         System.out.println("777777");
         //        System.out.println(request.getParameter("id"));
         //        System.out.println(request.getParameter("grade"));
            
                 GradeEnum grade = GradeEnum.getGradeFromString((String)request.getParameter("grade"));
                 DAOFactory factory = new DAOFactory(logger);
                 CourseEntryCRUD entryDao = factory.getCourseEntryDAO();
                 CourseEntry entry = new CourseEntry();
               //  entry.setId(id);
                 entry.setGrade(grade);
                 entry.setId(id);
                 boolean k = entryDao.update(entry);
                 Instructor instructor = (Instructor) session.getAttribute("user");
                
                List<CourseEntry> list = entryDao.getByInstructorActive(instructor);
                request.setAttribute("datalist", list);
                RequestDispatcher view = request.getRequestDispatcher(getWebParameter("InstructorGradeJSP"));
                
                 logger.info("grade "+grade+" was set for entry "+id);
                 view.forward(request, response);
             } catch (ServletException ex) {
                 logger.info("Servlet exception thrown");
                 logger.error("Exception thrown!",ex);
             } catch (IOException ex) {
                    logger.info("IOexception thrown");
                 logger.error("Exception thrown!",ex);
             } catch (DAOException ex) {
                 logger = (Logger)getServletContext().getAttribute("Daologger");
                  logger.error("Exception thrown!",ex);
             }
   }}
}
