/**
 * Created by xiaoJun on 2017/6/13.
 */

/**
 * 实现全部股票数据分页
 */

var prePage = 1;
var pageSize = 20;

var kLineIsShow = false;
var previewDayData;
var previewWeekData;
var previewMonthData;
var previewTimeData;
var barArray;
var timeArray;
var upStopArray;
var downStopArray;
var fiveUpDownArray;
var upAndDownChart = echarts.init(document.getElementById("chartContainer"));
var pieChart = echarts.init(document.getElementById("pieContainer"));
var previewChart = echarts.init(document.getElementById("preview"));
$(function () {
    $("#downupButton").hover(function () {
        $(this).addClass("chartButtonLeftBorder");
        $("#downupStopButton").removeClass("chartButtonLeftBorder");
        marketBarChart(upAndDownChart,barArray);
    });
    $("#downupStopButton").hover(function () {
        $(this).addClass("chartButtonLeftBorder");
        $("#downupButton").removeClass("chartButtonLeftBorder");
        marketLineChart(upAndDownChart,timeArray,upStopArray,downStopArray,fiveUpDownArray);
    });
    getMarketUpAndDownData();
    getMarketTimeLineData();
    var $stocks = $("#allStock tbody tr");
    var $hushenStocks = $("#hushen300 tbody tr");
    var $chuangyeStocks = $("#chuangyeban tbody tr");
    var $zhongxiaoStocks = $("#zhongxiaoban tbody tr");
    var pageCount = $stocks.length;
    var countindex =parseInt( pageCount % pageSize > 0 ? (pageCount / pageSize) + 1 : (pageCount / pageSize));
    var visiblePages = 6;
    for(var i = 0; i < pageSize; i++){
        $stocks.eq(i).removeClass("disappear");
    }
    $.jqPaginator('#pagination2', {
        totalPages: countindex,
        visiblePages: visiblePages,
        currentPage: 1,
        first: '<li class="prev"><a href="javascript:;">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="prev"><a href="javascript:;">下一页</a></li>',
        last: '<li class="prev"><a href="javascript:;">尾页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            for(var i = (prePage - 1); i < prePage*pageSize; i++){
                $stocks.eq(i).addClass("disappear");
            }
            for(var i =(num - 1)*pageSize; i < num*pageSize; i++){
                $stocks.eq(i).removeClass("disappear");
            }
            prePage = num;
        }
    });


    $("#allStocksTab").click(function () {
        pageCount = $stocks.length;
        countindex = parseInt( pageCount % pageSize > 0 ? (pageCount / pageSize) + 1 : (pageCount / pageSize));
        $("#allStock").removeClass("disappear")
            .siblings().addClass("disappear");
        $(this).addClass("allStockButtonClick")
            .siblings().removeClass("allStockButtonClick");
        changePage($stocks,countindex,visiblePages);
    });

    $("#hushen300Tab").click(function () {
        pageCount = $hushenStocks.length;
        countindex = parseInt( pageCount % pageSize > 0 ? (pageCount / pageSize) + 1 : (pageCount / pageSize));
        $("#hushen300").removeClass("disappear")
            .siblings().addClass("disappear");
        $(this).addClass("allStockButtonClick")
            .siblings().removeClass("allStockButtonClick");
        changePage($hushenStocks,countindex,visiblePages);
    });

    $("#chuangyebanTab").click(function () {
        pageCount = $chuangyeStocks.length;
        countindex = parseInt( pageCount % pageSize > 0 ? (pageCount / pageSize) + 1 : (pageCount / pageSize));
        $("#chuangyeban").removeClass("disappear")
            .siblings().addClass("disappear");
        $(this).addClass("allStockButtonClick")
            .siblings().removeClass("allStockButtonClick");
        changePage($chuangyeStocks,countindex,visiblePages)
    });

    $("#zhongxiaobanTab").click(function () {
        pageCount = $zhongxiaoStocks.length;
        countindex = parseInt( pageCount % pageSize > 0 ? (pageCount / pageSize) + 1 : (pageCount / pageSize));
        $("#zhongxiaoban").removeClass("disappear")
            .siblings().addClass("disappear");
        $(this).addClass("allStockButtonClick")
            .siblings().removeClass("allStockButtonClick");
        changePage($zhongxiaoStocks,countindex,visiblePages)
    });

    kLinePreview();
})

function changePage($node,countindex,visiblePages) {
    $("#maincont").children("div").children("table").children("tbody").children("tr").addClass("disappear");
    for(var i = 0; i < pageSize; i++){
        $node.eq(i).removeClass("disappear");
    }
    prePage = 1;
    $.jqPaginator('#pagination2', {
        totalPages: countindex,
        visiblePages: visiblePages,
        currentPage: 1,
        first: '<li class="prev"><a href="javascript:;">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:;">上一页</a></li>',
        next: '<li class="prev"><a href="javascript:;">下一页</a></li>',
        last: '<li class="prev"><a href="javascript:;">尾页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            for(var i = (prePage - 1); i < prePage*pageSize; i++){
                $node.eq(i).addClass("disappear");
            }
            for(var i =(num - 1)*pageSize; i < num*pageSize; i++){
                $node.eq(i).removeClass("disappear");
            }
            prePage = num;
        }
    });
}

/**
 * 实现表格K线图预览
 */
