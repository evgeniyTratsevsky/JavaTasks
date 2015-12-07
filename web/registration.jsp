<%-- 
    Document   : login
    Created on : 24.09.2015, 16:50:55
    Author     : old
--%>

<%@page contentType="text/html" pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="pragma" content="no-cache"/>
        <meta http-equiv="expires" content="0"/>
        <meta http-equiv="cache-control" content="no-cache"/>
        <title>JSP Page</title>
        <style>
            td,th {padding: 10px };    
            div.static {right: 5px; border: 3px 3px  solid #8AC007;};
        </style> 
    </head>    
    <body>
        <br>
        <br>
        <%@ include file="header.jsp" %>
        <br>
        <br>
    <center>        
        <h2>
            <c:if test="${not empty regSuccess}">
                <script language="JavaScript" type="text/JavaScript">
                    window.addEventListener("load", function () { alert("${regSuccess}");});
                </script>
            </c:if>
            <c:if test="${not empty regError}">
                <script language="JavaScript" type="text/JavaScript">
                    window.addEventListener("load", function () { alert("${regError}");});
                </script>
            </c:if>
            <c:out value="${addEditText}"/>                
        </h2>
        <form action="RegController" name="regForm" method=post>
            <table border=2 bgcolor="#ccdd7f">
                <tbody>
                <th colspan="2">Введите данные</th>
                <tr><td>Имя:</td><td><input type="text" size="15" name="name" value="${user.name}"></td></tr>
                <tr><td>Фамилия:</td><td><input type="text" size="15" name="surName" value="${user.surName}"></td></tr>
                <tr><td>Логин:</td><td><input type="text" size="15" name="login" value="${user.login}"></td></tr>
                <tr><td>Пароль:</td><td><input type="password" size="15" name="password" value="${user.password}"></td></tr>
                <tr><td>Подтверждение:</td><td><input type="password" size="15" name="passwordConfirm" value="${user.password}"></td></tr>
                <tr><td>email:</td><td><input type="text" size="15" name="email" value="${user.email}"></td></tr>
                <tr><td>тел. номер:</td><td><input type="text" size="15" name="phoneNumber" value="${user.phoneNumber}"></td></tr>                
                <tr>
                    <td colspan="2" align="center">
                        <c:if test="${empty addEditText}">
                            <input type="submit" name="buttonReg" value="Добавить">
                        </c:if>
                        <c:if test="${not empty addEditText}">
                            <input type="submit" name="buttonReg" value="Edit">
                        </c:if>
                    </td>
                </tr>
                <tr></tr>
                </tbody>
            </table>
        </form>
        <br>
        <br>
        <c:if test="${not empty regSuccess}">
            <c:out value="${regSuccess}"/>
            <a href="login.jsp">Войти</a>
        </c:if>
    </center>
</body>
</html>
