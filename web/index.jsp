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
    <meta charset="UTF-8">
    <title>team-project</title>
    <link rel="stylesheet" href="/style/w3.css">
</head>
<body class="w3-light-gray" background="image/office.jpg">
<div class="w3-container w3-black w3-opacity-min w3-center">
    <h1><fmt:message key="page.choose"/></h1>
</div>

<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <form action="controller" method="post">
            <input type="hidden" name="commandName" value="TO_LOG_IN"/>
            <button class="w3-btn w3-hover-red w3-round-large w3-light-blue" type="submit">
                <fmt:message
                        key="page.developer"/></button>
            <input type="hidden" name="commandName" value="TO_LOG_IN"/>
            <button class="w3-btn w3-hover-red w3-round-large w3-light-blue" type="submit">
                <fmt:message key="page.customer"/></button>
            <input type="hidden" name="commandName" value="TO_LOG_IN"/>
            <button class="w3-btn w3-hover-red w3-round-large w3-light-blue" type="submit">
                <fmt:message key="page.manager"/></button>
        </form>
    </div>
</div>

<div class="w3-container w3-center w3-bottom w3-black w3-opacity-min">
    <div class="w3-left-align">
        <br>
        <fmt:message key="welcome.message"/><br><br>
        <fmt:message key="welcome.message1"/><br>
        <fmt:message key="welcome.message2"/><br>
        <fmt:message key="welcome.message3"/><br><br>
        <fmt:message key="welcome.message4"/><br>
    </div>
</div>
</body>
</html>
