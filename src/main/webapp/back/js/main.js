window.onload = function () {

    new Vue({
        el: '#app',
        data: {
            basePath: '',
            users: [],
            myInfo: {},
            pageNum: 1,
            total: 0,
            pages: [],
            navigatepageNums: []
        },

        methods: {
            addJob: function () {
                $("#addJobModal").modal({
                    //show:true,
                    //backdrop:"static"
                });
            }
            ,
            toPage: function (pageNum) {
                console.log("pageNum");
                console.log(pageNum);

                //this.$http.jsonp("http://192.168.1.102:8080/andnanatest01/index",
                //  {//请求参数
                //	  params: {
                //		  pageNumber:pageNum, randomNum:Math.floor(Math.random() * 10)
                //	  },
                //	  jsonp:'callback',
                //	  jsonpCallback:"handle"
                //  }).then(function(res){
                //	  console.log(res)
                //	  console.log(res.data.myInfo.myInfo.list)
                //	  this.users = res.data.myInfo.myInfo.list
                //	  this.myInfo = res.data.myInfo.myInfo
                //	  this.pageNum = res.data.myInfo.myInfo.pageNum
                //	  this.total = res.data.myInfo.myInfo.total
                //	  this.pages = res.data.myInfo.myInfo.pages
                //	  console.log("useruser")
                //	  console.log(this.users)
                //	  // this.works = JSON.parse(res.bodyText)
                //  },function(){
                //	  //console.log(1)
                //
                //  });
                //var thisVue = this;
                //$.ajax({
                //  url: 'http://192.168.1.102:8080/andnanatest01/index',
                //  type: 'GET',
                //  dataType: 'JSONP',
                //  data:{pageNumber: pageNum,randomNum: Math.floor(Math.random() * 10)},
                //  jsonp:"callback",
                //  jsonpCallback:"handle",
                //
                //  success: function (res) {
                //	  console.log("dfeer")
                //	  console.log(res)
                //	  console.log("ggdg")
                //	  console.log(res.myInfo.myInfo.list)
                //	  thisVue.users = res.myInfo.myInfo.list
                //	  thisVue.pageNum = res.myInfo.myInfo.pageNum
                //	  thisVue.total = res.myInfo.myInfo.total
                //	  thisVue.pages = res.myInfo.myInfo.pages
                //	  console.log("useruser")
                //	  console.log(thisVue.users)
                //
                //  } ,
                //  error:function(XHR, textStatus, errorThrown){
                //	  console.log(errorThrown)
                //	  console.log(textStatus)
                //	  return false;
                //  }
                //})
                var thisVue = this;
                $.ajax({
                    url: thisVue.basePath + '/index',
                    type: 'GET',
                    dataType: 'JSON',
                    data: {pageNumber: pageNum, randomNum: Math.floor(Math.random() * 10)},
                    success: function (data) {
                        console.log("dfeer")
                        console.log(data)
                        console.log("ggdg")
                        console.log(data.myInfo.myInfo.list)
                        thisVue.users = data.myInfo.myInfo.list
                        thisVue.pageNum = data.myInfo.myInfo.pageNum
                        thisVue.total = data.myInfo.myInfo.total
                        thisVue.pages = data.myInfo.myInfo.pages
                        thisVue.navigatepageNums = data.myInfo.myInfo.navigatepageNums
                        console.log("useruser")
                        console.log(thisVue.users)
                    }
                });
            },
            deleteUser: function (userid) {
                console.log("userid")
                console.log(userid)
                var thisVue = this;
                if (confirm("确认要删除吗？")) {
                    console.log("确认")
                    //this.$http.jsonp(thisVue.basePath + "/user/" + userid,
                    //  {//请求参数
                    //	  params: {
                    //
                    //	  },
                    //	  type:"DELETE",
                    //	  jsonp:'callback',
                    //	  jsonpCallback:"handle"
                    //  }).then(function(res){
                    //	  //this.toPage(1)
                    //
                    //  },function(){
                    //	  //console.log(1)
                    //
                    //  });
                    //
                    //console.log("确认");
                    console.log("thisVue.basePath")
                    console.log(thisVue.basePath)
                    var thisVue = this;
                    $.ajax({
                        url: thisVue.basePath + "/user/" + userid,
                        data: {},
                        type: "DELETE",
                        success: function (data) {

                            thisVue.toPage(1);
                        }
                    });
                }
            }

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


            //console.log(this.$http.jsonp)
            //this.$http.jsonp("http://192.168.1.102:8080/andnanatest01/index",
            //  {//请求参数
            //	  params: {
            //			pageNumber:1, randomNum: Math.floor(Math.random() * 10)
            //	  },
            //	  jsonp:'callback',
            //	  jsonpCallback:"handle"
            //  }).then(function(res){
            //	  console.log(res)
            //	  console.log(res.data.myInfo.myInfo.list)
            //	  this.users = res.data.myInfo.myInfo.list
            //	  this.myInfo = res.data.myInfo.myInfo
            //	  this.pageNum = res.data.myInfo.myInfo.pageNum
            //	  this.pages = res.data.myInfo.myInfo.pages
            //	  console.log(this.users)
            //	  // this.works = JSON.parse(res.bodyText)
            //  },function(){
            //	  //console.log(1)
            //
            //  });
            //var contentType ="application/x-www-form-urlencoded; charset=utf-8";
            //
            //if(window.XDomainRequest) //for IE8,IE9
            //  contentType = "text/plain";
            //
            //$.ajax({
            //  url:"http://192.168.1.102:8080/andnanatest01/index",
            //  data:"pageNumber=1&randomNum=2",
            //  type:"GET",
            //  dataType:"json",
            //  contentType:contentType,
            //  success:function(data)
            //  {
            //	  alert("Data from Server"+JSON.stringify(data));
            //  },
            //  error:function(jqXHR,textStatus,errorThrown)
            //  {
            //	  alert("You can not send Cross Domain AJAX requests: "+errorThrown);
            //  }
            //});
            //var thisVue = this;
            //$.ajax({
            //     url: 'http://192.168.1.102:8080/andnanatest01/index',
            //     type: 'GET',
            //     dataType: 'JSONP',
            //     data:{pageNumber: 1,randomNum: Math.floor(Math.random() * 10)},
            //     jsonp:"callback",
            //     jsonpCallback:"handle",
            //
            //     success: function (res) {
            //	   console.log("dfeer")
            //	   console.log(res)
            //	   console.log("ggdg")
            //	   console.log(res.myInfo.myInfo.list)
            //	   thisVue.users = res.myInfo.myInfo.list
            //	   thisVue.pageNum = res.myInfo.myInfo.pageNum
            //	   thisVue.total = res.myInfo.myInfo.total
            //	   thisVue.pages = res.myInfo.myInfo.pages
            //	   console.log("useruser")
            //	   console.log(thisVue.users)
            //
            //
            //
            //   } ,
            //          error:function(XHR, textStatus, errorThrown){
            //          	console.log(errorThrown)
            //          	console.log(textStatus)
            //              return false;
            //          }
            //   })
        }

    });
}