<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<%@ taglib uri="/WEB-INF/taglib" prefix="tag" %>
<html>
<head>
    <title>user-tag</title>
</head>
<body>
<tag:getresource key="jsp.message.hello"/>
</body>
</html>
