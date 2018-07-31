<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource" />
<html>
<head>
    <meta charset="UTF-8">
    <title>team-project</title>
    <link rel="stylesheet" href="/style/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-center">
    <h1><fmt:message key="page.choose"/></h1>
</div>

<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <button class="w3-btn w3-hover-red w3-round-large w3-light-blue" onclick="location.href='/controller?commandName=TO_LOG_IN'"><fmt:message key="page.developer"/></button>
        <button class="w3-btn w3-hover-red w3-round-large w3-light-blue" onclick="location.href='/controller?commandName=TO_LOG_IN'"><fmt:message key="page.customer"/></button>
        <button class="w3-btn w3-hover-red w3-round-large w3-light-blue" onclick="location.href='/controller?commandName=TO_LOG_IN'"><fmt:message key="page.manager"/></button>
    </div>
</div>
</body>
</html>
