<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%@ include file="/WEB-INF/views/jspf/head-imports.jspf"%>
    <title>Title</title>
</head>
<body>
<%@ include file="/WEB-INF/views/jspf/header.jspf"%>
    <div>Lista użytkowników</div>
    <ul>
        <c:forEach var="user" items="${users}">
            <li>${user.id},${user.username},${user.email},${user.password}</li>
        </c:forEach>
    </ul>
<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>
