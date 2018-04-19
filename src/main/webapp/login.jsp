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
<form action="login" method="post" id="loginForm" onsubmit="return checkLoginForm();">
    usename:<input type="text" name="username" id="login_name"/>
    <br/>
    password:<input type="text" name="password" id="login_password"/>
    <br/>
    <input name="sub" type="submit" value="提交"/>
    <input name="reset" type="reset" value="重置">
</form>

<script>

    function checkLoginForm(){
        var userAcct = $.trim($("#login_name").val());
        var userPwd = $("#login_password").val();
        // var errMsg = $("#login_errMsg");
        // errMsg.html("");//？
        var errMsg = "";
        if(!userAcct){
            errMsg.html("请输入用户名");
            return false;
        }
        if(!userPwd){
            errMsg.html("请输入密码");
            return false;
        }
        if(userPwd.length < 6 || userPwd.length > 32){
            errMsg.html("无效的密码");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
