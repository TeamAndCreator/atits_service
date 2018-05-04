<%--
  Created by IntelliJ IDEA.
  User: 74355
  Date: 2018/5/3
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="station/findPage" method="get">
        page:<input name="page" type="text">
        <br>
        <input type="submit" name="sub">

    </form>
    <br>
    <a href="station/findPage?page=2">2</a>

</body>
</html>
