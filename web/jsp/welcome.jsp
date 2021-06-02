<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HI</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" name="draw" method="post" >
    <input type="hidden" name="command" value="draw">
    <br>

    <b>name of folder</b>
    <input type="text" value="" name="name" id="name" >
    <br>

    <b>GAS x coordinate </b>
    <br>
    <input type="number"  name="x"  id="x"  step="0.01" >
    <br>
    <b>GAX y coordinate </b>
    <br>
    <input type="number"  name="y"  id="y"  step="0.01" >
    <br>
    <b>GAS z coordinate </b>
    <br>
    <input type="number"  name="z"  id="z"  step="0.01" >
    <br>
    <b>depth h value </b>
    <br>
    <input type="number"  name="h"  id="h"  step="0.01" >
    <br>
    <b>time t value</b>
    <br>
    <input type="number"  name="t"  id="t"  step="0.01" >
    <br>
    <b>frequency mu value</b>
    <br>
    <input type="number"  name="mu"  id="mu"  step="0.01" >
    <br>
    <br>
    <b>location of source value </b>
    <br>
    <input type="number"  name="z_0"  id="z_0"  step="0.01" >
    <br>
    <input type="submit" value="Submit" >
</form>
<form action="${pageContext.request.contextPath}/controller" name="GET_PLOTS" method="post" >
    <input type="hidden" name="command" value="GET_PLOTS">
    <input type="submit" value="SEE all plots" >
</form>
</body>
</html>
