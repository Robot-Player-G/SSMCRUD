<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/3/31
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<% pageContext.setAttribute("path", request.getContextPath()); %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${path}/static/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">-->
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="${path}/static/js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${path}/static/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <%--    <link rel="stylesheet" href="${path}/static/css/bootstrap.min.new.css"/>--%>
    <link rel="stylesheet" href="${path}/static/css/bootstrap-maizi.css"/>
    <link rel="shortcut icon" href="/static/images/ico.png">
    <script type="application/javascript" src="${path}/static/js/adminMessage_actions.js?ver=1"></script>
    <title>Idea后台管理</title>
</head>
<body>
<!--导航-->
<nav class="navbar navbar-default">
    <div class="container">
        <!--小屏幕导航按钮和logo-->
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="javascript:void(0)" class="navbar-brand">Admin</a>
        </div>
        <!--小屏幕导航按钮和logo-->
        <!--导航-->
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/jump/adminHomePage"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;后台首页</a></li>
                <li><a href="/jump/adminUserManagement"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;用户管理</a></li>
                <li ><a href="/jump/adminTaskManagement"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;交易管理</a></li>
                <li class="active"><a href="javascript:void(0)"><span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;查看留言</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/jump/adminSignOut"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
            </ul>
        </div>
        <!--导航-->

    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <div class="list-group">
                <a href="javascript:void(0)" class="list-group-item active" id="show-message">查看留言</a>
                <a href="javascript:void(0)" class="list-group-item" id="send-message">发送留言</a>
            </div>
        </div>
        <div class="col-md-10">
            <div class="page-header" id="panel-heading">

            </div>
            <div class="container" id="panel-body">

            </div>
            <br/>
        </div>
    </div>
</div>
</body>
</html>
