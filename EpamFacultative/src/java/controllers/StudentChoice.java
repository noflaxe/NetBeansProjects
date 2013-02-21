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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Course;
import model.CourseEntry;
import model.Student;
import org.apache.log4j.Logger;
import static innerStrings.WebStrings.getWebParameter;

/**
 *
 * @author noflaxe
 */
public class StudentChoice extends HttpServlet {

       
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Logger logger = (Logger)getServletContext().getAttribute("Log");
          HttpSession session = request.getSession(false);
        session.setAttribute("student_message", "");
        if (session.getAttribute("user") instanceof Student) {
            DAOFactory factory = new DAOFactory(logger);   
            Student student = (Student) session.getAttribute("user");
            if (request.getParameter("getList") != null) {
                try {
                    RequestDispatcher view = request.getRequestDispatcher(getWebParameter("StudentEntryJSP"));
                    CourseCRUD courseDao = factory.getCourseDAO();
                    List<Course> list = courseDao.getAllCourses();
                    request.setAttribute("datalist", list);
                    view.forward(request, response);
                } catch (DAOException ex) {
                    logger = (Logger)getServletContext().getAttribute("Daologger");
                    logger.error("Exception thrown!",ex);
                }

            } else if (request.getParameter("archive") != null) {
                try {
                    RequestDispatcher view = request.getRequestDispatcher(getWebParameter("StudentArchiveJSP"));
                    CourseEntryCRUD entryDAO = factory.getCourseEntryDAO();
                    List<CourseEntry> list = entryDAO.getByStudentArchive(student);
                    request.setAttribute("datalist", list);
                    view.forward(request, response);
                } catch (DAOException ex) {
                  logger = (Logger)getServletContext().getAttribute("Daologger");
                   logger.error("Exception thrown!",ex);
                }
            }
        } else {
              logger.info("unauthorized access attempt");
            RequestDispatcher view = request.getRequestDispatcher(getWebParameter("AccessDeniedJSP"));
            view.forward(request, response);
        }
    }
}
