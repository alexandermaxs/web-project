<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>manager-menu</title>
    <link rel="stylesheet" href="style/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-center">
    <h1><fmt:message key="page.greetings"/> <fmt:message key="page.manager"/> ${user.name}!</h1>
</div>

<br>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-opacity w3-light-blue">
            <h2><fmt:message key="page.actionMenu"/></h2>
        </div>
        <ul class="w3-ul">
            <li class="w3-hover-sand" onclick="location.href='/controller?commandName=SHOW_PROJECTS'"><fmt:message key="page.showProjects"/></li>
            <li class="w3-hover-sand" onclick="location.href='/controller?commandName=SHOW_TASKS'"><fmt:message key="page.showTasks"/></li>
            <li class="w3-hover-sand" onclick="location.href='/controller?commandName=FREE_DEVELOPERS'"><fmt:message key="page.showFreeDevelopers"/></li>
            <li class="w3-hover-sand" onclick="location.href='/controller?commandName=FORM_TASK'"><fmt:message key="page.formTT"/></li>
        </ul>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding w3-bottom">
    <button class="w3-btn w3-round-large w3-white" onclick="location.href='/controller?commandName=EXIT'"><fmt:message key="page.logout"/></button>
</div>
</body>
</html>
