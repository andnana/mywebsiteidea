/**
 * Created by Administrator on 2017/12/17 0017.
 */
$(function () {
    console.log("dfaerewr")
    var localObj = window.location;
    console.log(localObj.pathname);
    var contextPath = localObj.pathname.split("/")[1];

    var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;


    toPage(1);
    function toPage(pageNumber) {
        var randomNum = Math.floor(Math.random() * 10);
        $.ajax({
                url: basePath + "/userlist",
                data: {pageNumber: pageNumber, randomNum: randomNum},
                type: "get",
                success: function (data) {
                    console.log(data);
                    initUsers(data);
                    initPageInfo(data);
                    initPageNav(data);

                }

            }
        );
    }

    function initUsers(data) {
        $("tbody").empty();
        var users = data.myInfo.myInfo.list;

        $.each(users, function (index, user) {
            console.log(user.username);
            var checkBoxInput = $("<input type='checkbox' />");
            var checkBoxTd = $("<td></td>");
            checkBoxTd.append(checkBoxInput);
            var useridTd = $("<td></td>").append(user.id);
            var usernameTd = $("<td></td>").append(user.username);
            var gender = user.gender == "M" ? "男" : "女";
            var userGenderTd = $("<td></td>").append(gender);
            var userEmailTd = $("<td></td>").append(user.email);
            var userDepartmentTd = $("<td></td>").append(user.department.deptName);
            var tr = $("<tr></tr>").append(checkBoxTd).append(useridTd).append(usernameTd)
                .append(userGenderTd).append(userEmailTd).append(userDepartmentTd).appendTo("tbody");
            /**
             <td>
             <button class="btn btn-success btn-sm"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</button>
             <button class="btn btn-success btn-sm"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除</button>


             </td>

             */
            var userBtnTd = $("<td></td>");
            var userEditBtn = $("<button></button>").addClass("btn btn-success btn-sm").append($("<span></span>").addClass("glyphicon glyphicon-pencil").attr("aria-hidden", "true")).append("编辑");
            userEditBtn.attr("userid", user.id);
            var userDeleteBtn = $("<button></button>").addClass("btn btn-danger btn-sm").append($("<span></span>").addClass("glyphicon glyphicon-trash").attr("aria-hidden", "true")).append("删除");
            userDeleteBtn.attr("userid", user.id);
            userBtnTd.append(userEditBtn).append(" ").append(userDeleteBtn);
            tr.append(userBtnTd);
        });
    }

    $("tbody").on("click", ".btn-success", function () {
        console.log("update button click");
        getDepts("#updateUserModal select")
        getUser($(this).attr("userid"));
        //console.log($(this).attr("userid"));
        $("#updateUserModal").modal({
            //show:true,
            //backdrop:"static"
        });
    });
    $("tbody").on("click", ".btn-danger", function () {
        console.log("delete button click");

        var userid = $(this).attr("userid")
        console.log($(this).attr("userid"));
        var username = $(this).parent().parent().find("td:eq(2)").text();
        if (confirm("确认删除名字为" + username + "的用户吗？")) {
            console.log("确认");
            $.ajax({
                url: basePath + "/user/" + userid,
                data: {},
                type: "DELETE",
                success: function (data) {
                    var pageNumber = $("#updateUserModal input[name='pageNumber']").val();
                    toPage(pageNumber);
                }
            });
        } else {
            console.log("取消");
        }
    });
    $("#check_all").click(function () {
        console.log($(this).prop("checked"));
        $("tbody input[type='checkbox']").prop("checked", $(this).prop("checked"));
    });
    $("tbody").on("click", "input[type='checkbox']", function () {
        if ($("tbody input[type='checkbox']:checked").length == $("tbody input[type='checkbox']").length) {
            $("#check_all").prop("checked", true);
        } else {
            $("#check_all").prop("checked", false);
        }
    });
    $("#deleteAllUserBtn").click(function () {
        var usernames = "";
        var ids = "";
        $.each($("tbody input[type='checkbox']:checked"), function (index) {
            console.log(index);
            console.log($(this).parent().parent().find("td:eq(2)").text());

            if (index != ($("tbody input[type='checkbox']:checked").length - 1)) {
                usernames += $(this).parent().parent().find("td:eq(2)").text() + ",";
                ids += $(this).parent().parent().find("td:eq(1)").text() + "-";
            } else {
                usernames += $(this).parent().parent().find("td:eq(2)").text();
                ids += $(this).parent().parent().find("td:eq(1)").text();
            }

        });
        if (($("tbody input[type='checkbox']:checked").length >= 1) && confirm("确认删除名字为：" + usernames + "的用户吗？")) {
            console.log("确认");
            $.ajax({
                url: basePath + "/user/" + ids,
                data: {},
                type: "DELETE",
                success: function (data) {
                    var pageNumber = $("#updateUserModal input[name='pageNumber']").val();
                    toPage(pageNumber);
                }
            });
        } else {
            console.log("取消");
        }
    });
    function getUser(id) {
        var randomNum = Math.floor(Math.random() * 10);
        $.ajax({
            url: basePath + "/user/" + id,
            data: {"randomNum": randomNum},
            type: "get",
            success: function (data) {
                console.log(data);
                $("p.form-control-static").text(data.myInfo.user.username);

                $("input[name='password']").val(data.myInfo.user.password);

                $("input[name='email']").val(data.myInfo.user.email);

                $("#updateUserModal input[name='gender']").val([data.myInfo.user.gender]);

                $("#updateUserModal select").val([data.myInfo.user.department.id]);

                $("#updateUserModal input[name='userid']").val(data.myInfo.user.id);

            }
        });
    }

    function initPageInfo(data) {


        $("#pageInfo").empty();
        //第${pageInfo.pageNum}页&nbsp;总共${pageInfo.pages}页 总共${pageInfo.total}条记录
        $("#pageInfo").append("第" + data.myInfo.myInfo.pageNum + "页&nbsp;总共" + data.myInfo.myInfo.pages + "页 总共" + data.myInfo.myInfo.total + "条记录")
        $("#updateUserModal input[name='pageNumber']").val(data.myInfo.myInfo.pageNum);

    }

    function initPageNav(data) {
        $("#pageNav").empty();
        var ul = $("<ul></ul>").addClass("pagination");
        var firstPageLi = $("<li></li>").append($("<a></a>").append("第一页"));
        var lastPageLi = $("<li></li>").append($("<a></a>").append("最后一页"));
        var previousPageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
        if (data.myInfo.myInfo.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            previousPageLi.addClass("disabled");
        } else {
            firstPageLi.click(function () {
                toPage(1);
            });
            previousPageLi.click(function () {
                //if(data.myInfo.myInfo.pageNum >= 1){
                toPage(data.myInfo.myInfo.pageNum - 1);
                //}
            });
        }
        if (data.myInfo.myInfo.hasNextPage == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            nextPageLi.click(function () {
                //if(data.myInfo.myInfo.pageNum != data.myInfo.myInfo.pages){
                toPage(data.myInfo.myInfo.pageNum + 1);
                //}
            });
            lastPageLi.click(function () {
                toPage(data.myInfo.myInfo.pages);
            });
        }


        ul.append(firstPageLi).append(previousPageLi);
        $.each(data.myInfo.myInfo.navigatepageNums, function (index, num) {
            var numLi = $("<li></li>").append($("<a></a>").append(num));
            if (data.myInfo.myInfo.pageNum == num) {
                numLi.addClass("active");
            }
            numLi.click(function () {
                toPage(num);
            });
            ul.append(numLi);
        });
        ul.append(nextPageLi).append(lastPageLi);
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#pageNav");
    }

    $("#addUserModelBtn").click(function () {
        console.log("dfdddf")

        //resetForm("#addUserModal form")
        $("#addUserModal form")[0].reset();
        $("#addUserModal form input").parent().removeClass("has-error has-success");
        $("#addUserModal form").find(".help-block").text("");
        getDepts("#addUserModal select");

        //$("#addUserModal").modal("show");
        $("#addUserModal").modal({
            //show:true,
            //backdrop:"static"
        });
    });
    function getDepts(ele) {
        $(ele).empty();
        $.ajax({
            url: basePath + "/depts",
            data: {},
            type: "get",
            success: function (data) {
                console.log(data);


                $.each(data.myInfo.depts, function (index, dept) {
                    var option = $("<option></option>").append(dept.deptName).attr("value", dept.id);
                    option.appendTo(ele)
                });
            }

        });
    }

    $("input[name='username']").change(function () {
        console.log("changetest");

        var username = $("input[name='username']").val();
        console.log(username);
        $.ajax({
            url: basePath + "/checkUsername",
            data: {username: username},
            type: "get",
            success: function (data) {
                console.log(data);
                if (200 == data.code) {
                    console.log(data.code);
                    $("#saveUserBtn").attr("duplicate", "n");
                    updateInputStatus("input[name='username']", "success", data.myInfo.otherMsg);
                } else {
                    $("#saveUserBtn").attr("duplicate", "y");
                    updateInputStatus("input[name='username']", "error", data.myInfo.otherMsg);
                }


            }

        });
    });
    //function resetForm(ele){
    //    $("#addUserModal form")[0].reset();
    //    $(ele).parent().removeClass("has-error has-success");
    //    $(ele).find(".help-block").text("");
    //
    //}
    function validateFormData() {
        var username = $("#addUserModal input[name='username']").val();
        var usernameReg = /(^[a-zA-Z0-9_-]{3,16})|(^[\u2E80-\u9FFF]{2,5}$)/;
        console.log(usernameReg.test(username));
        if (!usernameReg.test(username)) {
            //alert("用户名在3-16位英文字符，或2-5位中文。（a-zA-Z0-9_-)")
            //$("input[name='username']").parent().addClass("has-error");
            //$("input[name='username']").next("span").text("用户名在3-16位英文字符，或2-5位中文。（a-zA-Z0-9_-)");
            updateInputStatus("#addUserModal input[name='username']", "error", "用户名在3-16位英文字符，或2-5位中文。（a-zA-Z0-9_-)");
            return false;
        } else {
            //$("input[name='username']").parent().addClass("has-success");
            //$("input[name='username']").next("span").text("");
            updateInputStatus("#addUserModal input[name='username']", "success", "");
        }

        var password = $("#addUserModal input[name='password']").val();
        var passwordReg = /^[a-z0-9_-]{6,18}$/;
        if (!passwordReg.test(password)) {
            //alert("密码格式为(a-z0-9_-)长度为6-18位");
            //$("input[name='password']").parent().addClass("has-error");
            //$("input[name='password']").next("span").text("密码格式为(a-z0-9_-)长度为6-18位");
            updateInputStatus("#addUserModal input[name='password']", "error", "密码格式为(a-z0-9_-)长度为6-18位");
            return false;
        } else {
            //$("input[name='password']").parent().addClass("has-success");
            //$("input[name='password']").next("span").text("");
            updateInputStatus("#addUserModal input[name='password']", "success", "");
        }


        var email = $("#addUserModal input[name='email']").val();
        var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if (!emailReg.test(email)) {
            //alert("电子邮箱格式为(a-z0-9_.-)");
            //$("input[name='email']").parent().addClass("has-error");
            //$("input[name='email']").next("span").text("电子邮箱格式为(a-z0-9_.-)");
            updateInputStatus("#addUserModal input[name='email']", "error", "电子邮箱格式为(a-z0-9_.-)");
            return false;
        } else {
            //$("input[name='email']").parent().addClass("has-success");
            //$("input[name='email']").next("span").text("");
            updateInputStatus("#addUserModal input[name='email']", "success", "");
        }


        return true;
    }

    function updateInputStatus(ele, status, text) {
        $(ele).parent().removeClass("has-success has-error");
        if ("success" == status) {
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(text);
        } else if ("error" == status) {
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(text);
        }
    }

    $("#updateUserBtn").click(function () {
        var password = $("#updateUserModal input[name='password']").val();
        var passwordReg = /^[a-z0-9_-]{6,18}$/;
        if (!passwordReg.test(password)) {
            //alert("密码格式为(a-z0-9_-)长度为6-18位");
            //$("input[name='password']").parent().addClass("has-error");
            //$("input[name='password']").next("span").text("密码格式为(a-z0-9_-)长度为6-18位");
            updateInputStatus("#updateUserModal input[name='password']", "error", "密码格式为(a-z0-9_-)长度为6-18位");
            return false;
        } else {
            //$("input[name='password']").parent().addClass("has-success");
            //$("input[name='password']").next("span").text("");
            updateInputStatus("#updateUserModal input[name='password']", "success", "");
        }


        var email = $("#updateUserModal input[name='email']").val();
        var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if (!emailReg.test(email)) {
            //alert("电子邮箱格式为(a-z0-9_.-)");
            //$("input[name='email']").parent().addClass("has-error");
            //$("input[name='email']").next("span").text("电子邮箱格式为(a-z0-9_.-)");
            updateInputStatus("#updateUserModal input[name='email']", "error", "电子邮箱格式为(a-z0-9_.-)");
            return false;
        } else {
            //$("input[name='email']").parent().addClass("has-success");
            //$("input[name='email']").next("span").text("");
            updateInputStatus("#updateUserModal input[name='email']", "success", "");
        }
        var userid = $("#updateUserModal input[name='userid']").val();
        /*    $.ajax({
         url: basePath + "/user/" + userid,
         type: "post",
         data: $("#updateUserModal form").serialize() + "&_method=PUT",
         success: function(data){
         console.log(data.msg);
         }
         });*/
        $.ajax({
            url: basePath + "/user/" + userid,
            type: "PUT",
            data: $("#updateUserModal form").serialize(),
            success: function (data) {
                console.log(data.msg);
                $("#updateUserModal").modal("hide");
                var pageNumber = $("#updateUserModal input[name='pageNumber']").val();
                toPage(pageNumber);

            }
        });
    });
    $("#saveUserBtn").click(function () {

        if (!validateFormData()) {
            return false;
        }
        if ($("#saveUserBtn").attr("duplicate") == "y") {
            updateInputStatus("input[name='username']", "error", "用户名已存在");
            return false;
        }
        $.ajax({
            url: basePath + "/user",
            data: $("#addUserModal form").serialize(),
            type: "post",
            success: function (data) {
                if (data.code == 200) {
                    console.log("#$$$$$");
                    console.log($("#updateUserModal input[name='pageNumber']").val());
                    toPage($("#updateUserModal input[name='pageNumber']").val());

                    $("#addUserModal").modal("hide");

                } else {
                    if (data.myInfo.errors.username) {
                        updateInputStatus("input[name='username']", "error", data.myInfo.errors.username);
                    } else {
                        updateInputStatus("input[name='username']", "success", "");
                    }
                    if (data.myInfo.errors.password) {
                        updateInputStatus("input[name='password']", "error", data.myInfo.errors.password);
                    } else {
                        updateInputStatus("input[name='password']", "success", "");
                    }
                    if (data.myInfo.errors.email) {
                        updateInputStatus("input[name='email']", "error", data.myInfo.errors.email);
                    } else {
                        updateInputStatus("input[name='email']", "success", "");
                    }
                    console.log(data)
                }


            }

        });
    });
});