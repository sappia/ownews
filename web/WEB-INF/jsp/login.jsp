<%-- 
    Document   : Login
    Created on : Jan 8, 2013, 4:42:39 PM
    Author     : Shreya Appia
    Description: Allows a registered user to login
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="CSS/OwnewsStyle.css" />
        <script src="JS/validate.js" type="text/javascript"></script>
        <script src="JS/jquery.js" type="text/javaScript"></script>
        <title>Login Page</title>
    </head>
    <body onload='document.f.j_username.focus();'>
        <div class="header" align="center">                
            <h1>OwNews!</h1>
        </div>
        <div class="login">
            <h3>
                <c:if test="${!empty error}">
                    <div class="errorblock">
                        Your login attempt was not successful, try again.<br /> Cause :
                        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                    </div>
                </c:if>
            </h3>
            <h2>Existing User? Login here:</h2>
            <h3>
                <form modelAttribute="login" id="login_form" action="j_spring_security_check" method="post" onsubmit="return validateLogin()">
                    <fieldset style="width: 500px">
                        <table>
                            <tr>
                                <td><label for="j_username">Username:</label></td>
                                <td><input id="j_username" name="j_username" size="40" maxlength="50" type="text" title="Enter your username here" /></td>
                            </tr>
                            <tr>
                                <td><label for="j_password">Password:</label></td>
                                <td><input id="j_password" name="j_password" size="40" maxlength="50" type="password" title="Enter your password here" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Login" style="font-size:20px; width: 100px" title="Click here to login"/></td>
                                <td><span id="loginError" style="color: red"></span></td>
                            </tr>
                        </table>
                    </fieldset>

                </form>
            </h3>
        </div>
        <br />
        <div class="newuser">
            <h2>
                <a href="register.htm" title="Click here to register with us">New User? Click here to register</a>
            </h2>
        </div>

    </body>
</html>
