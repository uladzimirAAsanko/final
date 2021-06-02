<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: uladz
  Date: 02.06.2021
  Time: 06:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ALL_PLOTS</title>
</head>
<body>
<br> <b>ALL PLOTS By Names</b>
<c:forEach items="${list}" var="plot">
    <div class="row">
        <div class="col-md-6">
            <a href="${pageContext.request.contextPath}/controller?command=GET_PLOT&name=${plot}">${plot}</a>
        </div>
    </div>
</c:forEach>
<a class="nav-item nav-link active" href="${pageContext.request.contextPath}/jsp/welcome.jsp">New plot</a>
</body>
</html>
