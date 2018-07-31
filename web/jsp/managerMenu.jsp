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

<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <button class="w3-btn w3-hover-red w3-round-large w3-light-blue" onclick="location.href='/controller?commandName=FORM_PROJECT'"><fmt:message key="page.formTT"/></button>
        <button class="w3-btn w3-hover-red w3-round-large w3-light-blue" onclick="location.href='/controller?commandName=SHOW_PROJECTS'"><fmt:message key="page.showProjects"/></button>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large w3-white" onclick="location.href='/controller?commandName=EXIT'"><fmt:message key="page.logout"/></button>
</div>
</body>
</html>
