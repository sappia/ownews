<%-- 
    Document   : friends
    Created on : Apr 10, 2013, 10:20:37 AM
    Author     : Shreya Appia
    Description: Shows user's friends, suggestion for new friends and allows user to add friends
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
            function validatechoice() {
                var suggName = $('#frndsugg option:selected').val();
                if(suggName=='NONE') {
                    alert("Please select a suggestion");
                    return false;
                }
                else
                    populatefrnds(suggName);
            }
            function populatefrnds(suggName) {
                $.ajax({
                    datatype:"json",
                    type:"GET",
                    url:"${pageContext.request.contextPath}/friends/getfrnds.htm",
                    data:"suggName="+suggName,
                    success: function(data)
                    {
                        showUsers(data);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(jqXHR + " : " + textStatus + " : " + errorThrown); 
                    }
                });
            }
            function showUsers(data) {
                var html = '<option value="NONE">Select Friend</option>';
                var len = data.length;
                for ( var i = 0; i < len; i++) {
                    html += '<option value="' + data[i].userid + '">'
                        + data[i].username + '</option>';
                }
                html += '</option>';
                $('#friends').html(html);
            }
        </script>
        <title>Friends</title>
    </head>
    <body>
        <%@include file="linksheader.jsp" %>
        <div class="head1">
            <h1>Friends you may want to add</h1>
        </div>
        <div id="addfriends">           
            <c:url var="addFriend" value="/friends.htm" />
            <h2>
                Suggestions:
                <form:form modelAttribute="addfriendform" method="POST" action="${addFriend}" onsubmit="return validateFriend()">
                    <form:select path="friendsuggestions.suggestionValue" id="frndsugg" onchange="validatechoice()">
                        <form:option value="NONE" label="--- Select ---"/>
                        <form:options items="${suggestions}" itemValue="suggestionValue" itemLabel="suggestionName" />
                    </form:select>
                    <form:select id="friends" path="relationship.friendid">
                    </form:select>                        
                    <input type="submit" style="font-size:20px; width: 100px" value="Add Friend" title="Click here to add friend"/>
                </form:form>
            </h2>
        </div>
        <br /><br />
        <div class="head2">
            <h1>Your Friends</h1>
        </div>
        <div id="yourfriends">           
            <c:if test="${!empty yourfriends}">
                <h3>
                    <table border="5" cellpadding="10">              
                        <c:forEach items="${yourfriends}" var="login">
                            <tr>
                                <td><c:out value="${login.username}"/></td>
                            </tr>
                        </c:forEach>
                    </table>
                </h3>
            </c:if>
            <c:if test="${empty yourfriends}"><h2>You have no friends</h2></c:if>
        </div>
    </body>
</html>