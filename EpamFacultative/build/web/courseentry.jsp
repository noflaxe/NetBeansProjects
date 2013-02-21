<%-- 
    Document   : courseentry
    Created on : Feb 13, 2013, 10:38:31 AM
    Author     : noflaxe
--%>

<%@page import="model.Course"%>
<%@page import="model.Instructor"%>
<%@page import="com.sun.jmx.snmp.tasks.Task"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
        if (request.getAttribute("datalist") != null) {
            List<Course> datalist = (List<Course>) request.getAttribute("datalist");
            pageContext.setAttribute("datalist", datalist);          
        }
      
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entry page</title>
         <link rel="stylesheet" type="text/css" href="view.css" media="all">
    <script type="text/javascript" src="view.js"></script>
    </head>
   <body id="main_body" >
       <div id="form_container">
        <div class="form_description">
            <ul><h2>Choose the course you want to take part</h2>
			</ul>
		</div>	
           <ul>

         <table border="1">
<tr>
<th>Name</th>
<th>Instructor Name</th>
<th>Surname</th>
<th>Hours</th>
<th>Take part</th>
</tr>
 <c:forEach items="${datalist}" var="list">
<tr>
     <td>
          <c:out value="${list.name}"/> 
      </td> 
      <td><c:out value ="${list.instructor.name}"/></td>
       <td><c:out value ="${list.instructor.surname}"/></td>
       <td>
          <c:out value="${list.hours}"/> 
      </td>
      <td>      
        <a href="StudentCourseEntry?choice_id=<c:out value="${list.id}"/>">Join</a>
      </td>
</tr>
</c:forEach>
        </table>
           </ul>
       </div>
    </body>
</html>
