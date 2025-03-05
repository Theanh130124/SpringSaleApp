<%-- 
    Document   : index
    Created on : Mar 1, 2025, 3:44:03 PM
    Author     : LAPTOP
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--chỉ đang bị sai cái tag ở đây không dùng đc for-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <ul>
            <c:forEach items="${products}" var="p">
                <li>${p.id} - ${p.name} - ${p.price}</li>
                </c:forEach>
        </ul>
    </body>
</html>
