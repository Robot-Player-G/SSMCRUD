<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/2/8
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<% pageContext.setAttribute("path", request.getContextPath()); %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>chat</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${path}/static/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">-->
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="${path}/static/js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${path}/static/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="/static/images/ico.png">
    <link rel="stylesheet" href="${path}/static/css/chatRoom_style.css">
    <script src="${path}/static/js/chatRoom_actions.js"></script>
</head>
<body>
     <input type="hidden" id="userA" value="${userA}">
     <input type="hidden" id="userB" value="${userB}">
     <input type="hidden" id="flag">
     <div class="container" id="tou">
         webSocket多终端聊天测试
     </div>
     <div class="page-header" id="user-status"></div>
     <div class="well my-style" id="msg"></div>
     <div class="col-lg">
         <div class="input-group">
             <input type="text" class="form-control" placeholder="发送信息..." id="message">
             <span class="input-group-btn">
           <button class="btn btn-default" type="button" id="send" >发送</button>
        </span>
         </div>
     </div>
</body>
</html>
