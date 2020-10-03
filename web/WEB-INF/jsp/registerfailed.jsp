<%-- 
    Document   : registerfailed
    Created on : Feb 13, 2013, 10:41:15 AM
    Author     : Shreya Appia
    Description: Informs user if the registration was unsuccessful and lets them try again
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="CSS/OwnewsStyle.css" />
        <title>Registration Failure Page</title>
    </head>
    <body>      
        <div class="header" align="center"> 
            <h1>OwNews!</h1>
        </div>
        <div id="message">
            <h2>That username has already been registered, please choose a different name</h2>
        </div>
        <div id="tryagainlink">
            <h3>
                <a href ="register.htm" title="This link will take you to our registration page">Click here to try again</a>
            </h3>
        </div>
    </body>
</html>
