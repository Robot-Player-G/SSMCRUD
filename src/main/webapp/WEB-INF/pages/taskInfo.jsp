<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/12/5
  Time: 22:45
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
    <title>任务详情</title>
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
    <script type="application/javascript" src="${path}/static/js/taskInfo_actions.js?ver=1"></script>
</head>
<body>
       <input type="hidden" id="task_id" value="${taskId}">
       <input type="hidden" id="user" value="${username}">
       <div class="container">
           <h3 align="center">任务详情</h3>
           <hr/>
       </div>
       <div class="container">
           <table align="center" class="table table-striped">
               <tr>
                   <td>任务标题:</td>
                   <td id="title"></td>
               </tr>
               <tr>
                   <td>发布人:</td>
                   <td id="publisher"></td>
               </tr>
               <tr>
                   <td>任务内容:</td>
                   <td id="content"></td>
               </tr>
               <tr>
                   <td>报酬:</td>
                   <td id="pay"></td>
               </tr>
               <tr>
                   <td>发布于:</td>
                   <td id="publish_time"></td>
               </tr>
               <tr>
                   <td>状态:</td>
                   <td id="status"></td>
               </tr>
           </table>
           <p align="center">
               <button type="button" class="btn btn-default dropdown-toggle" id="back_btn">返回</button>
               <button type="button" class="btn btn-success" id="receive-btn">接取</button>
           </p>
       </div>
</body>
</html>
