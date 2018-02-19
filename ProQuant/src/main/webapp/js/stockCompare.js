/**
 * Created by xiaoJun on 2017/6/1.
 */

var stockNameArray = [];  //保存对比股票名字的数组
var timeContainer = echarts.init(document.getElementById("compareTimeContainer"));
var rangeContainer = echarts.init(document.getElementById("compareRangeContainer"));
var priceContainer = echarts.init(document.getElementById("comparePriceContainer"));
var xTimeData = [];
var yTimeData = [];
var xRangeData = [];
var yRangeData = []
var xPriceData = [];
var yPriceData = [];
var isShow = false;

$(function () {
    //点击按钮切换热门股票和最近访问
    $("#hotCompareStockButton").click(function () {
        $(this).addClass("hotStockBottomBorder");
        $(this).siblings().removeClass("hotStockBottomBorder");
        $("#hotCompareStock").removeClass("displayNone");
        $("#hotBrowseStock").addClass("displayNone");
    });

    $("#browseCompareStockButton").click(function () {
        $(this).addClass("hotStockBottomBorder");
        $(this).siblings().removeClass("hotStockBottomBorder");
        $("#hotCompareStock").addClass("displayNone");
        $("#hotBrowseStock").removeClass("displayNone");
    });

    $("#compareHolder tr").click(function () {
        var name = $(this).attr("id");
        if(isRepeat(name)){
            setTimeout(slidein(2,"股票已在对比列表中"),1800);
        }else{
            if(stockNameArray.length < 5){
                $("#holder").addClass("displayNone");
                $("#searchTextField").val("");
                stockNameArray.push(name);
                addNodeToStockContainer(name);
                isShow = false;
                console.log(stockNameArray);
            }else{
                setTimeout(slidein(2,"最多对比五只股票"),1800);
            }
        }
    });

    window.onresize = function () {
        if(!$("#holder").hasClass("displayNone")){
            showPromptDialog($("#searchTextField"));
        }
    }

    //点击除买入提示框以外的提示框时，关闭提示框
    $(document).click(function (e) {
        // e.stopPropagation();
        e = window.event || e;
        var target = e.srcElement || e.target;
        if(isShow && (!$(target).is("#holder, #holder *"))){
            $("#holder").addClass("displayNone");
            isShow= false;
        }
    });

    $("#searchTextField").change(function () {
        $("#holder").addClass("displayNone");
        for(var i = 0; i < 20; i++){
            $("#compareHolder tr").eq(i).text("");
            $("#compareHolder tr").eq(i).addClass("displayNone");
        }
        $.ajax({
            type: "post",
            async: true,//异步执行
            url: "/StockCompare/compareSearch.do",
            dataType: "json",
            data : {"nameOrCode" : $(this).val()},
            success : function (result) {
                $("#searchTextField").val()
                for(var i = 0; i < result.length; i++){
                    $("#compareHolder tr").eq(i).text(result[i].name+"("+result[i].code+")");
                    $("#compareHolder tr").eq(i).attr("id",result[i].name);
                    $("#compareHolder tr").eq(i).removeClass("displayNone");
                }
                showPromptDialog($("#searchTextField"));
                $("#holder").removeClass("displayNone");
                isShow = true;
            },
            error: function () {
            }
        })
    });

    //点击添加按钮，将对应股票添加到对比列表中
    $(".addCompareStockButton").click(function () {
        var node = $(this).parent();
        var stockName = node.siblings().first().children().first().text();
        if(isRepeat(stockName)){
            setTimeout(slidein(2,"股票已在对比列表中"),1800);
        }else{
            if(stockNameArray.length < 5){
                stockNameArray.push(stockName);
                addNodeToStockContainer(stockName);
            }else{
                setTimeout(slidein(2,"最多对比五只股票"),1800);
            }
        }
    });

    //点击删除按钮，将对应股票从对比列表中删除
    $(".stockCardDelete").click(function () {
        var stockName = $(this).siblings().first().text();
        stockNameArray.splice(indexOf(stockName),1);
        $(this).parent().remove();
    });

    //点击清空按钮，清空对比列表
    $("#deleteAllButton").click(function () {
        stockNameArray.splice(0,stockNameArray.length);
        $("#stockContainer").empty();
        slidein(0,"已清空对比股票");
    });

    //点击开始对比按钮，显示对比图表
    $("#compareButton").click(function () {
        fillInTable(stockNameArray);
        fillInChart(stockNameArray);
        $("#timeChartButton").addClass("hotStockBottomBorder");
        $("#rangeChartButton").removeClass("hotStockBottomBorder");
        $("#priceChartButton").removeClass("hotStockBottomBorder");
    });

    $("#timeChartButton").click(function () {
        $(this).addClass("hotStockBottomBorder");
        $("#rangeChartButton").removeClass("hotStockBottomBorder");
        $("#priceChartButton").removeClass("hotStockBottomBorder");
        lineChart(timeContainer,stockNameArray,xTimeData,yTimeData);
    });

    $("#rangeChartButton").click(function () {
        $(this).addClass("hotStockBottomBorder");
        $("#timeChartButton").removeClass("hotStockBottomBorder");
        $("#priceChartButton").removeClass("hotStockBottomBorder");
        lineChart(timeContainer, stockNameArray, xRangeData, yRangeData);
    });

    $("#priceChartButton").click(function () {
        $(this).addClass("hotStockBottomBorder");
        $("#timeChartButton").removeClass("hotStockBottomBorder");
        $("#rangeChartButton").removeClass("hotStockBottomBorder");

        lineChart(timeContainer,stockNameArray,xPriceData,yPriceData);
    });
})

