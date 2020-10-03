<%-- 
    Document   : popularnews
    Created on : May 28, 2013, 5:00:04 PM
    Author     : Shreya Appia
    Description: Shows popular news items to an authenticated user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="CSS/OwnewsStyle.css" />
        <title>Popular news</title>
    </head>
    <body>
        <%@include file="linksheader.jsp" %>
        <div class="head">
            <h1>Current popular news</h1>
        </div>
        <div id="popularnews">         
            <c:if test="${!empty popularnews}">
                <h2>
                    <table border="5" cellpadding="10">
                        <tr>
                            <th>Popular News links</th>
                            <th>Popularity Score</th>
                        </tr>
                        <c:forEach items="${popularnews}" var="news">
                            <tr>
                                <td><a href="<c:out value="${news.newslink}"/>" target="_blank"><c:out value="${news.newslink}"/></a></td>                    
                                <td><c:out value="${news.popularity}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </h2>
            </c:if>
            <c:if test="${empty popularnews}"><h1>There are no popular news items</h1></c:if>
        </div>
    </body>
</html>