function kLinePreview() {
    var $node = $(".kLineHover").eq(0);
    // var $nameLink = $("#maincont div table tbody tr:eq(2) a");
    // $(".kLineHover").hover(function (e) {
    //     if(kLineIsShow){
    //     }else{
    //         e.stopPropagation();
    //         $(".kLineHolder").removeClass("disappear");
    //         showKLinePrevire($(this));
    //         $("#tool").text($(this).text());
    //         $("#previewStock").text($(this).text());
    //         previewChart.showLoading();
    //         getPreviewKLineData();
    //         getPreviewTimeLineData();
    //         previewChart.hideLoading();
    //         $("#previewTime").click(function () {
    //             areaChart(previewChart,previewTimeData[0],previewTimeData[1],previewTimeData[2]);
    //         });
    //         $("#previewDayLine").click(function () {
    //             kLine(previewChart,0,previewDayData);
    //         });
    //         $("#previewWeekLine").click(function () {
    //             kLine(previewChart,1,previewWeekData);
    //         });
    //         $("#previewMonthLine").click(function () {
    //             kLine(previewChart,2,previewMonthData);
    //         });
    //         $node = $(this);
    //         kLineIsShow = true;
    //     }
    // });
    var urlSetting = {
        placement: "right",
        trigger: "hover",
        type: 'html',
        // cache: false,
        url: '#kLinePreview',
    }
    $(".kLineHover").webuiPopover("destroy").webuiPopover(urlSetting);
    $(".kLineHover").hover(function () {
        $("#previewStock").text($(this).text());
        previewChart.showLoading();
        getPreviewKLineData($(this).attr("id"));
        console.log($(this).text());
        getPreviewTimeLineData($(this).attr("id"));
        previewChart.hideLoading();
    })

    $("#previewTime").click(function () {
        areaChart(previewChart,previewTimeData[0],previewTimeData[1],previewTimeData[2])
    });
    $("#previewDayLine").click(function () {
        kLine(previewChart,0,previewDayData);
    });
    $("#previewWeekLine").click(function () {
        kLine(previewChart,1,previewWeekData);
    });
    $("#previewMonthLine").click(function () {
        kLine(previewChart,2,previewMonthData);
    });

}

function showKLinePrevire(jq_ele) {
    var dia = $(".kLineHolder")[0];
    var diaHeight = $(".kLineHolder").height();
    var offsetX = jq_ele.offset().left,
        offsetY = jq_ele.offset().top,
        width = jq_ele.width(),
        height = jq_ele.height();
    dia.style.left = offsetX + width - 10 +  "px";
    dia.style.top = offsetY -height - (diaHeight / 2) + "px";
}

function getPreviewKLineData(id) {
    $.ajax({
        type: "post",
        async: true,  //异步执行
        url: "/StockInfo/"+id+"/getStockDayKLine.do",
        dataType: "json",
        success: function (result) {
            if (result) {
                previewDayData = result;
            }
        },
        error : function () {
            alert("日K数据加载失败咯");
        }
    });

    $.ajax({
        type: "post",
        async: true,  //异步执行
        url: "/StockInfo/"+id+"/getStockWeekKLine.do",
        dataType: "json",
        success: function (result) {
            if (result) {
                previewWeekData = result;
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
        url: "/StockInfo/"+id+"/getStockMonthKLine.do",
        dataType: "json",
        success: function (result) {
            if (result) {
                previewMonthData = result;
            }
        },
        error : function () {
            alert("月K数据加载失败咯");
        }
    });
}

function getPreviewTimeLineData(id) {
    $.ajax({
        type: "post",
        async: true,  //异步执行
        url: "/StockInfo/"+id+"/getStockTimeLine.do",
        dataType: "json",
        success: function (result) {
            if (result) {
                previewTimeData = result;
                areaChart(previewChart,result[0],result[1],result[2]);
            }
        },
        error : function () {
            alert("月K数据加载失败咯");
        }
    });
}

function getMarketTimeLineData() {
    var hushenChart = echarts.init(document.getElementById("hu"));
    var chuangChart = echarts.init(document.getElementById("chuang"));
    var zhongChart = echarts.init(document.getElementById("zhong"));
    $.ajax({
        type: "post",
        async: true,  //异步执行
        url: "/MarketPage/getMarketTimeLineData.do",
        dataType: "json",
        success: function (result) {
            if (result) {
                marketTimeChart(hushenChart,result[0],result[1]);
                marketTimeChart(chuangChart,result[0],result[2]);
                marketTimeChart(zhongChart,result[0],result[3]);
            }
        },
        error : function () {
            alert("大盘分时数据加载失败咯");
        }
    })
}

function getMarketUpAndDownData() {
    $.ajax({
        type: "post",
        async: true,  //异步执行
        url: "/MarketPage/getMarketUpAndDownData.do",
        dataType: "json",
        success: function (result) {
            if (result) {
                console.log(result);
                $("#rise").text(result[5].upNum);
                $("#decline").text(result[5].downNum);
                $("#riseStop").text(result[5].upStopNum);
                $("#declineStop").text(result[5].downStopNum);
                marketBarChart(upAndDownChart,result[0]);
                marketPieChart(pieChart,result[5].score);
                barArray = result[0];
                timeArray = result[1];
                upStopArray = result[2];
                downStopArray  = result[3];
                fiveUpDownArray = result[4];
            }
        },
        error : function () {
            alert("涨跌数据加载失败咯");
        }
    })
}

