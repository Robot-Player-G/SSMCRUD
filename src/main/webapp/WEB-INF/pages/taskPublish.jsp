<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/12/6
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/taskPublish_actions.js?ver=1"></script>
    <link rel="shortcut icon" href="/static/images/ico.png">
</head>
<body>
      <input id="publisher" type="hidden" value="${publisher_username}">
      <p id="show_publisher"></p>
      <form method="post" action="/task/publishTask">
<%--                  <input type="hidden" name="task_id" id="task_id">--%>
          任务标题:<input type="text" name="task_title"><br/>
                   <input type="hidden" name="publisher" value="${publisher_username}">
          任务内容:<input type="text" name="task_content"><br/>
                   <input type="hidden" name="publish_time" id="publish_time">
                   <input type="hidden" name="receiver" id="receiver">
                   <input type="hidden" name="finish_time" id="finish_time">
          任务报酬:<input type="text" name="pay"><br/>
          <input type="submit" value="发布任务">
      </form>
</body>
</html>
