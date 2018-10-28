<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns:c="http://java.sun.com/jsp/jstl/core">
<head>
    <title>List Elements</title>
</head>
<body>
    <ul>
        <c:forEach items="${requestScope.elements}" var="item">
        <li>${item}</li>
        </c:forEach>
    </ul>
</body>
</html>
