$(document).ready(function () {
    $.userManagement();
    $("#user-management").bind("click",function () {
        $.userManagement();
    });
    $("#recharge-record").bind("click",function () {
        $.rechargeRecord();
    });
});
$.extend({
    deleteUser:function (param) {
        $("#delete-"+param.username).bind("click",function () {
            $.ajax({
                url:"/user/adminDeleteUser",
                type:"post",
                data:{username:param.username},
                success:function (data) {
                    if (data == "success"){
                        alert("删除用户"+param.username+"成功!");
                    }
                    location.reload();
                }
            });
        });
    },
    deleteRecharge:function (param) {
        $("#delete-"+param.recharge_id).bind("click",function () {
            $.ajax({
                url:"/user/adminDeleteRechargeRecord",
                type:"post",
                data:{recharge_id:param.recharge_id},
                success:function (data) {
                    if (data == "success"){
                        alert("删除充值记录"+param.recharge_id+"成功!");
                    }
                    location.reload();
                }
            });
        });
    },
    userManagement:function () {
        $.ajax({
            url:"/user/adminGetAllUser",
            type:"post",
            success:function (data) {
                $("#page-header").html("用户管理");
                $("#thead").html("");
                $("#tbody").html("");
                if (data.count==0){
                    $("#thead").html("<p>没有注册用户!</p>");
                    $("#page-ul").html("");
                }else {
                    $("#thead").append("<tr>" +
                        "                    <th>用户名</th>" +
                        "                    <th>密码</th>" +
                        "                    <th>昵称</th>" +
                        "                    <th>余额</th>" +
                        "                </tr>");
                    for (var a = 0;a<5;a++){
                        if (a==data.count){
                            break;
                        }
                        $("#tbody").append("<tr></tr><th scope='row'>"+data.List[a].username+"</th>" +
                            "                    <td>"+data.List[a].password+"</td>" +
                            "                    <td>"+data.List[a].nickname+"</td>" +
                            "                    <td>"+data.List[a].balance+"</td>" +
                            "                    <td>" +
                            "                        <div role='presentation'>" +
                            "                            <button class='btn btn-default dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false' id='delete-"+data.List[a].username+"'>" +
                            "                                删除" +
                            "                            </button>" +
                            "                        </div>" +
                            "                    </td></tr>");
                        $.deleteUser(data.List[a]);
                    }
                    $("#page-ul").html("");
                    $("#page-ul").append(" <li class='disabled'><a href='#' aria-label='Previous'>" +
                        "<span aria-hidden='true'>&laquo;</span></a></li>");
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
                        $("#tbody").html("");
                        $("#thead").html("");
                        $("#thead").append("<tr>" +
                            "                    <th>用户名</th>" +
                            "                    <th>密码</th>" +
                            "                    <th>昵称</th>" +
                            "                    <th>余额</th>" +
                            "                </tr>");
                        var toPage = this.firstChild.text;
                        toPage = toPage*5;
                        for (var b=toPage-5;b<toPage;b++){
                            if (b > data.count-1){
                                break;
                            }
                            $("#tbody").append("<tr></tr><th scope='row'>"+data.List[b].username+"</th>" +
                                "                    <td>"+data.List[b].password+"</td>" +
                                "                    <td>"+data.List[b].nickname+"</td>" +
                                "                    <td>"+data.List[b].balance+"</td>" +
                                "                    <td>" +
                                "                        <div role='presentation'>" +
                                "                            <button class='btn btn-default dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false' id='delete-"+data.List[b].username+"'>" +
                                "                                删除" +
                                "                            </button>" +
                                "                        </div>" +
                                "                    </td></tr>");
                            $.deleteUser(data.List[b]);
                        }
                    });
                }
            }
        });
    },
    rechargeRecord:function () {
        $.ajax({
            url:"/user/adminGetRechargeRecord",
            type:"post",
            success:function (data) {
                $("#page-header").html("用户充值记录");
                $("#thead").html("");
                $("#tbody").html("");
                if (data.count==0){
                    $("#thead").html("<p>没有充值记录!</p>");
                    $("#page-ul").html("");
                }else {
                    $("#thead").append("<tr>" +
                        "                    <th>充值记录id</th>" +
                        "                    <th>充值用户</th>" +
                        "                    <th>充值时间</th>" +
                        "                    <th>充值金额</th>" +
                        "                </tr>");
                    for (var a = 0;a<5;a++){
                        if (a==data.count){
                            break;
                        }
                        var date = new Date(data.List[a].time).toLocaleString();
                        $("#tbody").append("<tr></tr><th scope='row'>"+data.List[a].recharge_id+"</th>" +
                            "                    <td>"+data.List[a].user+"</td>" +
                            "                    <td>"+date+"</td>" +
                            "                    <td>"+data.List[a].sum+"</td>" +
                            "                    <td>" +
                            "                        <div role='presentation'>" +
                            "                            <button class='btn btn-default dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false' id='delete-"+data.List[a].task_id+"'>" +
                            "                                删除" +
                            "                            </button>" +
                            "                        </div>" +
                            "                    </td></tr>");
                        $.deleteUser(data.List[a]);
                    }
                    $("#page-ul").html("");
                    $("#page-ul").append(" <li class='disabled'><a href='#' aria-label='Previous'>" +
                        "<span aria-hidden='true'>&laquo;</span></a></li>");
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
                        $("#tbody").html("");
                        $("#thead").html("");
                        $("#thead").append("<tr>" +
                            "                    <th>充值记录id</th>" +
                            "                    <th>充值用户</th>" +
                            "                    <th>充值时间</th>" +
                            "                    <th>充值金额</th>" +
                            "                </tr>");
                        var toPage = this.firstChild.text;
                        toPage = toPage*5;
                        for (var b=toPage-5;b<toPage;b++){
                            if (b > data.count-1){
                                break;
                            }
                            var date = new Date(data.List[b].time).toLocaleString();
                            $("#tbody").append("<tr></tr><th scope='row'>"+data.List[b].recharge_id+"</th>" +
                                "                    <td>"+data.List[b].user+"</td>" +
                                "                    <td>"+date+"</td>" +
                                "                    <td>"+data.List[b].sum+"</td>" +
                                "                    <td>" +
                                "                        <div role='presentation'>" +
                                "                            <button class='btn btn-default dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false' id='delete-"+data.List[b].recharge_id+"'>" +
                                "                                删除" +
                                "                            </button>" +
                                "                        </div>" +
                                "                    </td></tr>");
                            $.deleteRecharge(data.List[b]);
                        }
                    });
                }
            }
        });
    }
});