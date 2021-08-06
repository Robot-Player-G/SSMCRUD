$(document).ready(function () {
    $.taskManagement();
    $("#task-management").bind("click",function () {
        $.taskManagement();
    });
    $("#deal-record").bind("click",function () {
        $.dealRecord();
    });
});
$.extend({
    passTask:function (param) {
        $("#pass-"+param.task_id).bind("click",function () {
            if (confirm("确认让该任务通过审核吗？")){
                var reason = "";
                $.ajax({
                    url:"/task/adminCheckTask",
                    type:"post",
                    data:{task_id:param.task_id,reason:reason},
                    success:function (data) {
                        if (data == "success"){
                            alert("审核任务"+param.task_id+"成功!");
                        }
                        location.reload();
                    }
                });
            }
        });
    },
    notTask:function(param){
        $("#not-"+param.task_id).bind("click",function () {
            if (confirm("确认让该任务不通过审核吗？")){
                var reason = prompt("请输入不通过原因");
                $.ajax({
                    url:"/task/adminCheckTask",
                    type:"post",
                    data:{task_id:param.task_id,reason:reason},
                    success:function (data) {
                        if (data == "success"){
                            alert("审核任务"+param.task_id+"成功!");
                        }
                        location.reload();
                    }
                });
            }
        });
    },
    deleteDeal:function (param) {
        $("#delete-"+param.deal_id).bind("click",function () {
            $.ajax({
                url:"/task/adminDeleteDealRecord",
                type:"post",
                data:{deal_id:param.deal_id},
                success:function (data) {
                    if (data == "success"){
                        alert("删除交易记录"+param.deal_id+"成功!");
                    }
                    location.reload();
                }
            });
        });
    },
    taskManagement:function () {
        $.ajax({
            url:"/task/adminGetAllTask",
            type:"post",
            success:function (data) {
                $("#page-header").html("任务审核");
                $("#thead").html("");
                $("#tbody").html("");
                if (data.count==0){
                    $("#thead").html("<p>没有用户发布任务!</p>");
                    $("#page-ul").html("");
                }else {
                    $("#thead").append("<tr>" +
                        "                    <th>任务id</th>" +
                        "                    <th>任务标题</th>" +
                        "                    <th>任务内容</th>" +
                        "                    <th>发布者</th>" +
                        "                    <th>发布时间</th>" +
                        "                    <th>接取者</th>" +
                        "                    <th>完成时间</th>" +
                        "                    <th>报酬</th>" +
                        "                </tr>");
                    for (var a = 0;a<5;a++){
                        var receiver;
                        var finish_time;
                        if (a==data.count){
                            break;
                        }
                        if (data.List[a].receiver==null){
                            receiver = "无"
                        }else {
                            receiver = data.List[a].receiver;
                        }
                        if (data.List[a].finish_time==null){
                            finish_time = "未完成";
                        }else {
                            finish_time = new Date(data.List[a].finish_time).toLocaleString();
                        }
                        $("#tbody").append("<tr></tr><th scope='row'>"+data.List[a].task_id+"</th>" +
                            "                    <td>"+data.List[a].task_title+"</td>" +
                            "                    <td>"+data.List[a].task_content+"</td>" +
                            "                    <td>"+data.List[a].publisher+"</td>" +
                            "                    <td>"+new Date(data.List[a].publish_time).toLocaleString()+"</td>" +
                            "                    <td>"+receiver+"</td>" +
                            "                    <td>"+finish_time+"</td>" +
                            "                    <td>"+data.List[a].pay+"</td>" +
                            "                    <td>" +
                            "                        <div role='presentation'>" +
                            "                            <button class='btn btn-default dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false' id='pass-"+data.List[a].task_id+"'>" +
                            "                                通过" +
                            "                            </button>" +
                            "                        </div>" +
                            "                    </td>" +
                            "                    <td>" +
                            "                        <div role='presentation'>" +
                            "                            <button class='btn btn-default dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false' id='not-"+data.List[a].task_id+"'>" +
                            "                                不通过" +
                            "                            </button>" +
                            "                        </div>" +
                            "                    </td></tr>");
                        $.passTask(data.List[a]);
                        $.notTask(data.List[a]);
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
                            "                    <th>任务id</th>" +
                            "                    <th>任务标题</th>" +
                            "                    <th>任务内容</th>" +
                            "                    <th>发布者</th>" +
                            "                    <th>发布时间</th>" +
                            "                    <th>接取者</th>" +
                            "                    <th>完成时间</th>" +
                            "                    <th>报酬</th>" +
                            "                </tr>");
                        var toPage = this.firstChild.text;
                        toPage = toPage*5;
                        for (var b=toPage-5;b<toPage;b++){
                            if (b > data.count-1){
                                break;
                            }
                            if (data.List[b].receiver==null){
                                receiver = "无"
                            }else {
                                receiver = data.List[b].receiver;
                            }
                            if (data.List[b].finish_time==null){
                                finish_time = "未完成";
                            }else {
                                finish_time = new Date(data.List[b].finish_time).toLocaleString();
                            }
                            $("#tbody").append("<tr></tr><th scope='row'>"+data.List[b].task_id+"</th>" +
                                "                    <td>"+data.List[b].task_title+"</td>" +
                                "                    <td>"+data.List[b].task_content+"</td>" +
                                "                    <td>"+data.List[b].publisher+"</td>" +
                                "                    <td>"+new Date(data.List[b].publish_time).toLocaleString()+"</td>" +
                                "                    <td>"+receiver+"</td>" +
                                "                    <td>"+finish_time+"</td>" +
                                "                    <td>"+data.List[b].pay+"</td>" +
                                "                    <td>" +
                                "                        <div role='presentation'>" +
                                "                            <button class='btn btn-default dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false' id='pass-"+data.List[b].task_id+"'>" +
                                "                                通过" +
                                "                            </button>" +
                                "                        </div>" +
                                "                    </td>" +
                                "                    <td>" +
                                "                        <div role='presentation'>" +
                                "                            <button class='btn btn-default dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false' id='not-"+data.List[b].task_id+"'>" +
                                "                                不通过" +
                                "                            </button>" +
                                "                        </div>" +
                                "                    </td></tr>");
                            $.passTask(data.List[b]);
                            $.notTask(data.List[b]);
                        }
                    });
                }
            }
        });
    },
    dealRecord:function () {
        $.ajax({
            url:"/task/adminGetDealRecord",
            type:"post",
            success:function (data) {
                $("#page-header").html("用户交易记录");
                $("#thead").html("");
                $("#tbody").html("");
                if (data.count==0){
                    $("#thead").html("<p>没有交易记录!</p>");
                    $("#page-ul").html("");
                }else {
                    $("#thead").append("<tr>" +
                        "                    <th>id</th>" +
                        "                    <th>完成时间</th>" +
                        "                    <th>发起者</th>" +
                        "                    <th>完成者</th>" +
                        "                    <th>交易金额</th>" +
                        "                    <th>交易文件hash</th>" +
                        "                </tr>");
                    for (var a = 0;a<5;a++){
                        if (a==data.count){
                            break;
                        }
                        $("#tbody").append("<tr></tr><th scope='row'>"+data.List[a].deal_id+"</th>" +
                            "                    <td>"+new Date(data.List[a].time).toLocaleString()+"</td>" +
                            "                    <td>"+data.List[a].publisher+"</td>" +
                            "                    <td>"+data.List[a].receiver+"</td>" +
                            "                    <td>"+data.List[a].money+"</td>" +
                            "                    <td>"+data.List[a].hash+"</td>" +
                            "                    <td>" +
                            "                        <div role='presentation'>" +
                            "                            <button class='btn btn-default dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false' id='delete-"+data.List[a].deal_id+"'>" +
                            "                                删除" +
                            "                            </button>" +
                            "                        </div>" +
                            "                    </td></tr>");
                        $.deleteDeal(data.List[a]);
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
                            "                    <th>交易记录id</th>" +
                            "                    <th>交易时间</th>" +
                            "                    <th>交易发起者</th>" +
                            "                    <th>交易完成者</th>" +
                            "                    <th>交易金额</th>" +
                            "                    <th>交易文件hash</th>" +
                            "                </tr>");
                        var toPage = this.firstChild.text;
                        toPage = toPage*5;
                        for (var b=toPage-5;b<toPage;b++){
                            if (b > data.count-1){
                                break;
                            }
                            $("#tbody").append("<tr></tr><th scope='row'>"+data.List[b].deal_id+"</th>" +
                                "                    <td>"+new Date(data.List[b].time).toLocaleString()+"</td>" +
                                "                    <td>"+data.List[b].publisher+"</td>" +
                                "                    <td>"+data.List[b].receiver+"</td>" +
                                "                    <td>"+data.List[b].money+"</td>" +
                                "                    <td>"+data.List[b].hash+"</td>" +
                                "                    <td>" +
                                "                        <div role='presentation'>" +
                                "                            <button class='btn btn-default dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true' aria-expanded='false' id='delete-"+data.List[b].deal_id+"'>" +
                                "                                删除" +
                                "                            </button>" +
                                "                        </div>" +
                                "                    </td></tr>");
                            $.deleteDeal(data.List[b]);
                        }
                    });
                }
            }
        });
    }
});