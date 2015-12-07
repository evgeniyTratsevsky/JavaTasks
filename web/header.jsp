<%-- 
    Document   : header
    Created on : 26.09.2015, 14:54:58
    Author     : old
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="pragma" content="no-cache"/>
        <meta http-equiv="expires" content="0"/>
        <meta http-equiv="cache-control" content="no-cache"/>
        <title>JSP Page</title>
    </head>
    <p align="right"><a href=""><c:out value="${sessionLogin}"/></a></p>
    <body>
        <br>
        <br>
    <center>
        <a href="header.jsp">Главная</a>
        <c:if test="${empty sessionLogin}">
            <a href="login.jsp">Войти</a>
        </c:if>        
        <a href="ContactsController?param=.">Список контактов</a>
        <a href="import.jsp">Импорт контактов</a>
        <a href="LogoutController">Выйти</a>
    </center>
</body>
</html>
