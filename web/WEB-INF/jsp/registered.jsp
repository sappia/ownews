<%-- 
    Document   : registered
    Created on : Feb 13, 2013, 10:20:08 AM
    Author     : Shreya Appia
    Description: Confirms that the registration was successful
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="CSS/OwnewsStyle.css" />
        <title>Registered Page</title>
    </head>
    <body>
        <div class="header" align="center"> 
            <h1>OwNews!</h1>
        </div>
        <div id="message">
            <h2>
                Thank you for registering with us!<br/><br/>
                Login to visit your news page<br/><br/>
            </h2>
        </div>
        <div id="homelink">
            <h2>
                <a href="login.htm" title="This link will take you to our login page">Click here to go to Login page</a>
            </h2>
        </div>
    </body>
</html>
