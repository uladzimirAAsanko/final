<%--
  Created by IntelliJ IDEA.
  User: uladz
  Date: 31.05.2021
  Time: 06:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Title</title>
</head>
<body>
<div>
    <div >
        <div >
            <div >
                <img src="${pageContext.request.contextPath}/file/?name=${name}&end=1.png" alt="plot" width="800" height="500"/>
            </div>
            <div >
                <img src="${pageContext.request.contextPath}/file/?name=${name}&end=2.png" alt="plot" width="800" height="500"/>
            </div>
            <div >
                <img src="${pageContext.request.contextPath}/file/?name=${name}&end=3.png" alt="plot" width="800" height="500"/>
            </div>
            <div >
                <img src="${pageContext.request.contextPath}/file/?name=${name}&end=4.png" alt="plot" width="800" height="500"/>
            </div>
        </div>
        <div >
            <div >
                <h5> Name of plots is ${name}  </h5>
            </div>
        </div>
    </div>
</div>

<form action="${pageContext.request.contextPath}/controller" name="GET_PLOTS" method="post" >
    <input type="hidden" name="command" value="GET_PLOTS">
    <input type="submit" value="SEE all plots" >
</form>
<br>
<br>
<a class="nav-item nav-link active" href="${pageContext.request.contextPath}/jsp/welcome.jsp">New plot</a>
</body>
</html>
