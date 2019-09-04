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
        <c:forEach var="sol" items="${solutionList}">
            <li>${sol.title},${sol.authorName},${sol.dateStr}</li>
        </c:forEach>
    </ul>
<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>
