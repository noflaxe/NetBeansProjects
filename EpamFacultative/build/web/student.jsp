<%-- 
    Document   : Student
    Created on : Feb 3, 2013, 2:18:54 PM
    Author     : noflaxe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student page</title>
        <link rel="stylesheet" type="text/css" href="view.css" media="all">
    <script type="text/javascript" src="view.js"></script>
    </head>
   <body id="main_body" >
       <div id="form_container">
            <form action="StudentChoice" method="Post" class="appnitro">
                 <div class="form_description">
                     <ul>	<h2>Hello,   <c:out value='${sessionScope.name}'/></h2>
			<p><c:out value='${sessionScope.student_message}'/></p> </ul>
		</div>
                <ul>
                     <li class="buttons">
                                
           <input id="saveForm" class="button_text" type="submit" name="getList" value="Make entry" />
                            </li>
                             <li class="buttons">
                                
     <input id="saveForm" class="button_text" type="submit" name="archive" value="View Grades Archive" />
                            </li>
                </ul>
            </form>
                   <div id="footer">
			
		</div>
       </div>
                 <img id="bottom" src="bottom.png" alt="">
    </body>
</html>
