<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/11/26
  Time: 14:34
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
    <link rel="shortcut icon" href="${path}/static/images/ico.png">
<%--    <link rel="stylesheet" href="${path}/static/css/bootstrap.min.new.css"/>--%>
    <link rel="stylesheet" href="${path}/static/css/bootstrap-maizi.css"/>
    <title>用户中心</title>
    <script type="text/javascript" src="${path}/static/js/userCenter_actions.js">
    </script>
</head>
<body>
      <input type="hidden" id="hidden-username" value="${username}">
      <input type="hidden" id="hidden-flag" value="${focus_flag}">
      <input type="hidden" id="hidden-message">
      <div class="container">
          <h3 align="center">用户中心</h3>
      </div>
      <%--导航--%>
      <div class="container">
          <nav class="navbar navbar-default">
              <div class="container-fluid">
                  <!-- Brand and toggle get grouped for better mobile display -->
<%--                  <div class="navbar-header">--%>
<%--                      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">--%>
<%--                          <span class="sr-only">Toggle navigation</span>--%>
<%--                          <span class="icon-bar"></span>--%>
<%--                          <span class="icon-bar"></span>--%>
<%--                          <span class="icon-bar"></span>--%>
<%--                      </button>--%>
<%--                      <a class="navbar-brand" href="#">Brand</a>--%>
<%--                  </div>--%>
                  <!-- Collect the nav links, forms, and other content for toggling -->
                  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                      <ul class="nav navbar-nav">
                          <li id="info-li"><a href="javascript:void(0)" id="user-info"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;个人信息 <span class="sr-only">(current)</span></a></li>
                          <li id="deal-li"><a href="javascript:void(0)" id="task-info"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;交易记录</a></li>
                          <li id="download-li"><a href="javascript:void(0)" id="downloadIdea"><span class="glyphicon glyphicon-download-alt"></span>&nbsp;&nbsp;下载方案</a></li>
                          <li id="feedback-li"><a href="javascript:void(0)" id="feedback"><span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;反馈</a></li>
                      </ul>
                  </div><!-- /.navbar-collapse -->
              </div>
          </nav>
      </div>
      <%--详细信息--%>
      <div class="container">
          <div class="panel panel-default">
              <div class="panel-heading" id="panel-heading">

              </div>
              <div class="panel-body" id="panel-body">

              </div>
          </div>
      </div>
</body>
</html>
