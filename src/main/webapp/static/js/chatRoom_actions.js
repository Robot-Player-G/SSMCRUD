$(document).ready(function () {
    var userA = $("#userA").val();
    var userB = $("#userB").val();
    var text;
    var message,myMessage;
    var delimiter = ".--.--.";
    var spaceStr = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
        "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
        "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
        "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+
        "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
    var websocket;
    if('WebSocket' in window) {
        console.log("此浏览器支持websocket");
        websocket = new WebSocket("ws://127.0.0.1:8080/websocketDemo/"+userA);
    } else if('MozWebSocket' in window) {
        alert("此浏览器只支持MozWebSocket");
    } else {
        alert("此浏览器只支持SockJS");
    }
    websocket.onopen = function(evnt) {
        $("#tou").html("链接服务器成功!");
        //获取消息记录
        $.getOldMessage(userA,userB,spaceStr);
    };
    websocket.onmessage = function(evnt) {
        $("#msg").html($("#msg").html() + "<br/>" + userB+"---:"+evnt.data);
    };
    websocket.onerror = function(evnt) {
        console.log(evnt.data);
    };
    websocket.onclose = function(evnt) {
        $("#tou").html("与服务器断开了链接!")
    }
    $.getMessage(userA,userB);
    $.timerFunction(userB);
    setInterval(function(){
        $.timerFunction(userB);
    },2000);
    $("#message").bind("keyup",function () {
        text = $("#message").val();
        message = text;
        myMessage = spaceStr+spaceStr+"我---:"+text;
    });
    $('#send').bind('click', function() {
        $.sendMessage(websocket,userA,userB,message);
        $("#msg").html($("#msg").html()+"<br/>"+myMessage);
        $("#message").val("");
    });
    $("#send-btn").bind("click",function () {
        var content = $("#m-flag").val();
        if (content!=""){
            $.ajax({
                url:"/user/leaveMessage",
                type:"post",
                data:{userA:userA,userB:userB,content:content},
                success:function () {
                     alert("发送成功！");
                     location.reload();
                }
            });
        }else {
            alert("请先输入留言内容！");
        }
    });
});
$.extend({
    sendMessage:function(websocket,A,B,message) {
            if(websocket != null) {
                $.ajax({
                    url:"/message/sendMessage",
                    type:"post",
                    data:{userA:A,userB:B,message:message},
                    success: function(result){
                        if(result=="success"){
                            alert("发送消息成功！");
                        }else {
                            alert("已发送发送离线消息！");
                        }
                    }
                });
            } else {
                alert('未与服务器链接.');
            }
    },
    getUserStatus:function (userB) {
        //查询用户是否在线
        $.ajax({
            url:"/message/getUserStatus",
            type:"post",
            data:{userB:userB},
            success:function (result) {
                if (result == "true"){
                    $("#flag").val("true");
                }else {
                    $("#flag").val("false");
                }
            }
        });
    },
    timerFunction:function (userB) {
        //用户B在线状态
        $.getUserStatus(userB);
        var userBStatus = $("#flag").val();
        if (userBStatus == "true"){
            $("#user-status").html("用户"+userB+"在线");
        }else {
            $("#user-status").html("用户"+userB+"不在线");
        }
    },
    getMessage:function (userA,userB) {
        $("#addname").bind("keyup",function () {
            $("#m-flag").val($("#addname").val());
        });
        $.ajax({
            url:"/user/getMessage",
            type:"post",
            data: {userA:userA,userB:userB},
            success:function (data) {
                $("#leave-message").empty();
                if (data[0]!=null){
                    for (var i=0;i<data.length;i++){
                        $("#leave-message").append("<p>"+data[i].from+":&nbsp"+data[i].content+"&nbsp&nbsp"+new Date(data[i].time).toLocaleString()+"</p>");
                    }
                }else {
                    $("#leave-message").append("<p>没有留言消息！</p>")
                }
            }
        });
    },
    getOldMessage:function (userA,userB,spaceStr) {
        $.ajax({
            url:"/message/getOldMessages",
            type:"post",
            data:{username:userA,from:userB},
            success:function (result) {
                console.log(0);
                console.log(result[0].content);
                for (var i=0;i<result.length;i++){
                    console.log(1);
                    if (result[i].from==userA){
                        console.log(2);
                        console.log(result[i].content);
                        if (i==0){
                            $("#msg").html($("#msg").html()+spaceStr+spaceStr+"我---:"+result[i].content);
                        }else {
                            $("#msg").html($("#msg").html() + "<br/>" + spaceStr + spaceStr +"我---:" + result[i].content);
                        }
                    }else {
                        console.log(3);
                        console.log(result[i].content);
                        if (i == 0) {

                            $("#msg").html($("#msg").html() + userB + "---:" + result[i].content);
                        } else {
                            $("#msg").html($("#msg").html() + "<br/>" + userB + "---:" + result[i].content);
                        }
                    }
                }
            }
        });
    }
});