/**
 * 创建节点并添加到对比股票容器中
 * @param stockName 股票名称
 */
function addNodeToStockContainer(stockName) {
    var $li = $("<li class='stockCard'></li>");
    var $span = $("<span class='stockCardName'>"+stockName+"</span>");
    var $button = $("<button class='stockCardDelete'><i class='fa fa-times-circle' aria-hidden='true'></i></button>");
    $button.click(function () {
        var stockName = $(this).siblings().first().text();
        stockNameArray.splice(indexOf(stockName),1);
        $(this).parent().remove();
    });
    $li.append($span);
    $li.append($button);
    $("#stockContainer").append($li);
}

/**
 * 判断添加对比时，股票是否已经在对比列表中
 * @param stockName
 */
function isRepeat(stockName) {
    for(var i = 0; i < stockNameArray.length; i++){
        if(stockNameArray[i] == stockName){
            return true;
        }
    }
    return false;
}

/**
 * 获取对应元素在数组中的下标
 * @param val 搜索的元素
 */
function indexOf(val) {
    for(var i = 0; i < stockNameArray.length; i++){
        if(stockNameArray[i] == val){
            return i;
    }
    }
    return -1;
}

/**
 * 将对比的股票信息填写到股票表格中
 * @param stockNameArray 对比的股票名称
 */
