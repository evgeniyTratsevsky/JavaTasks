<%-- 
    Document   : import
    Created on : 28.09.2015, 1:07:32
    Author     : old
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="pragma" content="no-cache"/>
        <meta http-equiv="expires" content="0"/>
        <meta http-equiv="cache-control" content="no-cache"/>
        <title>JSP Page</title>
    </head>
    <c:if test="${not empty fileError}">
        <script language="JavaScript" type="text/JavaScript">
            window.addEventListener("load", function () { alert("${fileError}");});
        </script>
    </c:if>

    <body>
        <br>
        <br>
        <%@ include file="header.jsp" %>
        <br>
        <br>      
    <center>
        <h2>Импорт контактов</h2>
        <br> 

        <form action="FileUpload" enctype="multipart/form-data" method="POST">
            <table>
                <tr>
                    <td><input type="file" name="file" value="Выберите файл"></td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td><input type="submit" value="Загрузить файл"></td>
                </tr>
            </table>            
        </form>
        <br/>       
    </center>
</body>
</html>
