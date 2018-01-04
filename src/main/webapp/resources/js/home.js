$(document).ready(function () {
    //const info = Vue.extend({
    //    template:'#info'
    //});
    Date.prototype.toLocaleString = function () {
        //return this.getFullYear() + "年" + (this.getMonth() + 1) + "月" + this.getDate() + "日 " + this.getHours() + "点" + this.getMinutes() + "分" + this.getSeconds() + "秒";
        return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + " " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
    };
    new Vue({
        el: '#app',
        data: {
            basePath: '',
            category: [],
            myInfo: {},
            pageNum: 1,
            total: 0,
            pages: [],
            navigatepageNums: [],
            categoryid: 0,
            categoryList: [],
            infoList2Dimension: [],
            info: {},
            labels2Dimension: []
        },

        methods: {

            activeColor: function (id) {
                console.log(id)
                this.categoryid = id;
                this.getInfoList(1, id);
                this.info = {}

            },
            bindToSearch: function (event) {
                console.log(event)

                $("a[data-toggle='dropdown'] span").eq(0).text($(event.target).text())
                $("a[data-toggle='dropdown']").attr('index', $(event.target).parent().attr('index'));
                console.log("bindToSearch")
            },

            getCategory: function (pageNum) {
                console.log("pageNum");
                console.log(pageNum);


                var thisVue = this;
                $.ajax({
                    url: thisVue.basePath + '/category' + '?randomNum=' + Math.floor(Math.random() * 10),
                    type: 'GET',
                    dataType: 'JSON',
                    data: {pageNumber: pageNum},
                    success: function (data) {
                        console.log("data")
                        console.log(data)
                        console.log("categorylist")
                        console.log(data.myInfo.myInfo.list)
                        thisVue.categoryList = data.myInfo.myInfo.list
                        thisVue.pageNum = data.myInfo.myInfo.pageNum
                        thisVue.total = data.myInfo.myInfo.total
                        thisVue.pages = data.myInfo.myInfo.pages
                        thisVue.navigatepageNums = data.myInfo.myInfo.navigatepageNums

                        console.log("category")
                        console.log(thisVue.categoryList)
                        if (thisVue.categoryList.length <= 0) {
                            alert("请添加分类")
                        } else {
                            thisVue.getInfoList(1, thisVue.categoryList[0].id);
                            thisVue.categoryid = thisVue.categoryList[0].id;
                        }
                    }
                });
            },
            getInfoList: function (pageNum, cateid) {
                var thisVue = this;
                $("#info-detail").hide();
                thisVue.info = {}
                $.ajax({
                    url: thisVue.basePath + '/infolist' + '?randomNum=' + Math.floor(Math.random() * 10),
                    type: 'GET',
                    dataType: 'JSON',
                    data: {pageNumber: pageNum, cateid: cateid},
                    success: function (data) {
                        console.log("data")
                        console.log(data)
                        console.log("infolist")
                        console.log(data.myInfo.myInfo.list)
                        var infoArr = data.myInfo.myInfo.list
                        if (infoArr.length > 0) {
                            $('.pagination').show();
                        } else {
                            $('.pagination').hide();
                        }
                        var infoList2Dimension = [];

                        var temp = []

                        for (var i = 0; i < infoArr.length; i++) {

                            temp.push(infoArr[i]);

                            if (temp.length == 4 || i == infoArr.length - 1) {

                                infoList2Dimension.push(temp);

                                temp = [];

                            }

                        }
                        console.log("infoList2Dimension")
                        console.log(infoList2Dimension)
                        thisVue.infoList2Dimension = infoList2Dimension
                        thisVue.pageNum = data.myInfo.myInfo.pageNum
                        thisVue.total = data.myInfo.myInfo.total
                        thisVue.pages = data.myInfo.myInfo.pages
                        thisVue.navigatepageNums = data.myInfo.myInfo.navigatepageNums
                        console.log("infoList2Dimension")
                        console.log(thisVue.infoList2Dimension)
                    }
                });
            }
            ,
            getInfoDetail: function (infoid) {
                var thisVue = this;
                $("#info-detail").show();
                $('.pagination').hide();
                $.ajax({
                    url: thisVue.basePath + '/info/' + infoid,
                    type: 'GET',
                    dataType: 'JSON',
                    success: function (data) {
                        console.log("data")
                        console.log(data)
                        var unixTimestamp = new Date(data.myInfo.info.infoDate);
                        var commonTime = unixTimestamp.toLocaleString();
                        var info = data.myInfo.info
                        info.infoDate = commonTime
                        thisVue.info = info
                        thisVue.infoList2Dimension = []
                    }
                });
            },
            getLabelList: function(){
                console.log("label-list-start")
                var thisVue = this;
                $.ajax({
                    url: thisVue.basePath + '/labels',
                    type: 'GET',
                    dataType: 'JSON',
                    success: function(data){
                        console.log("labelsdata");
                        console.log(data);
                        var labels1Dimension = data.myInfo.labelList;


                        var labels2Dimension = [];

                        var temp = []

                        for (var i = 0; i < labels1Dimension.length; i++) {

                            temp.push(labels1Dimension[i]);

                            if (temp.length == 2 || i == labels1Dimension.length - 1) {

                                labels2Dimension.push(temp);

                                temp = [];

                            }

                        }
                        thisVue.labels2Dimension = labels2Dimension;
                        console.log("labels2Dimension")
                        console.log(labels2Dimension)
                    }
                });
            }


        },
        created: function () {


            var localObj = window.location;
            console.log(localObj.pathname);
            var contextPath = localObj.pathname.split("/")[1];

            var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
            this.basePath = basePath;
            console.log("basePath")
            console.log(basePath)
            this.getLabelList();
            this.getCategory(1);


        }

    });
});