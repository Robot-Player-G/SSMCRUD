$(document).ready(function () {
    var username = $("#hidden-username").val();
    // var year = birthday.getFullYear();
    // var month = birthday.getMonth()+1;
    // var day = birthday.getDate();
    // var newDate = year+"-"+month+"-"+day;
    // $("#birthday").val(newDate);
    // console.log(username);
    // console.log(year+"-"+month+"-"+day);
    var  focus_flag = $("#hidden-flag").val();
    if (focus_flag == "userInfo"){
        $("#info-li").addClass("active");
        $.showUserInfo(username);
        // $("#birthday").val(newDate);
    }else {
        $("#deal-li").addClass("active");
        $.showTaskInfo(username);
    }
    $.liClick();
    $("#user-info").bind('focus',function () {
        $.showUserInfo(username);
        $("#published-li").removeClass("active");
    });
    $("#task-info").bind('focus',function () {
        $.showTaskInfo(username);
    });
    //下载方案点击事件
    $("#downloadIdea").bind("click",function () {
        $("#panel-heading").empty();
        $("#panel-heading").html("<b>方案下载</b>");
        $("#panel-body").empty();
        $("#panel-body").append("<br/>" +
        "<input type='text' id='hashData'>" +
        "<input type='button' id='search-btn' value='搜索'><br/><br/>" +
        "<p id='label'></p>");
        $.downloadIdea();
    });
    $("#feedback").bind("click",function () {
        $.feedbackToAdmin(username);
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
            "  <li class='active' role='presentation' id='published-li'><a href='javascript:void(0)' id='published'>已发布</a></li>\n" +
            "  <li role='presentation' id='received-li'><a href='javascript:void(0)' id='received'>已接取</a></li>\n" +
            "</ul>");
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
            data:"publisher="+param,
            dataType:"json",
            success:function (data) {
                $("#publishedTask_tbl").append("<tr><td>标题</td><td>任务内容</td><td>任务发布时间</td><td>接取者</td><td>报酬</td><td>任务状态</td><td>    </td></tr>");
                $.each(data,function (i,test) {
                    // console.log(test);  test为一个一元组
                    var statusMsg;
                    if (test.receiver == null){
                        statusMsg = "无人接取";
                        var date = new Date(test.publish_time).toLocaleString();
                        if(test.check_status==2){
                            $("#publishedTask_tbl").append("<tr name='"+test.task_id+"'><td>"+test.task_title+"</td><td>" +
                                test.task_content+"</td><td>" +
                                date+"</td><td>" +
                                "无</td><td>"+
                                test.pay+"</td>" +
                                "<td>"+statusMsg+"</td>"+
                                "<td>"+test.check_result+"</tr>"
                            );
                        }else {
                            $("#publishedTask_tbl").append("<tr name='"+test.task_id+"'><td>"+test.task_title+"</td><td>" +
                                test.task_content+"</td><td>" +
                                date+"</td><td>" +
                                "无</td><td>"+
                                test.pay+"</td>" +
                                "<td>"+statusMsg+"</td>"+
                                "<td><button class='btn-default' id='cancel-"+test.task_id+"'>撤回</button></td></tr>"
                            );
                        }
                        $.cancelTask(param,test);
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
                            if (test.status==0){
                                $("#publishedTask_tbl").append("<tr name='"+test.task_id+"'><td>"+test.task_title+"</td><td>" +
                                    test.task_content+"</td><td>" +
                                    date+"</td><td>" +
                                    test.receiver+"</td><td>"+
                                    test.pay+"</td>" +
                                    "<td>"+statusMsg+"</td>" +
                                    "<td><button class='btn-default' id='get-"+test.task_id+"'>获取文件hash</button></td>" +
                                    "<td><button class='btn-default' id='pay-"+test.task_id+"'>确认支付</button></td></tr>");
                                $.getHashByTaskId(param,test);
                                $.payForTask(param,test);
                            }
                            else {
                                $("#publishedTask_tbl").append("<tr name='"+test.task_id+"'><td>"+test.task_title+"</td><td>" +
                                    test.task_content+"</td><td>" +
                                    date+"</td><td>" +
                                    test.receiver+"</td><td>"+
                                    test.pay+"</td>" +
                                    "<td>"+statusMsg+"</td>" +
                                    "<td><button class='btn-default' id='get-"+test.task_id+"'>获取文件hash</button></td></tr>");
                                $.getHashByTaskId(param,test);
                            }
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
            data:"receiver="+param,
            dataType:"json",
            success:function (data) {
                $("#receivedTask_tbl").append("<tr><td>标题</td><td>发布者</td><td>任务内容</td><td>任务发布时间</td><td>报酬</td><td>任务状态</td><td></td></tr>");
                $.each(data,function (i,test) {
                    // console.log(test);  test为一个一元组
                    console.log(test);
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
                            "<td></td></tr>");

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
    cancelTask:function(param,test){
        $("#cancel-"+test.task_id).bind("click",function () {
            if(confirm("确认撤回任务吗？")){
                $.ajax({
                    url:"/task/cancelTask",
                    type:"post",
                    data:{task_id:test.task_id},
                    success:function (data) {
                        if (data=="success"){
                            alert("撤回成功!");
                            location.reload();
                        }
                    }
                });
            }
        });
    },
    showUserInfo:function (username) {
        $.ajax({
            url:"/user/getUserInfoByUsername",
            type:"post",
            data:{username:username},
            success:function (data) {
                console.log(data);
                console.log(data.username);
                var year = new Date(data.birthday).getFullYear();
                var month = new Date(data.birthday).getMonth()+1;
                var day = new Date(data.birthday).getDate();
                var newDate = year+"-"+month+"-"+day;
                console.log(newDate);
                $("#panel-heading").empty();
                $("#panel-heading").html(data.username+"的个人信息");
                $("#panel-body").empty();
                $("#panel-body").append("" +
                    "<form id='userInfoForm'>" +
                    "      <div class='form-group'>" +
                    "           <label>用户名:</label>"+data.username+
                    "      </div>" +
                    "      <div class='form-group'>" +
                    "           <label>昵称</label>" +
                    "           <input id='nickname' type='text' placeholder='"+data.nickname+"'"+
                    "      </div>" +
                    "      <div class='form-group'>" +
                    "          <label>性别</label>" +
                    "          <input id='sex' type='text' placeholder='"+data.sex+"'>" +
                    "      </div>" +
                    "      <div class='form-group'>" +
                    "           <label>生日</label>" +
                    "           <input id='birthday' type='text' placeholder='"+newDate+"'>" +
                    "      </div>" +
                    "           <button type='button' class='btn btn-default' id='modifyForm'>保存</button>" +
                    "</form>");
                $.modifyUserInfo(data.username,data.nickname,data.sex,newDate);
            }
        });
    },
    modifyUserInfo:function(username,nickname,sex,birthday){
        //修改信息按钮的点击事件
        $("#modifyForm").bind('click',function () {
            var value1 = $("#nickname").val();
            var value2 = $("#sex").val();
            var value3 = $("#birthday").val();
            if (value1==''&&value2==''&&value3==''){
                alert("请先输入要修改的信息!");
            }else if (value2!="男"&&value2!="女") {
                alert("请输入正确的性别!");
            }else {
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
                            data:{username:username,nickname:value1,sex:value2,birthday:value3},
                            success:function () {
                                alert("保存成功!");
                                $("#hidden-birthday").val(value3);
                                location.reload();
                            }
                        });
                    }
            }
        });
    },
    getHashByTaskId:function(param,test){
        $("#get-"+test.task_id).bind("click",function () {
            $.ajax({
                url:"/task/getHashByTaskId",
                type:"post",
                data:{username:param,task_id:test.task_id},
                success:function (data) {
                    alert("交易hash为：\n"+data+"，请牢记！");
                }
            });
        });
    },
    payForTask:function(param,test){
        $("#pay-"+test.task_id).bind("click",function () {
            if(confirm("确认支付"+test.receiver+"报酬吗？")){
                $.ajax({
                    url:"/task/updatePaymentStatus",
                    type:"post",
                    data:{task_id:test.task_id},
                    success:function (data) {
                        if (data=="success"){
                            alert("支付成功!");
                            location.reload();
                        }
                    }
                });
            }
        });
    },
    contactWithReceiver:function (param,test) {
        $("#contact-"+test.receiver).bind("click",function () {
            // $.ajax({
            //     url:"/jump/gotoChatRoom",
            //     type:"post",
            //     data:{userA:param,userB:test.receiver},
            // });
            window.location.href = "/jump/gotoChatRoom?userA="+param+"&userB="+test.receiver;
        });
    },
    contactWithPublisher:function (param,test) {
        $("#contact-"+test.publisher).bind("click",function () {
            // $.ajax({
            //     url:"/jump/gotoChatRoom",
            //     type:"post",
            //     data:{userA:param,userB:test.publisher},
            // });
            window.location.href = "/jump/gotoChatRoom?userA="+param+"&userB="+test.publisher;
        });
    },
    // changeUserImage:function (username) {
    //     //修改用户头像
    //     console.log("修改用户"+username+"的头像")
    //     var myData = $("#ico-file")[0].files[0];
    //     var formData = new FormData();
    //     formData.append("userImage",myData);
    //     $.ajax({
    //         contentType:"multipart/form-data",
    //         url:"/user/modifyUserImage",
    //         type:"post",
    //         data:formData,
    //         dataType:"text",
    //         processData: false, // 告诉jQuery不要去处理发送的数据
    //         contentType: false, // 告诉jQuery不要去设置Content-Type请求头
    //         success: function(result,data){
    //             console.log(data);
    //             location.reload();
    //         },
    //         error:function (result,data) {
    //             console.log(data);
    //         }
    //     });
    // },
    submitTask:function (test) {
        $("#submit-"+test.task_id).bind("click",function () {
            //前往提交任务页面
            // $.ajax({
            //     url:"/jump/gotoSubmitTask",
            //     data:{task_id:test.task_id},
            //     type:"post",
            // });
            window.location.href="/jump/gotoSubmitTask?task_id="+test.task_id;
        });
    },
    downloadIdea:function () {
        var value = "";
        $("#hashData").bind("change",function () {
            value = $("#hashData").val();
        })
        $("#search-btn").bind("click",function () {
            $.ajax({
                url:"/eth/searchForFile",
                data:{hash:value},
                type:"post",
                success:function (filename) {
                    console.log(filename);
                    $("#label").append("" +
                    "<a href='/eth/downloadFile?filename="+filename+"'>下载"+filename+"</a>");
                },
                error:function (err) {
                    alert("请确认你输入的交易hash是否正确!");
                }
            });
        });
    },
    feedbackToAdmin:function (username) {
        $("#panel-heading").empty();
        $("#panel-heading").html("<b>留言反馈</b>");
        $("#panel-body").empty();
        $("#panel-body").append("<br/>" +
            "<div class='container' id='s-div' style='width: 80%;height: 100px;border: 1px solid black;border-radius: 4px'>" +
            "</div>");
        $("#panel-body").append("<br/>" +
            "<div class='container' style='width: 80%;height: 30px;'>" +
            "<input id='m-content' style='width: 80%' type='text'/>&nbsp;&nbsp;&nbsp;&nbsp;<input id='sendMessage' type='button' value='发送留言'>"+
            "</div>");
        $("#m-content").bind("keyup",function () {
             $("#hidden-message").val($("#m-content").val());
        });
        $.getAdminMessage(username);
        $("#sendMessage").bind("click",function () {
            var content = $("#hidden-message").val();
            $.ajax({
                url:"/user/sendMessageToAdmin",
                type:"post",
                data:{username:username,content:content},
                success:function (res) {
                    if (res=="success"){
                        alert("留言成功");
                        $("#m-content").val(null);
                    }
                }
            })
        });
    },
    getAdminMessage:function (username) {
        $.ajax({
            url:"/user/getAMessageByUsername",
            data:{username:username},
            type:"post",
            success:function (data) {
                $("#s-div").empty();
                if (data[0]!=null){
                    for (var i=0;i<data.length;i++){
                        $("#s-div").append("<p>"+data[i].from+":&nbsp"+data[i].content+"&nbsp&nbsp"+new Date(data[i].time).toLocaleString()+"</p>");
                    }
                }else {
                    $("#s-div").append("<p>没有留言消息！</p>")
                }
            }
        });
    }
});