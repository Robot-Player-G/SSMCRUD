<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/2/11
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
    <link rel="shortcut icon" href="${path}/static/images/ico.png">
    <script type="text/javascript" src="${path}/static/js/submitTask_actions.js"></script>
    <title>提交方案</title>
</head>
<body>
    <input type="hidden" id="taskId" value="${taskId}">
    <div class="container">
        <h2 align="center">方案提交</h2>
        <hr/>
    </div>
    <div class="container">
        <div class="container">
            <b>请上传你的方案:</b><br/>
            <input type="file" class="btn-default" id="file"><br/>
            <input type="button" class="btn-default" id="submit" value="提交方案">
        </div>
        <div class="container">
            <p id="show-label"></p>
        </div>
        <div class="container" id="success-show">

        </div>
    </div>
</body>
</html>
