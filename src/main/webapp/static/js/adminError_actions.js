$(document).ready(function () {
    var time = 10;
    $("#show-error").html("请检查你的输入是否正确！");
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