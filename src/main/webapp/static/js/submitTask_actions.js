$(document).ready(function () {
    var taskId = $("#taskId").val();
    $("#submit").bind("click",function () {
        var fd = new FormData();
        fd.append("name","form-test");
        fd.append("file",$("#file")[0].files[0]);
        $.ajax({
            url: 'http://localhost:8088/eth/uploadFileToEth',
            type: 'post',
            processData: false,
            contentType: false,
            data: fd,
            success: function (res) {
                if(res == "error"){
                    alert("提交失败！请查看你上传的文件的大小是否小于等于2.08kb");
                }else{
                    console.log("你的交易hash为:"+res);
                    //为后台的taskInfo_tbl表设置完成时间
                    $.finishTask(taskId,res);
                }
            }
        });
    });
});
$.extend({
    finishTask:function (taskId,hash) {
        $.ajax({
            url:"/task/finishTask",
            type: "post",
            data:{task_id:taskId},
            success:function (res) {
                if (res == "success"){
                    alert("提交任务成功！")
                    $.ajax({
                        url:"/task/saveHash",
                        type:"post",
                        data:{task_id:taskId,hash:hash},
                        success:function (data) {
                            alert("任务提交成功!");
                        }
                    });
                }else {
                    alert("任务已提交，请勿重复提交！")
                }
            }
        });
    }
});