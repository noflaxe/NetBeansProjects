/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import CRUDDAO.DAOFactory;
import CRUDDAO.Interfaces.CourseCRUD;
import CRUDDAO.Interfaces.CourseEntryCRUD;
import exceptions.DAOException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Course;
import model.CourseEntry;
import model.GradeEnum;
import model.Student;
import org.apache.log4j.Logger;
import static innerStrings.WebStrings.getWebParameter;
/**
 *
 * @author noflaxe
 */
public class StudentCourseEntry extends HttpServlet {
    
            

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        
    Logger logger = (Logger)getServletContext().getAttribute("Log");
     RequestDispatcher view = request.getRequestDispatcher(getWebParameter("authorizationJSP"));
     HttpSession session = request.getSession(false);
     DAOFactory factory = new DAOFactory(logger);
     CourseEntryCRUD entryDao = factory.getCourseEntryDAO();
        if(session.getAttribute("user") instanceof Student == false){
        view = request.getRequestDispatcher(getWebParameter("AccessDeniedJSP"));
            try {           
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
                
                Student student = (Student)session.getAttribute("user");
                int id = Integer.parseInt(request.getParameter("choice_id"));
                CourseCRUD courseDao = factory.getCourseDAO();
                Course course = courseDao.findById(id);
                CourseEntry entry = entryDao.getByStudentCourseId(student, id);
                if(entry == null){
                entry = new CourseEntry();
                entry.setStudent(student);
                entry.setGrade(GradeEnum.IN_PROGRESS);
                entry.setCourse(course);
                entry.setId(id);
                int k = entryDao.save(entry);
               session.setAttribute("student_message", "Entry was successfuly made");
                view = request.getRequestDispatcher(getWebParameter("StudentJSP"));
                view.forward(request, response);
                } else if ("IN_PROGRESS".equals(entry.getGrade().name())) {
                session.setAttribute("student_message", "You are taking part in this course already");
                view = request.getRequestDispatcher(getWebParameter("StudentJSP"));
                view.forward(request, response);
                } else {
                session.setAttribute("student_message", "You have finished this course already");
                view = request.getRequestDispatcher(getWebParameter("StudentJSP"));
                view.forward(request, response);
                }    
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
        }
    
     
    }
}
