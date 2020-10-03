<%-- 
    Document   : linksheader
    Created on : Feb 20, 2013, 3:55:00 PM
    Author     : Shreya Appia
    Description: Contains links which will be visible on all pages after Login
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="links">
    <h2>
        <a href="viewnews.htm" class="spacing">News for you</a>
        <a href="popularnews.htm" class="spacing">Popular News</a>
        <a href="postnews.htm" class="spacing">Post a News Link</a>
        <a href="friends.htm" class="spacing">Friends</a>
        <c:url value="/j_spring_security_logout" var="logoutUrl"/> 
        <a href="${logoutUrl}" class="spacing">Log Out | <c:out value="${currentUser}"/></a>
    </h2>
</div>
