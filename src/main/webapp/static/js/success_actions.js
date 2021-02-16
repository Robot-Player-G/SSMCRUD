$(document).ready(function () {
    var isSuccess = $("#isSuccess-input").val();
    var username = $("#user-input").val();
    var backFlag = $("#back-flag").val();
    var searchTxt;
    var websocket;
    if('WebSocket' in window) {
        console.log("此浏览器支持websocket");
        websocket = new WebSocket("ws://127.0.0.1:8089/websocketDemo/"+username);
    } else if('MozWebSocket' in window) {
        alert("此浏览器只支持MozWebSocket");
    } else {
        alert("此浏览器只支持SockJS");
    }
    websocket.onopen = function(evnt) {
        console.log("链接服务器成功!");
    };
    if (isSuccess=="success"){
        $("#isSuccess-input").val(null);
        alert("任务发布成功");
    }
    if (backFlag == "taskCenter"){
        $("#task-center-li").addClass("active");
        $.showTaskCenter(username);
    }else if(backFlag == "homepage"){

    }
    if (username=='')
    {
        username=$("#publisher-input").val();
        $("#user-input").val(username);
        $("#user_center").html("用户"+username);
        $("#publisher-input").val(null);
    }
    $.liClick();
    //查看首页
    $("#home_page").bind('click',function () {
        $.show_Home_Page(username);
    });
    //前往发布任务页面
    $.gotoPublishTask(username);
    //查看任务中心
    $("#task_center").bind('click',function () {
        $.showTaskCenter(username);
    });
    //查看交易案例
    $("#list").bind('click',function () {
        $("#panel-heading").html("案例中心");
        $("#panel-body").empty();
        $("#panel-body").append("<h2>案例:</h2>");
    });
    $("#search-input").bind('change',function () {
        //当用户键入关键字后，在此执行向后台传值进而访问数据库
    });
    //智能推荐数据库中已存在的任务
    $("#search-input").bind("keyup",function () {
         searchTxt = $("#search-input").val();
         console.log("下拉推荐");
         $("#search-div").append("<ul id='search-ul' class='dropdown-menu' aria-labelledby='dropdownMenu1'></ul>");
         $("#search-ul").append("<li><a href='#'>"+searchTxt+"</a></li>");
    });
    //搜索功能
    $("#search-button").bind('click',function () {
        //当用户点击搜索按钮后，在此向后台进行传值
        $.search_For_Task(searchTxt);
    });
    //用户中心
    $("#user_center").bind('click',function () {
        $.toUserCenter(username);
    });
    //个人信息
    $("#user_information").bind("click",function () {
        $.toUserCenter(username);
    });
    //历史交易
    $("#history_deal").bind("click",function () {
        $.gotoHistoryDeal(username);
    });
    //用户退出
    $("#sign-out").bind("click",function () {
        window.location.href="/user/userSignOut";
    });
});
$.extend({
    taskInfo:function (username) {
        //点击任务详情
        $("tr").bind('click',function () {
            //这个this是指触发事件的那个<tr>，所以取的值也就是相对应的值
            var trFlag = $(this).attr("name");
            $.ajax({
                type:"POST",
                url:"/task/gotoTaskInfo",
                data:{trFlag:trFlag,username:username}
            });
            window.location.href="/task/gotoTaskInfo";
        });
    },
    gotoPublishTask:function (username) {
        //前往发布任务页面
        $("#publish-task").bind('click',function () {
            // 使用ajax传值到controller层进行页面跳转无法成
            // return返回值会全部变成数据传回
            $.ajax({
                url:"/task/gotoPublishTask",
                type:"post",
                data:"username="+username
            });
            window.location.href="/task/gotoPublishTask";
        });
    },
    liClick:function () {
        $("li").bind("click",function () {
            $("li").removeClass("active");
            $(this).addClass("active");
        });
    },
    toUserCenter:function (param) {
        // 使用ajax传值到controller层进行页面跳转无法成
        // return返回值会全部变成数据传回
        $.ajax({
            url:"/user/userCenter",
            type:"post",
            data:"username="+param
        });
        window.location.href="/user/userCenter";
    },
    showTaskCenter:function (username) {
        $("#panel-heading").html("任务中心");
        $("#panel-body").empty();
        $("#panel-body").append("<table class='table table-striped' id='task_show'></table>");
        $.ajax({
            type:"POST",
            url:"/task/findAllTask",
            data:"username="+username,
            dataType:"json",
            success:function (data) {
                $("#task_show").append("<tr><td>标题</td><td>发布者</td><td>任务内容</td><td>任务发布时间</td><td>报酬</td><td>任务状态</td></tr>");
                $.each(data,function (i,test) {
                    // console.log(test);  test为一个一元组
                    // console.log(test);
                    var statusMsg;
                    if (test.receiver == null){
                        statusMsg = "无人接取";
                    }else {
                        if (test.finish_time == null){
                            statusMsg = "已有人接取";

                        }else {
                            statusMsg = "已完成";
                        }
                    }
                    var date = new Date(test.publish_time).toLocaleString();
                    $("#task_show").append("<tr name='"+test.task_id+"'><td>"+test.task_title+"</td><td>" +
                        test.publisher+"</td><td>" +
                        test.task_content+"</td><td>" +
                        date+"</td><td>" +
                        test.pay+"</td>" +
                        "<td>"+statusMsg+"</td></tr>");
                });
                console.log($("#task_show").html());
                $.taskInfo(username);
            }
        });
    },
    show_Home_Page:function (username) {
        $("#panel-body").empty();
        $("#panel-heading").html("首页");
        $("#panel-body").append("<h2>welcome!</h2>");
        $("#panel-body").append("<a href='javascript:void(0)' id='publish-task'>发起任务!</a>");
        $.gotoPublishTask(username);
    },
    search_For_Task:function (searchTxt) {
        $.ajax({
            url:"/task/getTaskInfoByKeyWord",
            data:{keyword:searchTxt},
            type:"post",
            success:function (data) {
                $("#panel-body").empty();
                $("#panel-body").append("<table class='table table-striped' id='task_show'></table>");
                $("#task_show").append("<tr><td>标题</td><td>发布者</td><td>任务内容</td><td>任务发布时间</td><td>报酬</td><td>任务状态</td></tr>");
                $.each(data,function (i,test) {
                    // console.log(test);  test为一个一元组
                    // console.log(test);
                    var statusMsg;
                    if (test.receiver == null){
                        statusMsg = "无人接取";
                    }else {
                        if (test.finish_time == null){
                            statusMsg = "已有人接取";

                        }else {
                            statusMsg = "已完成";
                        }
                    }
                    var date = new Date(test.publish_time).toLocaleString();
                    $("#task_show").append("<tr name='"+test.task_id+"'><td>"+test.task_title+"</td><td>" +
                        test.publisher+"</td><td>" +
                        test.task_content+"</td><td>" +
                        date+"</td><td>" +
                        test.pay+"</td>" +
                        "<td>"+statusMsg+"</td></tr>");
                });
            }
        });
    },
    gotoHistoryDeal:function (username) {
        $.ajax({
            url:"/user/historyDeal",
            type:"post",
            data:"username="+username
        });
        window.location.href="/user/historyDeal";
    }
});