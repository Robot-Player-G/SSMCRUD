$(document).ready(function () {
    var code = $("#code").val();
    var user = $("#user").val();
    var check = $("#check").val();
    var time = 10;
    console.log(code+user+check);
    if (code == "0"){
        $("#show-error").html("请输入验证码！");
    }
    if (user == "0"){
        $("#show-error").html("请输入用户名和密码！");
    }else if (user == "1"){
        $("#show-error").html("用户不存在！");
    }
    if (check == "0"){
        $("#show-error").html("验证码或密码错误！");
    }
    setTimeout($.backToIndex,10000);
    timeTen(time);
    function timeTen(time) {
        $("#show-time").html("倒计时<font color=red>"+time+"</font>秒后返回欢迎页面 &nbsp;&nbsp;&nbsp;点击直接<a href='/jump/adminSignOut'>返回</a>");
        time=time-1;
        console.log(time);
        setTimeout(function () {
            timeTen(time);
            }
            ,1000);
    }
});
$.extend({
    backToIndex:function () {
        window.location.href = "/jump/adminSignOut";
    }
});