<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/7/6
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% pageContext.setAttribute("path", request.getContextPath()); %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Welcome to Idea World !</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/static/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">-->
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="/static/js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="/static/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="static/js/index_actions.js" type="text/javascript"></script>
    <link REL="SHORTCUT ICON" href="/static/images/ico.png">
</head>
<body>
<%--    <div>--%>
<%--        <form method="post"action="/user/userLogin">--%>
<%--            用户:<input type="text" name="username"><br/>--%>
<%--            密码:<input type="password" name="password"><br/>--%>
<%--            验证码:<input type="text" name="userCode"><img alt="code" id="randomCode" src="randomCode">--%>
<%--            <b id="flush_code">看不清，换一个</b><br/>--%>
<%--            <input type="submit" value="登录"><br/>--%>
<%--        </form>--%>
<%--    </div>--%>
    <p class="container">
        <h3><p class="text-center">欢迎来到idea世界，请先登录!</p></h3>
        <p class="text-center"><span class="label label-primary">welcome！</span></p>
    </div>
    <div class="container">
        <form class="form-horizontal" method="post" action="/user/userLogin">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">用户:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputEmail3" placeholder="请输入用户名" name="username">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">密码:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">验证码:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputUsercode" placeholder="请输入验证码，区分大小写" name="userCode">
                    <img alt="code" id="randomCode" src="${path}/randomCode">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> 记住用户
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">登录</button>&nbsp;&nbsp;&nbsp;
                    还没有账户?<a class="btn btn-default" href="/user/toUserRegister" role="button">注册</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
