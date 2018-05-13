<%--
  Created by IntelliJ IDEA.
  User: james
  Date: 2018/4/7
  Time: 01:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="report/save" enctype="multipart/form-data" method="post">
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
path=${pageContext.request.contextPath}
<a href="${pageContext.request.contextPath}/files/download?id=181" download="16计算机科学与技术1班春游策划.docx">16计算机科学与技术1班春游策划.docx</a>
<br>

<a href="${pageContext.request.contextPath}/File/1/重大文件/1/b93de87b-e1d0-4ca0-84d8-765d447b6b16.docx" download="download">txt</a>
<button></button>
</body>
</html>