$(document).ready(function () {
    var p_flag = $("#isSuccess-input").val();
    var username = $("#user-input").val();
    var backFlag = $("#back-flag").val();
    var rechargeFlag = $("#recharge-flag").val();
    var delimiter = ".--.--.";
    var searchTxt;
    console.log(p_flag);
    var num = 1;
    var websocket;
    $('[data-toggle="popover"]').popover();
    if('WebSocket' in window) {
        console.log("此浏览器支持websocket");
        websocket = new WebSocket("ws://127.0.0.1:8080/websocketDemo/"+username);
    } else if('MozWebSocket' in window) {
        alert("此浏览器只支持MozWebSocket");
    } else {
        alert("此浏览器只支持SockJS");
    }
    websocket.onopen = function(evnt) {
        $("#tou").html("链接服务器成功!");
        //获取未读消息的条数
        $.getUnReadMessageCount(username);
    };
    websocket.onmessage = function(evnt) {
        num = $("#new").html();
        num++;
        $("#new").html(num);
    };
    websocket.onerror = function(evnt) {
        console.log(evnt.data);
    };
    websocket.onclose = function(evnt) {

    }
    if (rechargeFlag == "success"){
        alert("充值成功！");
        $("#recharge-flag").val(null);
    }
    if (p_flag == "success"){
        $("#isSuccess-input").val(null);
        alert("任务发布成功！");
    }else if(p_flag == "error"){
        $("#isSuccess-input").val(null);
        alert("余额不够，请先充值余额！");
    }
    if (backFlag == "taskCenter"){
        $("#task-center-li").addClass("active");
        $.findTaskByPage(username);
    }else if(backFlag == "homepage"){
        $("#home_page").addClass("active");
        $.show_Home_Page(username);
    }
    if (username==''){
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
        // $.showTaskCenter(username);
        $.findTaskByPage(username);
    });
    //查看交易案例
    $("#list").bind('click',function () {
        $.findShareTask(username);
    });
    // $("#search-input").bind('change',function () {
    //     //当用户键入关键字后，在此执行向后台传值进而访问数据库
    // });
    //获取搜索输入框里的值
    $("#search-input").bind("keyup",function () {
         searchTxt = $("#search-input").val();
    });
    //搜索功能
    $("#search-button").bind('click',function () {
        //当用户点击搜索按钮后，在此向后台进行传值
        $.search_For_Task(searchTxt);
    });
    //确认充值金额
    $("#getCode_btn").bind("click",function () {
        var formData = $("#rechargeForm").serializeArray();
        var tradeNo = username+"-"+$("#rechargeIT").val()+"-"+new Date().getTime();
        console.log(formData[0].value);
        $("#payImg").html("");
        $("#check_div").html("");
        $("#payImg").append("<p align='center' style='margin: 0px'><img alt='支付宝二维码' " +
            "src='/alipay/rechargePay?tradeNo="+tradeNo+"'></p><p align='center'>请扫码支付！</p>");
        $("#check_div").append("<input id='check_btn' type='button' name='"+tradeNo+"' class='form-control'" +
            " value='我已支付'>");
        $("#check_btn").bind("click",function () {
            $.ajax({
                url:"/alipay/checkPay",
                type:"post",
                data:{tradeNo:$("#check_btn").attr("name")},
                success:function (result) {
                    if (result == "success"){
                        alert("充值"+tradeNo.split("-")[2]+"元成功！");
                    }else {
                        alert("请确认是否已支付！");
                    }
                }
            });
        });
    })
    //查询用户是否已付款
    $("#recharge_btn").bind("click",function () {
        var formData = $("#rechargeForm").serializeArray();

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
            if (trFlag!=undefined){
                window.location.href="/task/gotoTaskInfo?trFlag="+trFlag+"&username="+username;
            }
        });
    },
    gotoPublishTask:function (username) {
        //前往发布任务页面
        $("#publish-task").bind('click',function () {
            // 使用ajax传值到controller层进行页面跳转无法成
            // return返回值会全部变成数据传回
            window.location.href="/task/gotoPublishTask?username="+username;
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
        window.location.href="/user/userCenter?username="+param;
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
    findTaskByPage:function(username) {
        $("#panel-heading").html("任务中心");
        $("#panel-body").empty();
        $("#panel-body").append("<table class='table table-striped' id='task_show'></table>");
        $.ajax({
            url:"/task/findAllTask1",
            type:"post",
            data:"username="+username,
            success:function (data) {
                $("#task_show").append("<tr><td>标题</td><td>发布者</td><td>任务内容</td><td>任务发布时间</td><td>报酬</td><td>任务状态</td></tr>");
                for (var a = 0;a<5;a++){
                    if (a==data.count){
                        break;
                    }
                    var statusMsg;
                    if (data.List[a].receiver == null){
                        statusMsg = "无人接取";
                    }else {
                        if (data.List[a].finish_time == null){
                            statusMsg = "已有人接取";
                        }else {
                            statusMsg = "已完成";
                        }
                    }
                    var date = new Date(data.List[a].publish_time).toLocaleString();
                    $("#task_show").append("<tr name='"+data.List[a].task_id+"'><td>"+data.List[a].task_title+"</td><td>" +
                        data.List[a].publisher+"</td><td>" +
                        data.List[a].task_content+"</td><td>" +
                        date+"</td><td>" +
                        data.List[a].pay+"</td>" +
                        "<td>"+statusMsg+"</td></tr>");
                }
                $("#panel-body").append("<div class='container' id='Page'>" +
                   "<nav aria-label='Page navigation'>"+
                      "<ul class='pagination' id='page-ul'>"+
                    "    <li>" +
                    "      <a href='#' aria-label='Previous'>" +
                    "        <span aria-hidden='true'>&laquo;</span>" +
                    "      </a>" +
                    "    </li>" +
                      "</ul>" +
                   "</nav>"+
                "</div>");
                var pageIndex = data.count;
                for (var i=1;;i++){
                    if (pageIndex>0){
                        $("#page-ul").append("<li name='page'><a href='#'>"+i+"</a></li>");
                    }else {
                        break;
                    }
                    pageIndex = pageIndex-5;
                }
                $("#page-ul").append(
                    "    <li>" +
                    "      <a href='#' aria-label='Next'>" +
                    "        <span aria-hidden='true'>&raquo;</span>" +
                    "      </a>" +
                    "    </li>");
                $("li[name=page]").bind("click",function () {
                    $("#task_show").html("");
                    $("#task_show").append("<tr><td>标题</td><td>发布者</td><td>任务内容</td><td>任务发布时间</td><td>报酬</td><td>任务状态</td></tr>");
                    var toPage = this.firstChild.text;
                    toPage = toPage*5;
                    for (var b=toPage-5;b<toPage;b++){
                        if (b > data.count-1){
                            break;
                        }
                        var statusMsg;
                        if (data.List[b].receiver == null){
                            statusMsg = "无人接取";
                        }else {
                            if (data.List[b].finish_time == null){
                                statusMsg = "已有人接取";
                            }else {
                                statusMsg = "已完成";
                            }
                        }
                        var date = new Date(data.List[b].publish_time).toLocaleString();
                        $("#task_show").append("<tr name='"+data.List[b].task_id+"'><td>"+data.List[b].task_title+"</td><td>" +
                            data.List[b].publisher+"</td><td>" +
                            data.List[b].task_content+"</td><td>" +
                            date+"</td><td>" +
                            data.List[b].pay+"</td>" +
                            "<td>"+statusMsg+"</td></tr>");
                    }
                    $.taskInfo(username);
                });
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
    findShareTask:function(username){
        $("#panel-heading").html("案例共享");
        $("#panel-body").empty();
        $("#panel-body").append("<table class='table table-striped' id='task_show'></table>");
        $.ajax({
            type:"POST",
            url:"/task/findShareTask",
            data:"username="+username,
            dataType:"json",
            success:function (data) {
                $("#task_show").append("<tr><td>标题</td><td>发布者</td><td>任务内容</td><td>任务发布时间</td><td>报酬</td><td>任务状态</td></tr>");
                $.each(data,function (i,test) {
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
                $.taskInfo(username);
            }
        });
    },
    search_For_Task:function (searchTxt) {
        $.ajax({
            url:"/task/getTaskInfoByKeyWord",
            data:{keyword:searchTxt},
            type:"post",
            success:function (data) {
                console.log(data);
                if (data[0]==null){
                    $("#panel-body").empty();
                    $("#panel-heading").html("查找结果");
                    $("#panel-body").append("<p>没有查找到相关任务信息！</p>");
                }else {
                    $("#panel-body").empty();
                    $("#panel-heading").html("查找结果");
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
            }
        });
    },
    gotoHistoryDeal:function (username) {
        window.location.href="/user/historyDeal?username="+username;
    },
    getUnReadMessageCount:function (username) {
        $.ajax({
            url:"/message/getUnreadMessageCount",
            type:"post",
            data:{username:username},
            success:function (result) {
                $("#new").html(result);
            }
        });
    }
});