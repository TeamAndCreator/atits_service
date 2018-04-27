<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2018/4/7
  Time: 01:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello Page</h1>
    <form action="dynamic/update" enctype="multipart/form-data" method="post">
        <input type="hidden" name="_method" value="PUT">
        id:<input type="text" name="id" />
        <br/>
        title:<input type="text" name="title" />
        <br/>
        content:<input type="text" name="content" />
        <br/>
        time:<input type="text" name="time">
        <br>
        system.id<input type="text" name="system.id">
        <br>
        user.id<input type="text" name="user.id">
        <br>
        state<input type="text" name="state">
        <br>
        file<input type="file" name="multipartFiles" multiple>
        <input type="submit" value="submit"/>
    </form>
</body>
</html>
