<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>main</title>
</head>
<body>
    <div>
        <jsp:useBean id="user" scope="session" type="team.model.bean.User"/>
        <c:if test="${user.authority == 3}">
            <jsp:include page="/jsp/managerMenu.jsp"/>
        </c:if>
        <c:if test="${user.authority == 2}">
            <jsp:include page="/jsp/customerMenu.jsp"/>
        </c:if>
        <c:if test="${user.authority == 1}">
            <jsp:include page="/jsp/developerMenu.jsp"/>
        </c:if>
        <c:if test="${user == null}">
            <jsp:include page="/jsp/error1.jsp"/>
        </c:if>
    </div>
</body>
</html>