function fillInTable(stockNameArray) {
    for(var i = 0; i < stockNameArray.length; i++){
        var $a = $("<a>"+stockNameArray[i]+"</a>");
        $("#compareTableHead th:eq("+(i+1)+")").text("");
        $("#compareTableHead th:eq("+(i+1)+")").append($a);
}

    for(var i = stockNameArray.length; i < 6; i++){
        $("#compareTableHead th:eq("+(i+1)+")").text("--");
    }

    // const stockName = {
    //     "array" : stockNameArray
    // };
    // console.log(stockNameArray.join());
    $.ajax({
        type: "post",
        async: true,//异步执行
        url: "/StockCompare/getCompareInfo.do",
        dataType: "json",
        data : {"name" : stockNameArray.join(",")},
        success: function (result) {
            // alert("成功2334");
            console.log(result);
            for(var i = 0; i < stockNameArray.length; i++){
                $("#weekChangeRatio td:eq("+(i+1)+")").text(result[i].weekChangeRatio+"%");
                setFontColor($("#weekChangeRatio td:eq("+(i+1)+")"),result[i].weekChangeRatio);
                $("#monthChangeRatio td:eq("+(i+1)+")").text(result[i].monthChangeRatio+"%");
                setFontColor($("#monthChangeRatio td:eq("+(i+1)+")"),result[i].monthChangeRatio)
                $("#seasonChangeRatio td:eq("+(i+1)+")").text(result[i].seasonChangeRatio+"%");
                setFontColor($("#seasonChangeRatio td:eq("+(i+1)+")"),result[i].seasonChangeRatio)
                $("#halfayearChangeRatio td:eq("+(i+1)+")").text(result[i].halfayearChangeRatio+"%");
                setFontColor($("#halfayearChangeRatio td:eq("+(i+1)+")"),result[i].halfayearChangeRatio);
                $("#yearChangeRatio td:eq("+(i+1)+")").text(result[i].yearChangeRatio+"%");
                setFontColor($("#yearChangeRatio td:eq("+(i+1)+")"),result[i].yearChangeRatio);
                $("#pe td:eq("+(i+1)+")").text(result[i].pe);
                $("#pb td:eq("+(i+1)+")").text(result[i].pb);
                $("#pcf td:eq("+(i+1)+")").text(result[i].pcf);
                $("#ps td:eq("+(i+1)+")").text(result[i].ps);
                $("#perShareEarnings td:eq("+(i+1)+")").text(result[i].perShareEarnings);
                $("#netProfitMarginOnSales td:eq("+(i+1)+")").text(result[i].netProfitMarginOnSales+"%");
                $("#roa td:eq("+(i+1)+")").text(result[i].roa+"%");
                $("#debtAssetRatio td:eq("+(i+1)+")").text(result[i].debtAssetRatio+"%");
                $("#currentRatio td:eq("+(i+1)+")").text(result[i].currentRatio);
                $("#mainBusinessIncome td:eq("+(i+1)+")").text(result[i].mainBusinessIncome+"亿");
                $("#netProfit td:eq("+(i+1)+")").text(result[i].netProfit+"亿");
                $("#totalAssets td:eq("+(i+1)+")").text(result[i].totalAssets+"亿");
                $("#shareholdersEquity td:eq("+(i+1)+")").text(result[i].shareholdersEquity+"亿");
            }
            for(var i = stockNameArray.length; i < 6; i++){
                $("#weekChangeRatio td:eq("+(i+1)+")").text("--");
                $("#weekChangeRatio td:eq("+(i+1)+")")[0].style.color = "black";
                $("#monthChangeRatio td:eq("+(i+1)+")").text("--");
                $("#monthChangeRatio td:eq("+(i+1)+")")[0].style.color= "black";
                $("#seasonChangeRatio td:eq("+(i+1)+")").text("--");
                $("#seasonChangeRatio td:eq("+(i+1)+")")[0].style.color = "black";
                $("#halfayearChangeRatio td:eq("+(i+1)+")").text("--");
                $("#halfayearChangeRatio td:eq("+(i+1)+")")[0].style.color = "black";
                $("#yearChangeRatio td:eq("+(i+1)+")").text("--");
                $("#yearChangeRatio td:eq("+(i+1)+")")[0].style.color = "black";
                $("#pe td:eq("+(i+1)+")").text("--");
                $("#pb td:eq("+(i+1)+")").text("--");
                $("#pcf td:eq("+(i+1)+")").text("--");
                $("#ps td:eq("+(i+1)+")").text("--");
                $("#perShareEarnings td:eq("+(i+1)+")").text("--");
                $("#netProfitMarginOnSales td:eq("+(i+1)+")").text("--");
                $("#roa td:eq("+(i+1)+")").text("--");
                $("#debtAssetRatio td:eq("+(i+1)+")").text("--");
                $("#currentRatio td:eq("+(i+1)+")").text("--");
                $("#mainBusinessIncome td:eq("+(i+1)+")").text("--");
                $("#netProfit td:eq("+(i+1)+")").text("--");
                $("#totalAssets td:eq("+(i+1)+")").text("--");
                $("#shareholdersEquity td:eq("+(i+1)+")").text("--");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.responseText);
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert("失败233");
        }
    });
}

/**
 * 显示股票对比的图表
 * @param stockNameArray
 */
function fillInChart(stockNameArray) {

    timeContainer.showLoading();
    rangeContainer.showLoading();
    priceContainer.showLoading();
    $.ajax({
        type: "post",
        async: true,//异步执行
        url: "/StockCompare/getCompareData.do",
        dataType: "json",
        data : {"stockNameArray" : stockNameArray.join(",")},
        success : function (result) {
            // alert("success");
            console.log(result);
            xTimeData = result[0][0];
            yTimeData = [];
            for(var i = 0; i < stockNameArray.length; i++){
                yTimeData.push(result[i][1]);
            }

            xRangeData = result[0][2];
            yRangeData = [];
            for(var i = 0; i < stockNameArray.length; i++){
                yRangeData.push(result[i][3]);
            }

            xPriceData = result[0][4];
            yPriceData = [];
            for(var i = 0; i < stockNameArray.length; i++){
                yPriceData.push(result[i][5]);
            }
            lineChart(timeContainer,stockNameArray,xTimeData,yTimeData);

            timeContainer.hideLoading();
            rangeContainer.hideLoading();
            priceContainer.hideLoading();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.responseText);
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert("失败ca");
        }
    })
}

/**
 * 设置涨跌幅数据的颜色，涨为红色，跌为绿色
 * @param node
 * @param value
 */
function setFontColor(node,value) {
    if(value >= 0){
        node.addClass("redFont");
    }else{
        node.addClass("greenFont");
    }
}

function showPromptDialog(jq_ele) {
    var dia = $("#holder")[0];
    var offsetX = jq_ele.offset().left,
        offsetY = jq_ele.offset().top,
        width = jq_ele.width(),
        height = jq_ele.height();
    dia.style.left = offsetX +  "px";
    dia.style.top = offsetY + height+ 10 + "px";
}