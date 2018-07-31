<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resource" />
<html>
<head>
    <title>add-project</title>
</head>
<body>
    <div id="contentBody">
    <h3><fmt:message key="page.addProject"/></h3>
    <form action="controller">
    <table>
    <tr>
    <td><fmt:message key="page.cipher"/></td>
    <td><input type="text" name="cipher" /></td>
    </tr>
    <tr>
    <td><fmt:message key="page.date"/></td>
    <td><input type="text" name="date" /></td>
    </tr>
    <td><fmt:message key="page.cost"/></td>
    <td><input type="text" name="cost" /></td>
    </tr>
    <tr>
    <td></td>
    <td>
    <input type="hidden" name="commandName" value="ADD_PROJECT" />
    <button type="submit"><fmt:message key="page.submit"/></button>
    </td>
    </tr>
    </table>
    </form>
    </div>
</body>
</html>
