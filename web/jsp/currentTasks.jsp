<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource" />
<html>
<head>
    <title>current-tasks</title>
    <link rel="stylesheet" href="style/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1><fmt:message key="page.tasks"/></h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <c:if test="${user.authority == 2}">
        <table cellspacing='8' class="w3-table">
            <tr>
                <th><fmt:message key="page.info"/></th>
                <th><fmt:message key="page.date"/></th>
                <th><fmt:message key="page.number"/></th>
                <th><fmt:message key="page.cipher"/></th>
            </tr>
            <c:forEach items="${taskList}" var="task">
                <tr>
                    <td><c:out value="${task.info}" /><br/></td>
                    <td><c:out value="${task.date}" /><br/></td>
                    <td><c:out value="${task.number}" /><br/></td>
                    <td><c:out value="${task.cipher}" /><br/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding w3-bottom">
    <button class="w3-btn w3-round-large w3-white" onclick="location.href='/controller?commandName=CUSTOMER_MENU'"><fmt:message key="page.backMenu"/></button>
</div>
</body>
</html>
