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
        websocket = new WebSocket("ws://127.0.0.1:8089/websocketDemo/"+userA);
    } else if('MozWebSocket' in window) {
        alert("此浏览器只支持MozWebSocket");
    } else {
        alert("此浏览器只支持SockJS");
    }
    websocket.onopen = function(evnt) {
        $("#tou").html("链接服务器成功!")
    };
    websocket.onmessage = function(evnt) {
        var str = evnt.data.split(delimiter);
        
        if (str[1] == userB && str[2]==userA)
        {
            $("#msg").html($("#msg").html() + "<br/>" + str[0]);
        }
    };
    websocket.onerror = function(evnt) {
        console.log(evnt.data);
    };
    websocket.onclose = function(evnt) {
        $("#tou").html("与服务器断开了链接!")
    }
    $.timerFunction(userB);
    setInterval(function(){
        $.timerFunction(userB);
    },2000);
    $("#message").bind("keyup",function () {
        text = $("#message").val();
        message = "用户"+userA+"---:"+text+delimiter+userA+delimiter+userB;
        myMessage = spaceStr+spaceStr+"我---:"+text;
    });
    $('#send').bind('click', function() {
        $.sendMessage(websocket,userA,userB,message);
        $("#msg").html($("#msg").html()+"<br/>"+myMessage);
        $("#message").val("");
    });
});
$.extend({
    sendMessage:function(websocket,A,B,message) {
        if(websocket != null) {
            $.ajax({
                url:"http://localhost:8089/message/TestWS?userId="+B+"&message="
                    +message,
                type:"GET",
                success: function(result){
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
    }
});