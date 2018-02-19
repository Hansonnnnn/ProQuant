/**
 * Created by xiezhenyu on 2017/6/5.
 */
var addStockButtonWasClicked, marketTableCurrentEle, marketDialogIsShow;
$(function () {
    if($("#loginButton").eq(0).text()!=="未登录"){
        hasLogin = true;
    }
    //为最上面的涨跌分布和涨跌停标签添加事件
    $("#zhangdie-tab").click(function () {
        $(this).addClass("cur");
        $("#zzjs-tab").removeClass("cur");
        $("#zdfb").removeClass("disappear");
        $("#zzjs").addClass("disappear");
    });

    $("#zzjs-tab").click(function () {
        $(this).addClass("cur");
        $("#zhangdie-tab").removeClass("cur");
        $("#zzjs").removeClass("disappear");
        $("#zdfb").addClass("disappear");
    });
    // addStyleToRow();

    //实现提示框拖动的效果
    $("#attentionDialog")[0].onmousedown = fnDown;
    // $("#confirm-button").click(function (event) {
    //     var list = $("tbody").find("tr").hasClass("selectedClass");
    //     for (var i = 0;i < list.length;i++){
    //         var code = $("tbody").find("tr").eq(i).children().eq(1).children().eq(0).text();
    //         alert(code);
    //         selectedRowIDList.push(code)
    //     }
    //     event.stopPropagation();
    //     marketSendManyEleOneTime();
    // });
    // $("#cancel-button").click(function () {
    //     $("#attentionDialog")[0].style.display = "none";
    //     marketDeSelectTableRows(marketTableCurrentEle, marketTableFirstRowIndex, marketTableLastRowIndex);
    // });





    // $("#allStocksTable").find("tr").mousedown(function () {
    //     // alert($("#allStocksTable").find("tr").length);
    //     marketTableMouseIsDown = true;
    //     marketTableFirstRowIndex = $(this).index();
    //     selectThisRow($(this));
    // });

    // $("#allStocksTable").find("tr").mouseup(function () {
    //     marketTableMouseIsDown = false;
    //     marketTableLastRowIndex = $(this).index();
    //     marketTableCurrentEle = $(this);
    //     marketSelectRows(marketTableCurrentEle, marketTableFirstRowIndex, marketTableLastRowIndex);
    // });

    // $("#allStocksTable").find("tr").mouseover(function () {
    //     if(marketTableMouseIsDown){
    //         var selectedRowID = $(this).children().eq(1).children().eq(0).text();
    //         selectedRowIDList.push(selectedRowID);
    //         selectThisRow($(this));
    //     }
    // });

    $("a[title=addStocks]").click(function (event) {
        event.stopPropagation();
        addStockButtonWasClicked = true;

        var stockCode = $(this).parent().siblings("td").eq(1).children().eq(0).text();
        // alert($(".loginButtonHolder").children().eq(0).children().eq(0).text());
        if ($(".loginButtonHolder").children().eq(0).children().eq(0).text() !== "未登录"){
            addToMyStocks(stockCode);
        }else{
            slidein(2, "请先登录^_^")
        }
    });

    // $("#allStocksTab").click(function () {
    //
    //     $.ajax({
    //         type : "GET",
    //         async : true,
    //         url : "",
    //
    //     })
    // })

    // $("#confirm-button").click(function (e) {
    //     e.stopPropagation();
    //     confirm();
    // })
    //
    // $("#cancel-button").click(function (e) {
    //     e.stopPropagation();
    //     // cancel();
    //
    // })
});



function addStyleToRow() {
    var trLists = $("tbody").find("tr");
    for(var i = 0;i < trLists.length;i++){
        var index = trLists.eq(i).index();
        var ele = trLists.eq(i);
        if(index % 2 === 1){
            if(ele.hasClass("oddRowBg")||(ele.hasClass("evenRowBg"))){
                ele.removeClass("oddRowBg");
                ele.removeClass("evenRowBg");
            }
            ele.addClass("oddRowBg");
        }else{
            if(ele.hasClass("oddRowBg")||(ele.hasClass("evenRowBg"))){
                ele.removeClass("oddRowBg");
                ele.removeClass("evenRowBg");
            }
            ele.addClass("evenRowBg");
        }
    }
}

function fnDown(event) {
    event = event || window.event;
    var oDrag = $("#attentionDialog").get(0),
        //光标按下时，光标距离面板左边的距离
        disX = event.clientX - oDrag.offsetLeft,
        //光标按下时，光标距离面板上边的距离
        disY = event.clientY - oDrag.offsetTop;
    //移动
    document.onmousemove = function (event) {
        event = event || window.event;
        fnMove(event, disX, disY);
    }
    //释放鼠标
    document.onmouseup = function () {
        document.onmousemove = null;
        //把本身时间也卸载掉
        document.onmouseup = null;
    }
}


