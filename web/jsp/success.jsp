<%--
  Created by IntelliJ IDEA.
  User: uladz
  Date: 31.05.2021
  Time: 06:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sucess</title>
</head>
<body>
<b>PLOT IS CREATED SUCCESSFULLY</b>
<form action="${pageContext.request.contextPath}/controller" name="draw" method="post" >
    <input type="hidden" name="command" value="GET_PLOT">
    <input type="hidden" name="name" value="${name}">
    <input type="submit" value="SEE the plot ${name}" >
</form>
</body>
</html>
