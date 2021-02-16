$(document).ready(function () {
     var publisher = $("#publisher").val();
     if (publisher != ""){
          $("#show_publisher").html(publisher+"要发布任务");
     }
     $("#back_btn").bind("click",function () {
          $.ajax({
               url:"/jump/backToHomePageFocus",
               type:"post",
               data:"username="+publisher,
          });
          window.location.href="/jump/backToHomePageFocus"
     });
});