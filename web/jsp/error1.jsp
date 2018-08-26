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
    <title>error</title>
    <link rel="stylesheet" href="/style/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-red w3-opacity w3-center">
    <h1><fmt:message key="page.error"/></h1>
</div>
<div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <form action="controller" method="post">
            <input name="forwardPage" type="hidden" value="/index.jsp"/>
            <input type="hidden" name="commandName" value="TO_PAGE"/>
            <button class="w3-btn w3-hover-light-blue w3-round-large w3-red" type="submit"><fmt:message
                    key="page.backMain"/></button>
        </form>
    </div>
</div>
</body>
</html>