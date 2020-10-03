<%-- 
    Document   : postnews
    Created on : Feb 20, 2013, 5:54:26 PM
    Author     : Shreya Appia
    Description: Allows user to post news link
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/OwnewsStyle.css" />
        <script src="JS/validate.js" type="text/javascript"></script>
        <script src="JS/jquery.js" type="text/javaScript"></script>
        <title>Post News Link</title>
    </head>
    <body>
        <div id="postnews">
            <%@include file="linksheader.jsp" %>
            <h1>Post a news link here:</h1>
            <h2>
                <c:url var="addnewslink" value="/postnews.htm" />
                <form:form  modelAttribute="news" method="POST" action="${addnewslink}" onsubmit="return validateNewsLink()">
                    <fieldset>
                        <table>
                            <tr>
                                <td><form:label path="newslink">News Link:</form:label></td>
                                <td><form:input path="newslink" id="newslink" size="200" title="Past your news link here" /></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Post" title="Click here to post the link"/></td>
                            </tr>
                        </table>
                    </fieldset>
                </form:form>
            </h2>
        </div>
    </body>
</html>
