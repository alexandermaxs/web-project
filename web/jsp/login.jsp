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
    <title>login-page</title>
    <link rel="stylesheet" href="style/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h1><fmt:message key="page.team"/></h1>
</div>

<div class="w3-container w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2><fmt:message key="page.authorizationPage"/></h2>
        </div>

        <form action="controller" method="post" class="w3-selection w3-light-grey w3-padding">
            <label><fmt:message key="page.login"/>:
                <input type="text" pattern="[A-zА-я0-9]{3,20}" name="login" value="${lastLogin}"
                       class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br/>
            </label>
            <label><fmt:message key="page.password"/>:
                <input type="password" pattern="[A-zА-я0-9]{5,32}" name="password"
                       class="w3-input w3-animate-input w3-border w3-round-large"
                       style="width: 30%"><br/>
            </label>
            <input type="hidden" name="commandName" value="AUTHORIZATION"/>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom"><fmt:message
                    key="page.submit"/></button>
        </form>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding w3-bottom">
    <form action="controller" method="post">
        <input name="forwardPage" type="hidden" value="/index.jsp"/>
        <input type="hidden" name="commandName" value="TO_PAGE"/>
        <button class="w3-btn w3-round-large w3-white" type="submit"><fmt:message
                key="page.backMenu"/></button>
    </form>
</div>
</body>
</html>


