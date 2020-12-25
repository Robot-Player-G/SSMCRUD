$(document).ready(function () {
    var username = $("#hidden-username").val();
    var nickname = $("#hidden-nickname").val();
    var sex = $("#hidden-sex").val();
    var birthday = $("#hidden-birthday").val();
    $.liClick();
    $("#user-info").bind('click',function () {
         $("#panel-heading").empty();
         $("#panel-heading").html(username+"的个人信息");
         $("#panel-body").empty();
         $("#panel-body").append("" +
             "<form id='userInfoForm'>" +
             "      <div class='form-group'>" +
             "           <label>用户名:</label>"+username+" <img src='/static/images/user/user.jpg' width='30px' height='30px' alt='用户头像'>" +
             "      </div>" +
             "      <div class='form-group'>" +
             "           <label>昵称</label>" +
             "           <input id='nickname' type='text' placeholder='"+nickname+"'"+
             "      </div>" +
             "      <div class='form-group'>" +
             "          <label>性别</label>" +
             "          <input id='sex' type='text' placeholder='"+sex+"'>" +
             "      </div>" +
             "      <div class='form-group'>" +
             "           <label>生日</label>" +
             "           <input id='birthday' type='date' placeholder='"+birthday+"'>" +
             "      </div>" +
             "           <button type='button' class='btn btn-default' id='modifyForm'>保存</button>" +
             "</form>");
    });
    $("#task-info").bind('click',function () {
        $("#panel-heading").empty();
        $("#panel-heading").append("<ul class=\"nav nav-tabs\">\n" +
            "  <li role='presentation' class='active' id='published-li'><a href='javascript:void(0)' id='published'>已发布</a></li>\n" +
            "  <li role='presentation' id='received-li'><a href='javascript:void(0)' id='received'>已接取</a></li>\n" +
            "</ul>");
        $.publishedTask();
        $.receivedTask();
        $("#panel-body").empty();
        $("#panel-body").html("这里用户的所有个人交易信息");
    });
    //修改信息按钮的点击事件
    $("#modifyForm").bind('click',function () {
        var value1 = $("#nickname").val();
        var value2 = $("#sex").val();
        var value3 = $("#birthday").val();
        if (confirm("确认要修改信息吗？") == true){
            if (value1 == ''){
                value1 = nickname;
            }
            if (value2 == ''){
                value2 = sex;
            }
            if (value3 == ''){
                value3 = birthday;
            }
            $.ajax({
                url:"/user/modifyUserInfo",
                type:"post",
                data:{nickname:value1,sex:value2,birthday:value3},
                success:function () {
                    alert("保存成功!");
                }
            });
        }
    });
});
$.extend({
    publishedTask:function () {
        //已发布任务
        $("#published").bind('click',function () {
            $("#received-li").removeClass("active");
            $("#published-li").addClass("active");
            alert("已发布任务");
        });
    },
    receivedTask:function () {
        //已接取任务
        $("#received").bind('click',function () {
            $("#published-li").removeClass("active");
            $("#received-li").addClass("active");
            alert("已接取任务");
        });
    },
    liClick:function () {
        $("li").bind("click",function () {
            $("li").removeClass("active");
            $(this).addClass("active");
        });
    }
});