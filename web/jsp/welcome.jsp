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

    <b>x value </b>
    <br>
    <input type="number"  name="x"  id="x"  step="0.01" >
    <br>
    <b>y value </b>
    <br>
    <input type="number"  name="y"  id="y"  step="0.01" >
    <br>
    <b>z value </b>
    <br>
    <input type="number"  name="z"  id="z"  step="0.01" >
    <br>
    <b>h value </b>
    <br>
    <input type="number"  name="h"  id="h"  step="0.01" >
    <br>
    <input type="submit" value="Submit" >
</form>
</body>
</html>
