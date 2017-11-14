<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 07.11.2017
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>

<head>
    <base href="${pageContext.request.scheme}://
    ${pageContext.request.serverName}:
    ${pageContext.request.serverPort}
    ${pageContext.request.contextPath}/" />
    <title>Protected page</title>

</head>
<body>

    <div th:if ="${pageContext.request.userPrincipal.name != null}">
        <h2>Hi ${pageContext.request.userPrincipal.name}</h2>
    </div>

    <div>
        <a href="/">index</a> page
    </div>

    <div>
        <form:form modelAttribute="generatedRandomEmployees" action="/employeesTable" method="post">
            <p>list size:
                <input type="number" id="listSize" name="listSize" value="${generatedRandomEmployees.listSize}">
            </p>
            <input type="submit" value="Ok">

        </form:form>
    </div>

</body>
</html>
