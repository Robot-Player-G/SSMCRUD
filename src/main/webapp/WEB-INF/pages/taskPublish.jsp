<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/12/6
  Time: 22:17
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
    <title>发布任务</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${path}/static/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">-->
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="${path}/static/js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${path}/static/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script type="text/javascript" src="/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/taskPublish_actions.js?ver=1"></script>
    <link rel="shortcut icon" href="/static/images/ico.png">
</head>
<body>
      <input id="publisher" type="hidden" value="${publisher_username}">
      <div class="container">
          <p id="show_publisher" align="center"></p>
      </div>
      <div class="container" align="center">
          <form method="post" action="/task/publishTask">
              任务标题 :&nbsp<input type="text" name="task_title"><br/><br/>
              <input type="hidden" name="publisher" value="${publisher_username}">
              任务内容 ::&nbsp<input type="text" name="task_content"><br/><br/>
              <input type="hidden" name="publish_time" id="publish_time">
              <input type="hidden" name="receiver" id="receiver">
              <input type="hidden" name="finish_time" id="finish_time">
              任务报酬 ::&nbsp<input type="text" name="pay"><br/><br/>
              <p align="center">
                  <button type="button" class="btn btn-success" id="back_btn">返回</button>
                  <input type="submit"  class="btn btn-success" value="发布任务">
              </p>
          </form>
      </div>
</body>
</html>
