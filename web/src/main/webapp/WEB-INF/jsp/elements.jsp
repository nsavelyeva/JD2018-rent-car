<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

    <ol start="${requestScope.start}">
        <c:forEach items="${requestScope.elements}" var="item">
        <li>${item}</li>
        </c:forEach>
    </ol>

<%@ include file="include/end-html.jsp" %>
