$(document).ready(function () {
     var publisher = $("#publisher").val();
     if (publisher != ""){
          $("#show_publisher").html(publisher+"要发布任务");
     }
     $("#back_btn").bind("click",function () {
          // $.ajax({
          //      url:"/jump/backToHomePageFocus",
          //      type:"post",
          //      data:"username="+publisher,
          // });
          window.location.href="/jump/backToHomePageFocus?username="+publisher;
     });
     $("#publish-btn").bind("click",function () {
          console.log($("#task-form").serialize());
          console.log($("#task-form").serializeArray());
          $.ajax({
               url:"/task/publishTask",
               type: "post",
               data:$("#task-form").serialize(),
               success:function (data) {
                    console.log(data);
                    if (data == "success"){
                         alert("任务发布成功！");
                         // $.ajax({
                         //      url:"/jump/backToHomePageFocus",
                         //      type:"post",
                         //      data:"username="+publisher,
                         // });
                         window.location.href="/jump/backToHomePageFocus?username="+publisher;

                    }else {
                         alert("余额不足，请先充值余额！");
                         // $.ajax({
                         //      url:"/jump/backToHomePageFocus",
                         //      type:"post",
                         //      data:"username="+publisher,
                         // });
                         window.location.href="/jump/backToHomePageFocus?username="+publisher;
                    }
               }
          });
     });
});