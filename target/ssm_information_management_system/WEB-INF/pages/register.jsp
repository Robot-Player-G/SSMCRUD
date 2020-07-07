<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/7/7
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        body{
            text-align: center;
        }
    </style>
</head>
<body>
      <p style="text-shadow: 2px 2px aqua">请输入注册信息</p>
      <form action="user/userRegister" method="post">
         用户名： <input type="text" name="username"><br><br>
          密码：<input type="password" name="password"><br><br>
          <input type="submit" value="提交注册信息"><br>
      </form>
</body>
</html>
