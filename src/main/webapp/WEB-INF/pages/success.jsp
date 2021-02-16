<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/7/7
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" isELIgnored="false"%>
<% pageContext.setAttribute("path", request.getContextPath()); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Welcome to Idea World !</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${path}/static/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
 <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">-->
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="${path}/static/js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${path}/static/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="/static/images/ico.png">
    <script type="application/javascript" src="${path}/static/js/success_actions.js?ver=1"></script>
</head>
<body>
      <input type="hidden" value="${user}" id="user-input">
      <input type="hidden" value="${username}" id="publisher-input">
      <input type="hidden" value="${publish}" id="isSuccess-input">
      <input type="hidden" value="${backFlag}" id="back-flag">
      <div class="container">
          <h3 align="center">欢迎来到idea交易世界!</h3>
      </div>
      <!--导航栏-->
      <div class="container">
          <nav class="navbar navbar-inverse">
              <div class="container-fluid">
                  <!-- Brand and toggle get grouped for better mobile display -->
                  <div class="navbar-header">
                      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                          <span class="sr-only">Toggle navigation</span>
                          <span class="icon-bar"></span>
                          <span class="icon-bar"></span>
                          <span class="icon-bar"></span>
                      </button>
                      <li class="active" id="homepage-li"><a class="navbar-brand" href="javascript:void(0);" id="home_page">首页</a></li>
                  </div>

                  <!-- Collect the nav links, forms, and other content for toggling -->
                  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                      <ul class="nav navbar-nav">
                          <li id="task-center-li"><a href="javascript:void(0);" id="task_center">创意方案交易中心<span class="sr-only">(current)</span></a></li>
                          <li><a href="javascript:void(0);" id="list">创意方案交易共享案例</a></li>
                          <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">更多 <span class="caret"></span></a>
                              <ul class="dropdown-menu">
                                  <li><a href="${path}/jump/jumpToUserManual">新手教程</a></li>
                                  <li role="separator" class="divider"></li>
                                  <li><a href="${path}/jump/jumpToSiteDescription">网站介绍</a></li>
                              </ul>
                          </li>
                      </ul>
                      <form class="navbar-form navbar-left">
                          <div class="form-group" id="search-div">
                              <input id="search-input" type="text" class="form-control" placeholder="搜索任务">
                          </div>
                          <button id="search-button" type="button" class="btn btn-default">搜索</button>
                      </form>
                      <ul class="nav navbar-nav navbar-right">
                          <li class="dropdown">
                              <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><img src="${path}/static/images/user/123.jpg" alt="头像" width="20px" height="20px"><span class="caret"></span></a>
                              <ul class="dropdown-menu">
                                  <li><a href="javascript:void(0)" id="user_center" >用户${user}</a></li>
                                  <li role="separator" class="divider"></li>
                                  <li><a href="javascript:void(0)" id="user_information">个人信息</a></li>
                                  <li role="separator" class="divider"></li>
                                  <li><a href="javascript:void(0)" id="history_deal">历史交易</a></li>
                                  <li role="separator" class="divider"></li>
                                  <li><a href="javascript:void(0)" id="sign-out">退出登录</a></li>
                              </ul>
                          </li>
                      </ul>
                  </div><!-- /.navbar-collapse -->
              </div><!-- /.container-fluid -->
          </nav>
      </div>
      <!--内容展示-->
      <div class="container">
          <div class="panel panel-default">
              <div class="panel-heading" id="panel-heading">首页</div>
              <div class="panel-body" id="panel-body">
                  <h2>welcome</h2>
                  <a href="javascript:void(0)" id="publish-task">发起任务!</a>
              </div>
          </div>
      </div>

</body>
</html>
