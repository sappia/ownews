<%-- 
    Document   : errorPage
    Created on : Feb 13, 2013, 10:06:03 AM
    Author     : Shreya Appia
    Description: This is a custom error page displayed for 404 Not found error and for any java exception
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="CSS/OwnewsStyle.css" />
        <title>Ownews - Error Page</title>
    </head>
    <body> 
        <div class="errormessage">
            <h1>Sorry! The page requested does not exist!</h1>
        </div>
        <br />
        <div class="loginpage">
            <h2>
                <a href="login.htm" title="This link will take you to our login page">Click here</a> to go to our website
            </h2>
        </div>
    </body>
</html>
