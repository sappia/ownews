<%-- 
    Document   : viewnews
    Created on : Feb 20, 2013, 3:08:09 PM
    Author     : Shreya Appia
    Description: Shows news inbox of an authenticated user
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
        <script>
            function changeradio(){
            <c:forEach items = "${votednews}" var="votedposts">
                    document.getElementById("newsid"+${votedposts.newsid}).disabled=true;
                    document.getElementById("newsid"+${votedposts.newsid}).title="You have already voted for this item";
            </c:forEach>
                }
        </script>
        <script>
            function validateNewsVote(){
                var rating = $('#newsrating').val();
                if(rating=='NONE') {
                    alert("Please select a rating");
                    return false;
                }
                else if (!checkradio()) {
                    alert("Please choose a news item");
                    return false;
                }
                else
                    return true;
            }
            function checkradio(){
                var count = 0;
            <c:forEach items="${vnews}" var="news">
                    if(document.getElementById("newsid"+${news.newsid}).checked == true) {
                        count = count + 1;
                    }
            </c:forEach>
                    if(count == 0){
                        return false;
                    }
                    else {
                        return true;
                    }
                }
        </script>
        <title>News for you</title>
    </head>
    <body onload="changeradio()">
        <%@include file="linksheader.jsp" %>
        <div class ="friendsnote">
            <c:if test="${needfriends==1}">
                <h4>NOTE: You have very few friends! Add more friends to see more news!</h4>
            </c:if>
            <c:if test="${needfriends==2}">
                <h4>NOTE: Add friends, share news and vote for news items for a great experience on Ownews!</h4>
            </c:if>
        </div>
        <c:if test="${!empty radioerror}">
            <div class="errorblock">
                <h4>Please select a news item to vote!</h4>
            </div>
        </c:if>
        <div class="head">
            <h1>Your news Inbox</h1>
        </div>
        <div id="newsInbox">           
            <c:url var="updateVotes" value="/viewnews.htm" />
            <c:if test="${!empty vnews}">
                <h2>
                    <form:form modelAttribute="votingform" method="POST" action="${updateVotes}" onsubmit="return validateNewsVote()">
                        <table border="5" cellpadding="10">
                            <tr>
                                <th>News Link</th>
                                <th>Popularity Score</th>
                                <th>Choose to Vote</th>
                            </tr>
                            <c:forEach items="${vnews}" var="news">
                                <tr>
                                    <td><a href="<c:out value="${news.newslink}"/>" target="_blank"><c:out value="${news.newslink}"/></a></td>
                                    <td><c:out value="${news.popularity}"/></td>
                                    <td><form:radiobutton path="news.newsid" id="newsid${news.newsid}" value="${news.newsid}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <br />
                        <form:label path="rating.ratingValue">Rate the news item:</form:label>
                        <form:select path="rating.ratingValue" id="newsrating">
                            <form:option value="NONE" label="--- Select ---"/>
                            <form:options items="${ratings}" itemValue="ratingValue" itemLabel="ratingName" />
                        </form:select>
                        <input type="submit" style="font-size:20px; width: 300px"  value="Submit Rating" title="Click here to submit your vote"/>
                    </form:form>
                </h2>
            </c:if>
            <c:if test="${empty vnews}"><h1>Your news inbox is empty! Add friends to see news!</h1></c:if>

        </div>
    </body>
</html>
