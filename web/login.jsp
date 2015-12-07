<%-- 
    Document   : login
    Created on : 24.09.2015, 16:50:55
    Author     : old
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>       
        <title>JSP Page</title>
        <style>
            td,th {padding: 10px };    
        </style>
        <script type="text/javascript" src="login.js"></script>    
    </head>     
    <c:if test="${not empty loginSuccess}">
        <script language="JavaScript" type="text/JavaScript">
            window.addEventListener("load", function () { alert("${loginSuccess}");});
        </script>
    </c:if>    
    <body>
        <%@ include file="header.jsp" %>
        <br>
        <br>
        <br>
    <center>
        <form action ="LoginController" name="LoginForm" method=post>
            <center>
                <table border=2 bgcolor="#ccdd7f">
                    <tbody>
                    <th colspan="2">Введите ваш данные</th>
                    <tr><td>Логин:</td><td><input type="text" size="15" name="login"></td></tr>
                    <tr><td>Пароль:</td><td><input type="password" size="15" name="password"></td></tr>
                    <tr><td colspan="2">
                    <center><input type="submit" name="buttonLogin" value="Вход">
                        <c:if test="${empty loginSuccess}">
                            &nbsp;&nbsp;<a href="registration.jsp">Регистрация</a>
                        </c:if>                        
                    </center></td></tr>
                    <tr></tr>
                    </tbody>
                </table>
                <br>
                <br>               
            </center>
        </form>
    </center>
</body>
</html>

