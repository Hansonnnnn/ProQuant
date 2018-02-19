/**
 * Created by xiaoJun on 2017/5/16.
 */

// import * as temp from '../charts/meterChart';
var buy_dialogIsShowed = false;
var dayData;
var weekData;
var monthData ;
var timeData;
var mycharts = echarts.init(document.getElementById("main"));
var bpChart = echarts.init(document.getElementById("bpChart"));
var stockId = $("#stockIdHolder").text();

$(function () {
    // alert(stockId +" ll");
    $("#kLine div ul li").click(function () {
        $(this).addClass("blueBackground");
        $(this).siblings().removeClass("blueBackground");
    });


    //点击买入按钮，显示买入提示框
    $("#buyButton").click(function (e) {
        //edit_dialogIsShowed = true;
        var username = $(".loginButtonHolder").children().eq(0).children().eq(0).text();
        var price = $(".priceHolder").eq(0).text();
        if(username === "未登录"){
            slidein(2, "请先登录，再进行买卖！");
        }else{
            if(buy_dialogIsShowed){

            }else{
                e.stopPropagation();
                //调用bl层方法获得总资产和得到最大持股数量
                $.ajax({
                    type: "post",
                    url: "/StockInfo/getAccountInfo.do",
                    data : {
                        "username": username,
                        "price": price
                    },
                    dataType: "json",
                    success: function (result) {
                        if(result){
                            $("#maxNum").text(result.maxNum);
                            $("#availableProperty").text("￥" + result.ap);
                            $("#numInput").text(result.tempNum);
                        }
                        showBuyHolder();
                        $(".buy-page-holder").removeClass("disappear");
                        // showPromptDialog($("#buyButton"));
                        buy_dialogIsShowed = true;
                        // alert("调用account数据结束");
                    },
                    error: function () {
                        // alert("加载account数据失败，stockJS line 57");
                        slidein(2,"加载account数据失败，stockJS line 57");
                    }

                })

                //当点击确认买卖之后，调用逻辑层方法
            }
        }

    });



    // $("#weiboButton").click(function () {
    //     $("#imageModal").modal('show');
    // })

    //当浏览器大小改变时，调整买入提示框的位置
    window.onresize = function () {
        if(!$(".buy-page-holder").hasClass("disappear")){
            showBuyHolder();
        }
    }

    //点击除买入提示框以外的提示框时，关闭提示框
    $(document).click(function (e) {
        // e.stopPropagation();
        e = window.event || e;
        var target = e.srcElement || e.target;
        if(buy_dialogIsShowed && (!$(target).is(".buy-page-holder, .buy-page-holder *"))){
            $(".buy-page-holder").addClass("disappear");
            buy_dialogIsShowed = false;
        }
    });


    //加入自选股
    $("#stockAddButton").click(function () {
        if($(".loginButtonHolder").children().eq(0).children().eq(0).text() === "未登录"){
            slidein(2, "请先登录！");
        }else{
            addToMyStocks($("#stockIdHolder").text().trim());
        }
    });


    historyDataPage();

    meterAndRadarChart();
    mycharts.showLoading();
    getKLineData();
    getTimeLineData();
    // kLine(mycharts,0,dayData);
    mycharts.hideLoading();
    $("#time").click(function () {
        areaChart(mycharts,timeData[0],timeData[1],timeData[2]);
    });
    $("#dayLine").click(function () {
        kLine(mycharts,0,dayData);
    });
    $("#weekLine").click(function () {
        kLine(mycharts,1,weekData);
    });
    $("#monthLine").click(function () {
        kLine(mycharts,2,monthData);
    });



    //为买卖弹出框设置监听
    $("#addPriceButton").click(function () {
        var maxNum = parseInt($("#maxNum").text());
        var currentNum = parseInt($("#numInput").text());
        currentNum += 100;
        if(currentNum <= maxNum){
            $("#numInput").text(currentNum);
        }else{
            currentNum -= 100;
            $("#numInput").text(currentNum);
        }

    });

    $("#deletePriceButton").click(function () {
        var currentNum = parseInt($("#numInput").text());
        currentNum -= 100;
        if(currentNum >= 0){
            $("#numInput").text(currentNum);
        }else{
            currentNum += 100;
            $("#numInput").text(currentNum);
        }
    })


    $("#buyConfirmButton").click(function () {
        var username = $(".loginButtonHolder").children().eq(0).children().eq(0).text();
        var num = $("#numInput").text();
        var code = $("#stockIdHolder").text().trim();
        $.ajax({
            type: "post",
            url: "/StockInfo/buy.do",
            async: false,
            data : {
                "username": username,
                "code": code,
                "num": num
            },
            dataType: "json",
            success: function (result) {
                // alert("成功！");
                if(result){
                    switch(result.singal){
                        case "Success":
                            slidein(0, "成功买入！");
                            $(".buy-page-holder").addClass("disappear");
                            break;
                        case "Insufficient":
                            slidein(1, "账户余额不足，请充值！");
                            $(".buy-page-holder").addClass("disappear");
                            break;
                        default:
                            slidein(1, "网络故障，请稍后重试");
                            $(".buy-page-holder").addClass("disappear");
                    }

                }
            },
            error: function () {
                alert("buy失败，stockJS line 173");
            }
        })

    })


    //BP神经网络
    var thisCode = $("#stockIdHolder").text().trim();
    $.ajax({
        type : "post",
        url : "/Strategy/bp.do",
        data : {
            "code" : thisCode
        },
        dataType : "json",
        success : function (result) {
            if(result){
                // alert("获取bp数据结果成功");
                console.log(result);
                bpLineChart(bpChart,result[0],result[1],result[2]);
                if(result[3].up){
                    $("#typeHolder").text("涨");
                }else{
                    $("#typeHolder").text("跌");
                }
                $("#trustValueHolder").text(result[3].rate+"%")
                $("#valueHolder").text(result[3].change +"%");
            }
        },
        error : function () {
            alert("StockInfoJS line 207");
        }
    })
})


