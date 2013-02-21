<%-- 
    Document   : instructorupdate
    Created on : Feb 12, 2013, 10:16:21 PM
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
         //   request.setAttribute("datalist", datalist);
        }
      
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instructor update page</title>
       <link rel="stylesheet" type="text/css" href="view.css" media="all">
    <script type="text/javascript" src="view.js"></script>
    </head>
    <body id="main_body" >
       <div id="form_container">
        <div class="form_description">
            <ul><h2>This is grades control center</h2>
                <p> <c:out value='${sessionScope.instructor_message}'/></p>
			</ul>
		</div>	
           <ul>

    
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
    <form action="InstructorUpdate" method="POST">
           <input type="hidden" name="id" value="${list.id}">
      <select name="grade" id="grade" onchange="this.form.submit()" >
        <option value="IN_PROGRESS">IN PROGRESS</option>
        <option value="A">A</option>
        <option value="B">B</option>
        <option value="C">C</option>
        <option value="D">D</option>
        <option value="E">E</option>
        <option value="F">F</option>
    </select>
    </form>
  
      </td>
</tr>
</c:forEach>
  <c:set var="id" value="${4}" scope="request" />
           </ul>
       </div>
    </body>
</html>
