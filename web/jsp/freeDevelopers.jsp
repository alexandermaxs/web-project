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
    <title>free-developers</title>
    <link rel="stylesheet" href="style/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1><fmt:message key="page.freeDevelopers"/></h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <c:if test="${user.authority == 3}">
        <table cellspacing='8' class="w3-table">
            <tr>
                <th>id</th>
                <th><fmt:message key="page.name"/></th>
                <th><fmt:message key="page.qualification"/></th>
            </tr>
            <c:forEach items="${developerList}" var="developer">
                <tr>
                    <td><c:out value="${developer.id}"/><br/></td>
                    <td><c:out value="${developer.name}"/><br/></td>
                    <td><c:out value="${developer.info}"/><br/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<div id="contentBody" class="w3-card-4 w3-centered">
    <div class="w3-container w3-center w3-blue-gray">
        <h3><fmt:message key="page.formAss"/></h3>
    </div>

    <div class="w3-center">
        <br>
        <form action="controller" method="post">
            <fmt:message key="page.task"/>
            <select type="text" name="taskId">
                <c:forEach items="${taskList}" var="task">
                    <option value="${task.id}">${task.info}</option>
                </c:forEach>
            </select>
            <br><br>
            <fmt:message key="page.developer"/>
            <select type="text" name="developerId">
                <c:forEach items="${developerList}" var="developer">
                    <option value="${developer.id}">${developer.name}</option>
                </c:forEach>
            </select>
            <br><br>
            <input type="hidden" name="commandName" value="ASSIGNMENT"/>
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
