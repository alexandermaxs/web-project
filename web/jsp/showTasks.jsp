<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource" />
<html>
<head>
    <title>show-tasks</title>
    <link rel="stylesheet" href="style/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1><fmt:message key="page.tasks"/></h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <c:if test="${user.authority == 3}">
        <table cellspacing='8' class="w3-table">
            <tr>
                <th>id</th>
                <th><fmt:message key="page.info"/></th>
                <th><fmt:message key="page.date"/></th>
                <th><fmt:message key="page.number"/></th>
                <th><fmt:message key="page.cipher"/></th>
            </tr>
            <c:forEach items="${taskList}" var="task">
                <tr>
                    <td><c:out value="${task.id}" /><br/></td>
                    <td><c:out value="${task.info}" /><br/></td>
                    <td><c:out value="${task.date}" /><br/></td>
                    <td><c:out value="${task.number}" /><br/></td>
                    <td><c:out value="${task.cipher}" /><br/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<div id="contentBody" class="w3-card-4 w3-centered">
    <div class="w3-container w3-center w3-blue-gray">
        <h3><fmt:message key="page.deleteTask"/></h3>
    </div>
    <form action="controller" class="w3-selection w3-light-grey w3-padding">
        <table class="w3-centered">
            <tr>
                <td><fmt:message key="page.taskId"/></td>
                <td><input type="text" name="taskId" class="w3-input w3-border w3-round-large"/></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="hidden" name="commandName" value="DELETE_TASK" />
                    <button type="submit" class="w3-btn w3-hover-red w3-round-large w3-opacity w3-blue-gray"><fmt:message key="page.submit"/></button>
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding w3-bottom">
    <button class="w3-btn w3-round-large w3-white" onclick="location.href='/controller?commandName=MANAGER_MENU'"><fmt:message key="page.backMenu"/></button>
</div>
</body>
</html>
