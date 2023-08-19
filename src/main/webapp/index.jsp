<%@ page contentType="text/html" language="java" isELIgnored="false"%>
<%--<%@ page  %>--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="language" value="en" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<title>Welcome</title>
<body>
<%--<c:out value="${pageContext.request.contextPath}"/>--%>
<jsp:forward page="/login"/>
<%--adsddsdsd--%>
</body>
</html>