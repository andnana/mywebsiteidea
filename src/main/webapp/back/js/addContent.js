$(document).ready(function () {

    new Vue({
        el: '#app',
        data: {
            basePath: '',
            category: [],
            myInfo: {},
            pageNum: 1,
            total: 0,
            pages: [],
            categoryName: '',
            helpwords: '',
            navigatepageNums: [],
            title: '',
            tips: '',
            labelList: []
        },

        methods: {
            change: function () {
                console.log("change")
                var thisVue = this;
                $.ajax({
                    url: thisVue.basePath + "/checkInfoTitle",
                    data: {title: thisVue.title},
                    type: "get",
                    success: function (data) {
                        console.log(data);
                        $(".tips").text('');
                        if (200 == data.code) {

                            console.log(data.code);
                            console.log(data.myInfo.otherMsg);
                            thisVue.tips = data.myInfo.otherMsg;
                            console.log("tips")
                            //$(".help-block").parent().addClass("has-success");
                        } else {
                            thisVue.tips = data.myInfo.otherMsg;
                            //$(".help-block").parent().addClass("has-error");
                        }


                    }

                });
            }
            ,

            /*            saveInfo:function(){
             console.log("saveInfo");
             var thisVue = this;
             $.ajax({
             url: thisVue.basePath + "/info",
             data: $("form").serialize(),
             type:"post",
             success:function(data){
             if(data.code == 200){
             console.log("#$$$$$");
             console.log(data.code);
             console.log(data)
             //thisVue.pageNum += 1;
             //thisVue.toPage(thisVue.pageNum);

             }else{

             console.log(data)
             }


             }

             });
             }*/
            saveInfo: function () {
                console.log("saveinfosaveinfo")
                //var form = new FormData($("#form")[0]);
                $('#form').submit();
                console.log("12342134")
                var thisVue = this;
                console.log(thisVue.basePath)

                /*      $.ajax({
                 url : thisVue.basePath + "/info" + '?randomNum=' + Math.floor(Math.random() * 10) ,
                 data: form,
                 type : 'POST',
                 dataType:'JSON',
                 processData:false,
                 contentType:false,
                 jsonp: "jsoncallback",
                 success : function(data){
                 if(data.code == 200){
                 console.log("#$$$$$");
                 console.log(data.code);
                 console.log(data)
                 //thisVue.pageNum += 1;
                 //thisVue.toPage(thisVue.pageNum);

                 }else{

                 console.log(data)
                 }
                 },
                 error : function(data){
                 console.log(data)
                 }
                 });*/


            }
            ,
            toPage: function (pageNum) {
                console.log("pageNum");
                console.log(pageNum);


                var thisVue = this;
                $.ajax({
                    url: thisVue.basePath + '/category' + '?randomNum=' + Math.floor(Math.random() * 10),
                    type: 'GET',
                    dataType: 'JSON',
                    data: {pageNumber: pageNum},
                    success: function (data) {
                        console.log("dfeer")
                        console.log(data)
                        console.log("ggdg")
                        console.log(data.myInfo.myInfo.list)
                        thisVue.category = data.myInfo.myInfo.list

                        console.log("categorycategory")
                        console.log(thisVue.category)
                    }
                });
            }
            ,
            getLabelList: function(pageNum){
                var thisVue = this;
                $.ajax({
                    url: thisVue.basePath + '/label',
                    type: 'GET',
                    dataType: 'JSON',
                    data: {pageNumber: pageNum, randomNum: Math.floor(Math.random() * 10)},
                    success: function (data) {
                        console.log("dfeer")
                        console.log(data)
                        console.log("ggdg")
                        console.log(data.myInfo.myInfo.list)
                        thisVue.labelList = data.myInfo.myInfo.list

                        console.log("labelList")
                        console.log(thisVue.labelList)
                    }
                });
            }
            //,
            //deleteCategory:function(categoryid){
            //    console.log("categoryid")
            //    console.log(categoryid)
            //    var thisVue = this;
            //    if(confirm("确认要删除吗？")){
            //        console.log("确认")
            //
            //        console.log("thisVue.basePath")
            //        console.log(thisVue.basePath)
            //        var thisVue = this;
            //        $.ajax({
            //            url: thisVue.basePath + "/category/" + categoryid,
            //            data: {randomNum: Math.floor(Math.random() * 10)},
            //            type:"DELETE",
            //            success:function(data) {
            //
            //                thisVue.toPage(thisVue.pageNum);
            //            }
            //        });
            //    }
            //}

        },
        created: function () {
            //this.$http.get("http://192.168.1.102:8080/andnanatest01/index", {
            //  // employeeopenid: 'oDsIf0XaW9-XrkTC_KP3AH8kaF1I',
            //  // bossopenid: 'oDsIf0YK8VAmcS0UqYWBcWhW0asE'
            //}).then(function (response) {
            //  console.log(response.data)
            //  this.users = response.data
            //})


            var localObj = window.location;
            console.log(localObj.pathname);
            var contextPath = localObj.pathname.split("/")[1];

            var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
            this.basePath = basePath;
            console.log("basePath")
            console.log(basePath)

            this.toPage(1);
            this.getLabelList(1);


        }

    });
});