function meterAndRadarChart() {
    var meterCharts = echarts.init(document.getElementById("meter"));
    window.onresize = meterCharts.resize;
    var radarCharts = echarts.init(document.getElementById("radar"));
    window.onresize = radarCharts.resize;

    $.ajax({
        type: "post",
        async: true,//异步执行
        url: "/StockInfo/getStockScore.do",
        dataType: "json",
        success: function (result) {
            // console.log(result);
            meterChart(meterCharts,result.score);
            var radarData = [];
            radarData.push(result.technology);
            radarData.push(result.fund);
            radarData.push(result.info);
            radarData.push(result.industry);
            radarData.push(result.base);
            radarChart(radarCharts,radarData);
            $("#score").text(result.score+"分");
            $("#beat").text(result.beat+"%");
        },
        error: function () {
            alert("失败");
        }
    });
}


/**
 * 获取画K线图的日K，周K，月K数据
 */
function getKLineData() {
    $.ajax({
        type: "post",
        async: true,  //异步执行
        url: "/StockInfo/"+stockId+"/getStockDayKLine.do",
        dataType: "json",
        success: function (result) {
            if (result) {
                dayData = result;
            }
        },
        error : function () {
            alert("日K数据加载失败咯");
        }
    });

    $.ajax({
        type: "post",
        async: true,  //异步执行
        url: "/StockInfo/"+stockId+"/getStockWeekKLine.do",
        dataType: "json",
        success: function (result) {
            if (result) {
                weekData = result;
                // kLine(mycharts,0,result);
            }
        },
        error : function () {
            alert("周K数据加载失败咯");
        }
    });

    $.ajax({
        type: "post",
        async: true,  //异步执行
        url: "/StockInfo/"+stockId+"/getStockMonthKLine.do",
        dataType: "json",
        success: function (result) {
            if (result) {
                monthData = result;
            }
        },
        error : function () {
            alert("月K数据加载失败咯");
        }
    });
}

/**
 * 获取
 */
function getTimeLineData() {
    $.ajax({
        type: "post",
        async: true,  //异步执行
        url: "/StockInfo/"+stockId+"/getStockTimeLine.do",
        dataType: "json",
        success: function (result) {
            if (result) {
                timeData = result;
                console.log(result[0]);
                console.log(result[2]);
                areaChart(mycharts,result[0],result[1],result[2]);
            }
        },
        error : function () {
            alert("月K数据加载失败咯");
        }
    });
}

/**
 * 股票历史数据分页
 */
function historyDataPage() {

    var pageCount = $("#historyData table tbody tr").length;
    console.log($("#historyData table tbody tr"));
    var pageSize = 20;
    var countindex = pageCount % pageSize > 0 ? (pageCount / pageSize) + 1 : (pageCount / pageSize);
    var visiblePages = 6;
    for(var i = 0; i < pageSize; i++){
        $("#historyData table tbody tr").eq(i).removeClass("disappear")
    }
    var prevPage = 1;
    countindex = parseInt(countindex);
    if(countindex < 6){
        visiblePages = countindex;
    }
    $.jqPaginator('#pagination1', {
        totalPages: countindex,
        visiblePages: visiblePages,
        currentPage: 1,
        first: '<li class="prev"><a href="javascript:;">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="prev"><a href="javascript:;">下一页</a></li>',
        last: '<li class="prev"><a href="javascript:;">尾页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            for(var i = (prevPage - 1); i < prevPage*pageSize; i++){
                $("#historyData table tbody tr").eq(i).addClass("disappear");
            }
            for(var i =(num - 1)*pageSize; i < num*pageSize; i++){
                $("#historyData table tbody tr").eq(i).removeClass("disappear");
            }
            prevPage = num;
        }
    });
}


//加入自选股
function addToMyStocks(stockCode) {

    $.ajax({
        type : "post",
        url: "/MarketPage/addMyStock.do",
        data: {
            'key' : stockCode
        },
        dataType : "json",
        success : function (result) {
            if(result.r === "success"){
                slidein(0, "成功加入自选股!");
            }else {
                slidein(1, "已在自选股列表");
            }
        },
        error : function () {
            alert("addToMyStocks"+"失败 line 254");
        }
    });
}


function showBuyHolder() {
    var dia = $(".buy-page-holder")[0];
    var offsetX = $("#buyButton").offset().left,
        offsetY = $("#buyButton").offset().top,
        width = $("#buyButton").width(),
        height = $("#buyButton").height();
    dia.style.left = offsetX + (width / 2) -20 +  "px";
    dia.style.top = offsetY + height+ 20 + "px";
}
