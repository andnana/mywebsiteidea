/**
 * Created by Administrator on 2017/12/17 0017.
 */
$(function(){
   console.log("dfaerewr")
    var localObj = window.location;
    console.log(localObj.pathname);
    var contextPath = localObj.pathname.split("/")[1];

    var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

    var server_context=basePath;
    console.log(server_context);
    $.ajax({
            url: basePath + "/index",
            data: {pageNumber:1},
            type:"get",
            success:function(data){
                console.log(data);
                initUsers(data);
                initPageInfo(data);
                initPageNav(data);

    }

    }
    );
    function initUsers(data){
        var users = data.myInfo.myInfo.list;
        $.each(users, function(index, user){
            console.log(user.username);
            var useridTd = $("<td></td>").append(user.id);
            var usernameTd = $("<td></td>").append(user.username);
            var tr = $("<tr></tr>").append(useridTd).append(usernameTd).appendTo("tbody");
            /**
              <td>
             <button class="btn btn-success btn-sm"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</button>
             <button class="btn btn-success btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除</button>


             </td>

             */
            var userBtnTd = $("<td></td>");
            var userEditBtn = $("<button></button>").addClass("btn btn-success btn-sm").append($("<span></span>").addClass("glyphicon glyphicon-pencil").attr("aria-hidden","true")).append("编辑");
            var userDeleteBtn = $("<button></button>").addClass("btn btn-success btn-sm").append($("<span></span>").addClass("glyphicon glyphicon-trash").attr("aria-hidden","true")).append("删除");
            userBtnTd.append(userEditBtn).append(" ").append(userDeleteBtn);
            tr.append(userBtnTd);
        });
    }
    function initPageInfo(data){
        //第${pageInfo.pageNum}页&nbsp;总共${pageInfo.pages}页 总共${pageInfo.total}条记录
        $("#pageInfo").append("第"+ data.myInfo.myInfo.pageNum +"页&nbsp;总共"+ data.myInfo.myInfo.pages +"页 总共"+ data.myInfo.myInfo.total +"条记录")
    }
    function initPageNav(data){

    }
});