    <%-- 
    Document   : instructorarchive
    Created on : Feb 12, 2013, 10:16:40 PM
    Author     : noflaxe
--%>
<%@page import="model.CourseEntry"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
        if (request.getAttribute("datalist") != null) {
            List<CourseEntry> datalist = (List<CourseEntry>) request.getAttribute("datalist");
            pageContext.setAttribute("datalist", datalist);          
        }
      
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instructor page</title>
         <link rel="stylesheet" type="text/css" href="view.css" media="all">
    <script type="text/javascript" src="view.js"></script>
    </head>
   <body id="main_body" >
       <div id="form_container">
        <div class="form_description">
            <ul><h2>This is the archive</h2>
			<p>Your grades store here</p></ul>
		</div>	
           <ul >
       <table border="1">
<tr>
<th>Name</th>
<th>Student Name</th>
<th>Student Surname</th>
<th>Hours</th>
<th>Grade</th>
</tr>
 <c:forEach items="${datalist}" var="list">
<tr>
       <td>
          <c:out value="${list.course.name}"/> 
      </td> 
       <td>
          <c:out value="${list.student.name}"/> 
      </td>
      <td>
          <c:out value="${list.student.surname}"/> 
      </td>
      <td>
          <c:out value="${list.course.hours}"/> 
      </td>
       <td>
          <c:out value="${list.grade}"/> 
      </td>
</tr>
</c:forEach>
           </ul>
       </div>
    </body>
</html>
