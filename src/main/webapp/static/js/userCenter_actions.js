$(document).ready(function () {
    var username = $("#hidden-username").val();
    var nickname = $("#hidden-nickname").val();
    var sex = $("#hidden-sex").val();
    var birthday = $("#hidden-birthday").val();
    var  focus_flag = $("#hidden-flag").val();
    if (focus_flag == "userInfo"){
        $("#info-li").addClass("active");
        $.showUserInfo(username,nickname,sex,birthday);
    }else {
        $("#deal-li").addClass("active");
        $.showTaskInfo(username);
    }
    $.liClick();
    $("#change_user_img").bind("click",function () {
        $.changeUserImage(username);
    });
    $("#user-info").bind('focus',function () {
        $.showUserInfo(username,nickname,sex,birthday);
        $("#published-li").removeClass("active");
    });
    $("#task-info").bind('focus',function () {
        $.showTaskInfo(username);
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
                    location.reload();
                }
            });
        }
    });
});
$.extend({
    publishedTask:function (param) {
        //已发布任务
        $("#published").bind('click',function () {
            $("#received-li").removeClass("active");
            $("#published-li").addClass("active");
            $.showPublishedTask(param);
        });
    },
    receivedTask:function (param) {
        //已接取任务
        $("#received").bind('click',function () {
            $("#published-li").removeClass("active");
            $("#received-li").addClass("active");
            $.showReceivedTask(param);
        });
    },
    liClick:function () {
        $("li").bind("click",function () {
            $("li").removeClass("active");
            $(this).addClass("active");
        });
    },
    showTaskInfo:function (param) {
        $("#panel-heading").empty();
        $("#panel-heading").append("<ul class='nav nav-tabs'>\n" +
            "  <li role='presentation' id='published-li'><a href='javascript:void(0)' id='published'>已发布</a></li>\n" +
            "  <li role='presentation' id='received-li'><a href='javascript:void(0)' id='received'>已接取</a></li>\n" +
            "</ul>");
        $("#published-li").addClass("active");
        $.publishedTask(param);
        $.receivedTask(param);
        $.showPublishedTask(param);
    },
    showPublishedTask:function (param) {
        $("#panel-body").empty();
        $("#panel-body").append("<table class='table table-striped' id='publishedTask_tbl'></table>");
        $.ajax({
            type:"POST",
            url:"/task/getTaskInfoByPublisher",
            data:{publisher:param},
            dataType:"json",
            success:function (data) {
                $("#publishedTask_tbl").append("<tr><td>标题</td><td>任务内容</td><td>任务发布时间</td><td>接取者</td><td>报酬</td><td>任务状态</td><td>    </td></tr>");
                $.each(data,function (i,test) {
                    // console.log(test);  test为一个一元组
                    var statusMsg;
                    if (test.receiver == null){
                        statusMsg = "无人接取";
                        var date = new Date(test.publish_time).toLocaleString();
                        $("#publishedTask_tbl").append("<tr name='"+test.task_id+"'><td>"+test.task_title+"</td><td>" +
                            test.task_content+"</td><td>" +
                            date+"</td><td>" +
                            "无</td><td>"+
                            test.pay+"</td>" +
                            "<td>"+statusMsg+"</td>"+
                            "<td> </td></tr>"
                        );
                    }else {
                        if (test.finish_time == null){
                            statusMsg = "已被接取";
                            var date = new Date(test.publish_time).toLocaleString();
                            $("#publishedTask_tbl").append("<tr name='"+test.task_id+"'><td>"+test.task_title+"</td><td>" +
                                test.task_content+"</td><td>" +
                                date+"</td><td>" +
                                test.receiver+"</td><td>"+
                                test.pay+"</td>" +
                                "<td>"+statusMsg+"</td>"+
                                "<td><button class='btn-default' id='contact-"+test.receiver+"'>联系"+test.receiver+"</button></td></tr>");
                            $.contactWithReceiver(param,test);
                        }else {
                            statusMsg = "已完成";
                            var date = new Date(test.publish_time).toLocaleString();
                            $("#publishedTask_tbl").append("<tr name='"+test.task_id+"'><td>"+test.task_title+"</td><td>" +
                                test.task_content+"</td><td>" +
                                date+"</td><td>" +
                                test.receiver+"</td><td>"+
                                test.pay+"</td>" +
                                "<td>"+statusMsg+"</td>" +
                                "<td> </td></tr>");
                        }
                    }
                });
            }
        });
    },
    showReceivedTask:function (param) {
        $("#panel-body").empty();
        $("#panel-body").append("<table class='table table-striped' id='receivedTask_tbl'></table>");
        $.ajax({
            type:"POST",
            url:"/task/getTaskInfoByReceiver",
            data:{receiver:param},
            dataType:"json",
            success:function (data) {
                $("#receivedTask_tbl").append("<tr><td>标题</td><td>发布者</td><td>任务内容</td><td>任务发布时间</td><td>报酬</td><td>任务状态</td><td></td></tr>");
                $.each(data,function (i,test) {
                    // console.log(test);  test为一个一元组
                    // console.log(test);
                    var statusMsg;
                    if (test.receiver == null){
                        statusMsg = "无人接取";
                        var date = new Date(test.publish_time).toLocaleString();
                        $("#receivedTask_tbl").append("<tr name='"+test.task_id+"'><td>"+test.task_title+"</td><td>" +
                            "无</td><td>" +
                            test.task_content+"</td><td>" +
                            date+"</td><td>" +
                            test.pay+"</td>" +
                            "<td>"+statusMsg+"</td>" +
                            "<td> </td></tr>");
                    }else {
                        if (test.finish_time == null){
                            statusMsg = "已被接取";
                            var date = new Date(test.publish_time).toLocaleString();
                            $("#receivedTask_tbl").append("<tr name='"+test.task_id+"'><td>"+test.task_title+"</td><td>" +
                                test.publisher+"</td><td>" +
                                test.task_content+"</td><td>" +
                                date+"</td><td>" +
                                test.pay+"</td>" +
                                "<td>"+statusMsg+"</td>" +
                                "<td><button class='btn-default' id='contact-"+test.publisher+"'>联系"+test.publisher+"</button></td>" +
                                "<td><button class='btn-default' id='submit-"+test.task_id+"'>提交方案</button> </td></tr>"
                            );
                            $.contactWithPublisher(param,test);
                            $.submitTask(test);
                        }else {
                            statusMsg = "已完成";
                            var date = new Date(test.publish_time).toLocaleString();
                            $("#receivedTask_tbl").append("<tr name='"+test.task_id+"'><td>"+test.task_title+"</td><td>" +
                                test.publisher+"</td><td>" +
                                test.task_content+"</td><td>" +
                                date+"</td><td>" +
                                test.pay+"</td>" +
                                "<td>"+statusMsg+"</td>" +
                                "<td> </td></tr>"
                             );
                        }
                    }
                });
            }
        });
    },
    showUserInfo:function (username,nickname,sex,birthday) {
        var ico = username+".jpg";
        $("#panel-heading").empty();
        $("#panel-heading").html(username+"的个人信息");
        $("#panel-body").empty();
        $("#panel-body").append("" +
            "<form id='userInfoForm'>" +
            "      <div class='form-group'>" +
            "           <label>用户名:</label>"+username+" <img src='/static/images/user/"+ico+"' width='30px' height='30px' alt='用户头像'>" +
            "           <input name='userImage' id='ico-file' type='file' value='上传头像'><a id='change_user_img' href='javascript:void(0)'>确认修改</a>"+
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
    },
    contactWithReceiver:function (param,test) {
        $("#contact-"+test.receiver).bind("click",function () {
            $.ajax({
                url:"/jump/gotoChatRoom",
                type:"post",
                data:{userA:param,userB:test.receiver},
            });
            window.location.href = "/jump/gotoChatRoom";
        });
    },
    contactWithPublisher:function (param,test) {
        $("#contact-"+test.publisher).bind("click",function () {
            $.ajax({
                url:"/jump/gotoChatRoom",
                type:"post",
                data:{userA:param,userB:test.publisher},
            });
            window.location.href = "/jump/gotoChatRoom";
        });
},
    changeUserImage:function (username) {
        //修改用户头像
        console.log("修改用户"+username+"的头像")
        var myData = $("#ico-file")[0].files[0];
        var formData = new FormData();
        formData.append("userImage",myData);
        $.ajax({
            contentType:"multipart/form-data",
            url:"/user/modifyUserImage",
            type:"post",
            data:formData,
            dataType:"text",
            processData: false, // 告诉jQuery不要去处理发送的数据
            contentType: false, // 告诉jQuery不要去设置Content-Type请求头
            success: function(result,data){
                console.log(data);
                location.reload();
            },
            error:function (result,data) {
                console.log(data);
            }
        });
    },
    submitTask:function (test) {
        $("#submit-"+test.task_id).bind("click",function () {
            //前往提交任务页面
            $.ajax({
                url:"/jump/gotoSubmitTask",
                data:{task_id:test.task_id},
                type:"post",
            });
            window.location.href="/jump/gotoSubmitTask";
        });
    }
});