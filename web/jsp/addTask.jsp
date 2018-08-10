<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource" />
<html>
<head>
    <title>add-task</title>
    <link rel="stylesheet" href="style/w3.css">
</head>
<body>
<div id="contentBody">
    <div class="w3-container w3-center w3-blue-gray">
        <h3><fmt:message key="page.addTask"/></h3>
    </div>
    <form action="controller" class="w3-selection w3-light-grey w3-padding">
        <table class="w3-centered">
            <tr>
                <td><fmt:message key="page.number"/></td>
                <td><input type="text" name="number" class="w3-input w3-border w3-round-large"/></td>
            </tr>
            <tr>
                <td><fmt:message key="page.info"/></td>
                <td><input type="text" name="info" class="w3-input w3-border w3-round-large"/></td>
            </tr>
            <td><fmt:message key="page.projectId"/></td>
            <td><input type="text" name="projectId" class="w3-input w3-border w3-round-large"/></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="hidden" name="commandName" value="ADD_TASK" />
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
