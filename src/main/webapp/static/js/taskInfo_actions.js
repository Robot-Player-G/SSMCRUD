$(document).ready(function () {
    var task_id = $("#task_id").val();
    var receiver = $("#user").val();
    $.ajax({
        url:"/task/getTaskInfoById",
        type:"post",
        data:"task_id="+task_id,
        success:function (data) {
            console.log(data);
            var statusMsg;
            $("#title").html(data.task_title);
            $("#publisher").html(data.publisher);
            $("#content").html(data.task_content);
            $("#pay").html(data.pay);
            $("#publish_time").html(data.publish_time);
            if (data.receiver == null){
                statusMsg = "无人接取";
                $("#status").html(statusMsg);
            }else{
                if (data.finish_time == null){
                    statusMsg = "已有人接取";
                    $("#status").html(statusMsg);
                    $("#receive-btn").prop("class","btn btn-default");
                    $("#receive-btn").unbind('click');
                }else {
                    statusMsg = "已完成";
                    $("#status").html(statusMsg);
                    $("#receive-btn").prop("class","btn btn-default");
                    $("#receive-btn").unbind('click');
                }
            }
        }
    });
    $("#receive-btn").bind('click',function () {
        var publisher = $("#publisher").html();
        console.log(receiver==publisher);
        console.log(receiver);
        console.log(publisher);
        if (receiver==publisher){
            alert("不可以接取自己发布的任务哟！");
        }else{
            if(confirm("确认接取该任务吗？")){
                $.ajax({
                    url:"/task/updateReceiver",
                    type:"post",
                    data:{task_id:task_id,receiver:receiver},
                    success:function (data) {
                        if (data){
                            // alert("接取成功!");
                            location.reload();
                        }
                    },
                    error:function(xhr){
                        console.log(xhr.responseText);
                    }
                });
            }
        }
    });
    $("#back_btn").bind("click",function () {
        $.ajax({
            url:"/jump/backToTaskCenterFocus",
            type:"post",
            data:"username="+receiver,
        });
        window.location.href="/jump/backToTaskCenterFocus"
    });
});