/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import CRUDDAO.DAOFactory;
import CRUDDAO.Interfaces.InstructorCRUD;
import CRUDDAO.Interfaces.StudentCRUD;
import CRUDDAO.Interfaces.UserCRUD;
import exceptions.DAOException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Instructor;
import model.Student;
import model.User;
import org.apache.log4j.*;
import static innerStrings.WebStrings.getWebParameter;

/**
 *
 * @author noflaxe
 */
public class Authorization extends HttpServlet {

    
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    
     Logger logger = (Logger)getServletContext().getAttribute("Log");
     String login = request.getParameter("name");
     String password = request.getParameter("password");  
     logger.info("Login attempt with Login: "+login);
     RequestDispatcher view = request.getRequestDispatcher(getWebParameter("authorizationJSP")); 
     HttpSession session = request.getSession(false);
     DAOFactory factory = new DAOFactory(logger);     
     UserCRUD userdao = factory.getUserDAO();
        try {
           User user = userdao.findByLogin(login);
           if(user == null || !user.getPassword().equals(password)){
           //no user with this name and password
               session = request.getSession(true);
               session.setAttribute("message", "no user with this name and password");
               logger.info("login failed");
               view.forward(request, response);
           } else 
           {
           if(user.getIsInstructor()){
               session.setAttribute("isLoggedIn",true);
               session.setAttribute("message", "");
               InstructorCRUD instructorDao = factory.getInstructorDAO();
               Instructor instructor = instructorDao.findByUser(user);
               session.setAttribute("user",instructor);
               session.setAttribute("name",instructor.getName()+" "+instructor.getSurname());
               logger.info("instructor login successful");
           view = request.getRequestDispatcher(getWebParameter("InstructorJSP"));
           view.forward(request, response);
           } else {
               session.setAttribute("isLoggedIn",true);
               session.setAttribute("message", "");
               StudentCRUD studentDao = factory.getStudentDAO();
               Student student = studentDao.findByUser(user);
               session.setAttribute("user",student);
               session.setAttribute("name",student.getName()+" "+student.getSurname());
                 logger.info("student login successful");
           view = request.getRequestDispatcher(getWebParameter("StudentJSP"));
           view.forward(request, response);
           }
           }
        } catch (DAOException ex) {
           view = request.getRequestDispatcher(getWebParameter("ErrorJSP"));
           view.forward(request, response);
            logger.info("Exception thrown in Authorization servlet");
            logger.error("Exception thrown!",ex);
        }

           
    }
}