function fnMove(e, posX, posY) {
    var oDrag = $("#attentionDialog").get(0),
        l = e.clientX - posX,
        t = e.clientY - posY,
        //获取浏览器窗口的宽度和高度
        winWidth = document.documentElement.clientWidth || document.body.clientWidth,
        winHeight = document.documentElement.clientHeight || document.body.clientHeight,
        //div.offsetWidth获取容器的宽度
        maxWidth = winWidth - oDrag.offsetWidth,
        maxHeight = winHeight - oDrag.offsetHeight;
    //最后确定容器位置的是left和top
    if(l < 0){
        l = 0;
    }else if(l > maxWidth){
        l = maxWidth;
    }
    if(t < 0){
        t = 0;
    }else if(t > maxHeight){
        t = maxHeight;
    }
    oDrag.style.left = l + 'px';
    oDrag.style.top = t + 'px';
}

//该方法实现了单行即时选中效果
function selectThisRow(ele) {
    if(ele.hasClass("oddRowBg")||(ele.hasClass("evenRowBg"))){
        ele.removeClass("oddRowBg");
        ele.removeClass("evenRowBg");
    }
    ele.addClass("selectedClass");
}

function marketSelectRows(ele, frIndex, lrIndex) {
    if((lrIndex - frIndex)>=1){
        showPromptDoalog(ele);
    }else{
        ele.removeClass("selectedClass");
    }
}

function marketDeSelectTableRows(ele, frIndex, lrIndex) {
    //要用父级元素获得全部的行，在选中其中的某些行
    var tableRowList = ele[0].parentNode.rows;
    for(var i = 0;i < tableRowList.length;i++){
        if((i >= frIndex)&&(i <= lrIndex)){
            $(tableRowList[i]).removeClass("selectedClass")
        }
    }
    //本来灰色的行的样式都已经给去掉了，现在再加回去
    // addStyleToRow();
    marketHideDialog();
}
//
// function marketSendManyEleOneTime() {
//     var trLists = $("tbody").find("tr");
//     for(var i = 0;i < trLists.length;i++){
//         if(trLists.eq(i).hasClass("selectedClass")){
//             trLists.eq(i).removeClass("selectedClass");
//         }
//     }
//     // alert(selectedRowIDList.length);
//     if(hasLogin){
//         alert("marketSendManyEleOneTime()");
//         var myStockList = selectedRowIDList.join(";");
//         $.ajax({
//             type : "post",
//             url : "/MarketPage/addMyStock.do",
//             data : {
//                 "key" : 0,
//                 "list" : myStockList
//             },
//             dataType : "json",
//             success : function () {
//             },
//             error : function () {
//
//             }
//         });
//     }else {
//         slidein(2, "请先登录^_^");
//     }
//
//     // addStyleToRow();
//     marketHideDialog();
// }







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
                slidein(2, "已在自选股列表");
            }
        },
        error : function () {
            // alert("addToMyStocks"+"失败 line 254");
        }
    });
}
function marketShowDialog(jq_ele) {
    marketDialogIsShow = true;
    var dia = $("#attentionDialog")[0];
    var offsetX = jq_ele.offset().left,
        offsetY = jq_ele.offset().top,
        width = jq_ele.width(),
        height = jq_ele.height();
    dia.style.left = offsetX + (width / 2)  +  "px";
    dia.style.top = offsetY + height + "px";
    dia.style.display = "block";
}
function marketHideDialog() {
    marketDialogIsShow = false;
    $("#attentionDialog").fadeOut();
}

//下面封装三个函数，分别为鼠标按下触发、鼠标经过时触发、鼠标放开时触发
var idList = [], refList = [] ,marketTableMouseIsDown;
function mouseDown(ref, id) {
    marketTableMouseIsDown = true;
    ref.addClass("selectedClass");
    idList.push(id);
    refList.push(ref);
}
function mouseOver(ref, id) {
    if(marketTableMouseIsDown){
        ref.addClass("selectedClass");
        idList.push(id);
        refList.push(ref);
    }
}
function mouseUp() {
    marketTableMouseIsDown = false;
    // alert(idList.length);
    // for (var i = 0; i < idList.length;i++){
    //     alert(idList[i]);
    // }
    if(idList.length <= 1){
        if(idList.length === 1){
            refList[0].removeClass("selectedClass");
            idList = [];
            refList = [];
        }
    }else{
        marketShowDialog(refList[refList.length-1]);
    }

}
//为确认和取消按钮设置事件响应
// function confirm(){
//     for(var i = 0; i < refList.length;i++){
//         refList.eq(i).removeClass("selectedClass");
//     }
//     //清空引用数组
//     refList = [];
//     var finalIdStr = idList.join(";");
//
//     $.ajax({
//         type : "post",
//         url: "/MarketPage/addMyStock.do",
//         data: {
//             'key' : "0",
//             'list' : finalIdStr
//         },
//         dataType : "json",
//         success : function () {
//             slidein(0, "成功加入自选股!");
//         },
//         error : function () {
//             alert("加入自选股"+"失败" + " MarketJS line313 ");
//         }
//     });
//
//     idList = [];
//     marketHideDialog();
// }
// function cancel(){
//     for(var i = 0; i < refList.length;i++){
//         refList.eq(i).removeClass("selectedClass");
//     }
//     //清空引用数组
//     refList = [];
//     //清空id数组
//     idList = [];
//     // marketHideDialog();
//     marketDialogIsShow = false;
//     // alert("haha");
//     $("#attentionDialog").fadeOut();
// }

