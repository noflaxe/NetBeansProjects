<%-- 
    Document   : index
    Created on : Feb 3, 2013, 1:41:31 PM
    Author     : noflaxe
--%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" type="text/css" href="view.css" media="all">
                <script type="text/javascript" src="view.js"></script>
        <title>Login Page</title>
    </head>
<body id="main_body" >
  
        <div id="form_container">
      <form action="Authorization" method="Post" class="appnitro">    
          <div class="form_description">
			<h2>Facultative login page</h2>
			<p><c:out value='${sessionScope.message}'/></p>
		</div>		
                	<ul >
                            <li id="li_1" >
                 <label class="description" for="element_1">Enter your Login below </label>
                 <div>
            	<input id="element_1" name="name" class="element text medium" type="text" maxlength="255" value=""/> 
                     </div>
                 </li>
                            
                     <li id="li_2" >
                 <label class="description" for="element_2">Password </label>
                 <div>
            	<input id="element_2" name="password" class="element text medium" type="password" maxlength="255" value=""/> 
                     </div>
                 </li>
                            <li class="buttons">
                                
                                <input id="saveForm" class="button_text" type="submit" name="submit" value="Login" />
                            </li>
                            </ul>

                                                 </form>

                    </div>
                    <img id="bottom" src="bottom.png" alt="" >
  
    </body>
</html>
