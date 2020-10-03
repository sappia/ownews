<%-- 
    Document   : register
    Created on : Feb 13, 2013, 10:14:01 AM
    Author     : Shreya Appia
    Description: Allows new users to register
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
        <title>Registration Page</title>
    </head>
    <body>
    <div class="header" align="center"> 
        <h1>OwNews!</h1>
    </div>
    <div class="registration">
        <h2>New User? Register here:</h2>
            <h3>
            <c:url var="registerUrl" value="/register.htm" />
            <form:form modelAttribute="register" method="POST" action="${registerUrl}" onsubmit="return validateRegistration()">
                <fieldset style="width: 700px">
                    <table>
                        <tr><td><form:label path="username">Username:</form:label></td>
                            <td><form:input path="username" id="username" type="text" title="Choose a username, length between 5 and 20"/></td>
                            <td><span id="usernameError" style="color: red"></span></td>
                        </tr>
                        <tr><td><form:label path="password">Password:</form:label></td>
                            <td><form:input path="password" id="password" type="password" title="Choose a password, length between 5 and 10"/></td>
                            <td><span id="passwordError" style="color: red"></span></td>
                        </tr>
                        <tr>
                            <td><label>Confirm Password:</label></td>
                            <td><input id="confirmpassword" type="password" title="Retype password"/></td>
                            <td><span id="cnfrmpwdError" style="color: red"></span></td>
                        </tr>
                        <tr>
                            <td><form:label path="emailid">Email ID:</form:label></td>
                            <td><form:input path="emailid" id="emailid" type="text"title="Enter your Email ID"/></td>
                            <td><span id="emailError" style="color: red"></span></td>
                        </tr>
                        <tr>
                            <td><label>Confirm Email ID:</label></td>
                            <td><input id="confirmemailid" type="text" title="Retype your Email ID"/></td>
                            <td><span id="cnfrmemailError" style="color: red"></span></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Register" title="Click here to register"/></td>
                            <td><span id="allfieldsError" style="color: red"></span></td>
                        </tr>
                    </table>
                </fieldset>
            </form:form>
        </h3>
    </div>
    <div class="existinguser">
        <h2>
            <a href="login.htm" title="Click here to go to login page">Existing User? Click here to login</a>
        </h2>
    </div>
</body>
</html>
