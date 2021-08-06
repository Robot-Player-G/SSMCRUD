$(document).ready(function () {
    var msg = $("#msg").val();
    var code = $("#code").val();
    var time = 15;
    if (code == 0) {
        $("#show-error").html("<b><font color=red>"+msg+"</font></b>");
    }
    setTimeout($.backToIndex,15000);
    timeTen(time);
    function timeTen(time) {
        $("#show-time").html("倒计时<font color=red>"+time+"</font>秒后返回欢迎页面 &nbsp;&nbsp;&nbsp;点击直接<a href='/jump/adminSignOut'>返回</a>");
        time=time-1;
        setTimeout(function () {
            timeTen(time);
            },1000);
    }
});
$.extend({
    backToIndex:function () {
        window.location.href = "/jump/adminSignOut";
    }
});