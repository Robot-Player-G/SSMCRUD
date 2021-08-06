$(document).ready(function () {
    $.getAdminMessage();
    $("#show-message").bind("click",function () {
        $("#send-message").removeClass("active");
        $("#show-message").addClass("active");
        $.getAdminMessage();
    });
    $("#send-message").bind("click",function () {
        $("#show-message").removeClass("active");
        $("#send-message").addClass("active");
        $.sendFunction();
    });
});
$.extend({
    getAdminMessage:function () {
        $("#panel-heading").empty();
        $("#panel-heading").html("<b>查看留言</b>");
        $("#panel-body").empty();
        $("#panel-body").append("<br/>" +
            "<div class='container' id='s-div' style='width: 80%;height: 100px;margin:0 0 0 0 ;border: 1px solid black;border-radius: 4px;overflow-y: auto'>" +
            "</div>");
        $.ajax({
            url:"/user/getAdminMessage",
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
    },
    sendFunction:function () {
        $("#panel-heading").empty();
        $("#panel-heading").html("<b>发送留言</b>");
        $("#panel-body").empty();
        $("#panel-body").append("<br/>" +
            "<div class='container' id='send-div'>" +
            "<form id='messageForm'>" +
            "      <div class='form-group'>" +
            "           <label>留言接收者:</label>" +
            "           <input id='user' name='to' type='text' style='width: 150px;height: 30px;border-radius: 3px;' placeholder='请输入接收者'>"+
            "      </div></br>" +
            "      <div class='form-group'>" +
            "          <label>内&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp容:</label>" +
            "          <input id='message' name='content' type='text' style='width:150px;height: 80px;border-radius: 3px;' placeholder='请输入留言内容'>" +
            "      </div>" +
            "<input type='hidden' id='to-flag' value=''>"+
            "<input type='hidden' id='content-flag' value=''>"+
            "      <div class='form-group'> " +
            "          <button type='button' class='btn btn-default' id='sendMessage'>确认发送</button>" +
            "      </div> "+
            "</form>"+
            "</div>");
        $("#user").bind("keyup",function () {
            $("#to-flag").val($("#user").val());
        });
        $("#message").bind("keyup",function () {
            $("#content-flag").val($("#message").val());
        });
        $.sendToUser();
    },
    sendToUser:function () {
        $("#sendMessage").bind("click",function () {
            var to = $("#to-flag").val();
            var content = $("#content-flag").val();
            console.log(content);
            $.ajax({
                url:"/user/adminSendMessage",
                type:"post",
                data:{to:to,content:content},
                success:function (data) {
                    alert("发送成功");
                    location.reload();
                },
                error:function (data) {
                    console.log(data.responseText);
                }
            });
        });
    }
});