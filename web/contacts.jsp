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
        <link href="http://mottie.github.io/tablesorter/css/theme.default.css" rel="stylesheet">
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.9.1/jquery.tablesorter.min.js"></script>

        <script>
            $(function () {
                $("#sortTable").tablesorter({widgets: ['zebra']});
            });
        </script>        
    </head>      
    <c:if test="${not empty radioError}">
        <script language="JavaScript" type="text/JavaScript">
            window.addEventListener("load", function () { alert("${radioError}");});
        </script>
    </c:if>
    <c:if test="${not empty uploadRes}">
        <script language="JavaScript" type="text/JavaScript">
            window.addEventListener("load", function () { alert("${uploadSuccess}");});
        </script>
    </c:if>
    <c:if test="${not empty loginSuccess}">
        <script language="JavaScript" type="text/JavaScript">
            window.addEventListener("load", function () { alert("${loginSuccess}");});
        </script>
    </c:if>    
    <script language="JavaScript" type="text/JavaScript">
        function init() {
        var sorter = tsorter.create('TABLE-ID');
        }
        window.onload = init;
    </script>
    <body>
        <br>
        <%@ include file="header.jsp" %>
    <center>   
        <h2>Список контактов</h2>                
        <form name="addEditForm" action="ContactsController" method="GET">            
            <input type="submit" name="action" value="Добавить контакт"/>
            <br>
            <br>
            <table id="sortTable" border="1" border="0" cellpadding="0" cellspacing="1" >
                <thead>
                    <tr>            
                        <th>№</th>
                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>Логин</th>
                        <th>Пароль</th>
                        <th>e-mail</th>
                        <th>Тел. номер</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <c:forEach items="${users}" var="user">                
                    <tr>                        
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.surName}</td>
                        <td>${user.login}</td>
                        <td>${user.password}</td>
                        <td>${user.email}</td>                    
                        <td>${user.phoneNumber}</td>
                        <td><input type="radio" name="radio" value="${user.id}"/></td>
                        <td>
                            <input type="submit" name="action" value="Редактировать"/>
                        </td>
                    </tr>
                </c:forEach>    
            </table>
            <br>                    
            <input type="submit" name="page" value="1"/>&nbsp;>&nbsp;
            <input type="submit" name="page" value="2"/>&nbsp;>&nbsp;
            <input type="submit" name="page" value="3"/>&nbsp;>&nbsp;
            <input type="submit" name="page" value="4"/>&nbsp;>&nbsp;
            <input type="submit" name="page" value="5"/>
           
        </form>
    </center>    
</body>
</html>
