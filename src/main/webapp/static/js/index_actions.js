$(document).ready(function () {
     var variable = $.getQueryVariable("param");
     if (variable==1){
         alert("用户注册成功!");
     }
     $("#randomCode").bind('click',function () {
         $("#randomCode").attr("src",function () {
              return this.src+"?"+Math.random();
         });
     });
     $("#check_div").bind("click",function () {
         console.log(variable);
         alert(variable);
     });
     $("input[type=radio][name=flag]").bind("change",function () {
         if (this.value == "admin"){
             $("#method-flag").val("PUT");
             console.log($("#method-flag").val());
         }else {
             $("#method-flag").val("POST");
             console.log($("#method-flag").val());
         }
     });
});
$.extend({
    getQueryVariable:function (variable) {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }
});