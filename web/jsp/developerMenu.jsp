<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resource"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>developer-menu</title>
    <link rel="stylesheet" href="style/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-center">
    <h1><fmt:message key="page.greetings"/> <fmt:message key="page.developer"/> ${user.name}!</h1>
</div>
<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <div><fmt:message key="page.assignment"/> - ${user.info}</div>
        <br>
        <div><fmt:message key="page.deadline"/>: ${user.date}</div>
        <br>
    </div>
</div>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding w3-bottom">
    <form action="controller" method="post">
        <input type="hidden" name="commandName" value="EXIT"/>
        <button class="w3-btn w3-round-large w3-white w3-hover-red" type="submit"><fmt:message
                key="page.logout"/></button>
    </form>
</div>
</body>
</html>
