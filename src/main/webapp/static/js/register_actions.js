$(document).ready(function () {
    //1.检验用户是否已存在
    //2.检验用户名是否为空
    $("#user").bind("keyup",function () {
       var username = $("#user").val();
        if ($.checkNull(username)==1){
            $("#checkUser").html("请输入用户名");
        }else if(username.indexOf(' ')!=-1){
            $("#checkUser").html("用户名中不能有空格,请重新输入！");
        }
        else {
            $.ajax({
                type:"POST",
                url:"/user/checkUser",
                data:"username="+username,
                success:function (result) {
                    if(result==1) {
                        $("#checkUser").html("用户已存在");
                    }else {
                        $("#checkUser").html("√");
                    }
                },
                error:function (result) {
                    console.log(result);
                }
            });
        }
    });

    //1.检验两次密码是否一致
    $("#checkPassword").bind("blur",function () {
       var password = $("#password").val();
       var checkPassword = $("#checkPassword").val()
        if (password==checkPassword){
            $("#checkText").html("√");
        }
        else {
            $("#checkText").html("请确认密码一致!");
        }
    });
    $("#userCode").bind("blur",function () {
        var userCode = $("#userCode").val();
    });
    $("#randomCode").bind("click",function () {
        $("#randomCode").attr("src",function () {
            return this.src+"?"+Math.random();
        });
    });
    $("#register_btn").bind("click",function (e) {
        //定义变量
        var username = $("#user").val();
        var password = $("#password").val();
        var checkPassword = $("#checkPassword").val();
        var userCode = $("#userCode").val();
        //清除警告条
        $("#register_div").empty();
        if($("#user_manual").prop("checked")==true){
            if ($.checkNull(username)==1||$.checkNull(password)==1||$.checkNull(checkPassword)==1||$.checkNull(userCode)==1){
                e.preventDefault();
                $("#register_div").append("<div class=\"alert alert-warning alert-dismissible\" role=\"alert\">"+
                "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>"+
                "<strong>警告!</strong> 请先填写信息！\n" +
                    "</div>");
            }

        }
        else {
            e.preventDefault();
            $("#checkbox_div").append("<div class=\"alert alert-danger\" role=\"alert\">请同意用户手册!</div>");
        }
    });
    $("#register_btn").bind("mouseout",function () {
        $("#register_div").empty();
    });
    $("#user_manual").bind("click",function () {
         $("#checkbox_div").empty();
    });
    $("#check_btn").bind("click",function () {
         console.log($("#checkbox_div").html());
         console.log($("#register_div").html());
    });
});
$.extend({
    checkNull:function(param) {
        if (param==null||param==undefined||param==''){
            return 1;
        }
        else{
            return 0;
        }
    }
});