<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/7/7
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<% pageContext.setAttribute("path", request.getContextPath());%>
<html>
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
    <script type="application/javascript" src="${path}/static/js/register_actions.js"></script>
    <link REL="SHORTCUT ICON" href="${path}/static/images/ico.png">
</head>
<body>
    <div class="container">
    <h3><p class="text-center">请注册!</p></h3>
    </div>
    <div class="container">
        <form class="form-horizontal" method="post" action="/user/userRegister">
            <div class="form-group">
                <label for="user" class="col-sm-2 control-label">用户:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="user" placeholder="" name="username">
                    <p id="checkUser"></p>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">密码:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="password" placeholder="Password" name="password">
                </div>
            </div>
            <div class="form-group">
                <label for="checkPassword" class="col-sm-2 control-label">确认密码:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="checkPassword" placeholder="Password" name="repassword">
                    <p id="checkText"></p>
                </div>
            </div>
            <div class="form-group">
                <label for="userCode" class="col-sm-2 control-label">验证码:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="userCode" placeholder="Code" name="userCode">
                    <img alt="code" id="randomCode" src="${path}/randomCode">
                    <p id="checkCode"></p>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" id="user_manual">同意用户使用手册
                        </label>
                        <div id="checkbox_div"></div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default" id="register_btn">注册</button>
                    <div id="register_div"></div>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
