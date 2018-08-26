<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resource"/>
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
                    <td><c:out value="${task.id}"/><br/></td>
                    <td><c:out value="${task.info}"/><br/></td>
                    <td><c:out value="${task.date}"/><br/></td>
                    <td><c:out value="${task.number}"/><br/></td>
                    <td><c:out value="${task.cipher}"/><br/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<div id="contentBody" class="w3-card-4 w3-centered">
    <div class="w3-container w3-center w3-blue-gray">
        <h3><fmt:message key="page.deleteTask"/></h3>
    </div>
    <div class="w3-center">
        <br>
        <form action="controller" method="post">
            <fmt:message key="page.taskId"/>
            <select type="text" name="taskId">
                <c:forEach items="${taskList}" var="task">
                    <option value="${task.id}">${task.id}</option>
                </c:forEach>
            </select>

            <input type="hidden" name="commandName" value="DELETE_TASK"/>
            <button type="submit">
                <fmt:message key="page.submit"/></button>
        </form>
        <br>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding w3-bottom">
    <form action="controller" method="post">
        <input type="hidden" name="commandName" value="MANAGER_MENU"/>
        <button class="w3-btn w3-round-large w3-white" type="submit"><fmt:message
                key="page.backMenu"/></button>
    </form>
</div>
</body>
</html>
