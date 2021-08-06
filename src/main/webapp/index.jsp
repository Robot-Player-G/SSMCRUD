<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/7/6
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
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
    <div class="container">
        <h3><p class="text-center">欢迎来到idea世界!</p></h3>
        <p class="text-center"><span class="label label-primary">welcome！</span></p>
    </div>
    <div class="jumbotron" style="width:80%;margin: auto" align="center">
        <div class="container">
            <h1>Hello, world!</h1>
            <p>欢迎来到本网站！</p>
            <p>欢迎使用！</p>
            <a data-toggle="modal" data-target="#loginModal" class="btn-default">登录</a>
             &nbsp;&nbsp;&nbsp;&nbsp; 还没有账号?<a href="/jump/toUserRegister" class="btn-default">注册</a>
        </div>
    </div>
    <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"><input type="radio" checked="checked" name="flag" value="user">用户&nbsp;&nbsp;&nbsp;<input type="radio" name="flag" value="admin">管理员</h4>
                </div>
                <div class="modal-body">
                    <form id="loginForm" action="${path}/user/userLogin" method="post">
                        <div class="form-group">
                            <input type="hidden" id="method-flag" name="_method" value=""/>
                        </div>
                        <div class="form-group">
                            <label for="addname">用户</label>
                            <input type="text" id="addname" name="username" class="form-control" placeholder="请输入用户名">
                        </div>
                        <div class="form-group">
                            <label for="addpassword">密码</label>
                            <input type="text" id="addpassword" name="password" class="form-control" placeholder="请输入密码">
                        </div>
                        <div class="form-group">
                            <label for="rCode">验证码</label>
                            <input type="text" id="rCode" name="userCode" class="form-control" placeholder="请输入验证码，区分大小写">
                            <img alt="code" id="randomCode" src="${path}/randomCode">
                        </div>
                        <div class="form-group" style="child-align: middle;">
                            <p align="right"><input type="submit" style="width: 100px;" class="form-control" value="登录"></p>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                </div>
            </div>
        </div>
    </div>
    <!--footer-->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <p align="center">
                        Copyright&nbsp;©&nbsp;2021-2025&nbsp;&nbsp;www.maiziedu.com&nbsp;&nbsp;蜀ICP备13014270号-4
                    </p>
                </div>
            </div>
        </div>
    </footer>
    <!--footer-->
</body>
</html>