/**
 * Created by xiezhenyu on 2017/5/17.
 */
//全局变量，选中的第一行，选中的最后一行
//rowChanged用来标识行是否变化过，来判断是最一开始还没进行行删除和增加操作还是已进行过这些操作的不同状态，之所以设置该标志，
//     是因为最一开始行上是没有类的，这时要添加新类，而进行过操作的行是有类的，这时要将原有的类替换为新的类（奇->偶/偶->奇）
//operationButtonWasClicked用来标识删除键或其他操作键是否被点击，以区分是在行上的点击事件还是在这些功能键上的点击,
//    该状态需要注意的是什么是否被置为false
var firstRowIndex, lastRowIndex, currentEle, mouseIsDown, dialogIsShow, rowChanged, operationButtonWasClicked, edit_dialogIsShowed,
    qiandao_holderIsShowed;
var deletedMyStocks = [], deletedStrategysList = [];

$(function(){
    $("#username-two").text($(".loginButtonHolder").children().eq(0).children().eq(0).text());
    rowChanged = false;
    operationButtonWasClicked = false;
    //为tab标签设置点击事件
    var tablist = ["#tab0", "#tab1", "#tab2", "#tab3", "#tab4"];
    var pagelist = ["#user-account-part","#zixuangu-part", "#usestrategy-part", "#history-part"];
    function myRemoveOtherClass(tabName) {
        for(var i = 0;i < tablist.length;i++)
        {
            if(tabName != tablist[i])
            {
                $(tablist[i]).removeClass("active");
            }
        }
    }
    function showPage(pageName) {
        $(pageName).removeClass("disappear");
        for (var i = 0;i < pagelist.length;i++)
        {
            if(pagelist[i] != pageName)
            {
                $(pagelist[i]).addClass("disappear");
            }

        }
    }
    $("#tab0").click(function () {
        $(this).addClass("active");
        myRemoveOtherClass("#tab0");
        $("#user-account-part").removeClass("disappear");
        $("#clear-button").hide();
        showPage("#user-account-part");
    });
    $("#tab1").click(function () {
        $(this).addClass("active");
        myRemoveOtherClass("#tab1");
        $("#zixuangu-part").removeClass("disappear");
        $("#clear-button").show();
        showPage("#zixuangu-part");
    });
    // $("#tab2").click(function () {
    //     $(this).addClass("active");
    //     myRemoveOtherClass("#tab2");
    //     $("#").removeClass("disappear");
    //
    //     showPage("#user-info-part");
    // });
    $("#tab3").click(function () {
        $(this).addClass("active");
        myRemoveOtherClass("#tab3");
        $("#usestrategy-part").removeClass("disappear");
        $("#clear-button").show();
        showPage("#usestrategy-part");
    });
    $("#tab4").click(function () {
        $(this).addClass("active");
        myRemoveOtherClass("#tab4");
        $("#history-part").removeClass("disappear");
        $("#clear-button").show();
        showPage("#history-part");
    });

    //货币类型选择框
    $("#popover").webuiPopover({width:120,height:55});



    //持仓记录和交易记录之间的切换
    $("#second-tab1").click(function () {
        $(this).addClass("secondTabActive");
        $("#second-tab2").removeClass("secondTabActive");
        $("#second-tab2").addClass("secondTabDeActive");
        $("#chicang-table").removeClass("disappear");
        $("#chengjiao-table").addClass("disappear");
    });
    $("#second-tab2").click(function () {
        $(this).addClass("secondTabActive");
        $("#second-tab1").removeClass("secondTabActive");
        $("#second-tab1").addClass("secondTabDeActive");
        $("#chengjiao-table").removeClass("disappear");
        $("#chicang-table").addClass("disappear");
    });



    //为清空按钮添加事件响应
    $("#clear-button").click(function () {
        clearAllItems();
    });

    //实现提示框拖动的效果
    $("#attentionDialog")[0].onmousedown = fnDown;
    $("#confirm-button").click(function () {
        deleteManyEleOneTime();
    });
    $("#cancel-button").click(function () {
        $("#attentionDialog")[0].style.display = "none";
        deSelectTableRows(currentEle, firstRowIndex, lastRowIndex);
    });






    //为表格行添加样式
    // addStyleToRow();






    //获取行的高度
    // var rowHeight = $("tr:first").height();
    // alert(rowHeight);
    //获取鼠标按下和弹起时各自的位置

    // $("tr").click(function () {
    //     alert("sadas");
    // });
    // $("table")[0].onmousedown = getY1;

    // $("table tr").click(function () {
    //     if(operationButtonWasClicked){
    //         alert($(this).index());
    //     }
    // });


    //这样写是不是行数突然变多了 ~_~
    $("#zixuangu-part").find("tr").mousedown(function () {
        mouseIsDown = true;
        firstRowIndex = $(this).index();
        if(!operationButtonWasClicked){
            selectThisRow($(this));
        }
    });
    $("#zixuangu-part").find("tr").mouseup(function () {
        mouseIsDown = false;
        lastRowIndex = $(this).index();
        currentEle = $(this);
        if(!operationButtonWasClicked){
            selectRows(currentEle, firstRowIndex, lastRowIndex);
        }
    });
    $("#zixuangu-part").find("tr").mouseover(function () {
        if(mouseIsDown){
            if(!operationButtonWasClicked){
                selectThisRow($(this));
            }
        }
    });
    $("#makestrategy-part").find("tr").mousedown(function () {
        mouseIsDown = true;
        firstRowIndex = $(this).index();
        if(!operationButtonWasClicked){
            selectThisRow($(this));
        }
    });
    $("#makestrategy-part").find("tr").mouseup(function () {
        mouseIsDown = false;
        lastRowIndex = $(this).index();
        currentEle = $(this);
        if(!operationButtonWasClicked){
            selectRows(currentEle, firstRowIndex, lastRowIndex);
        }
    });
    $("#makestrategy-part").find("tr").mouseover(function () {
        if(mouseIsDown){
            if(!operationButtonWasClicked){
                selectThisRow($(this));
            }
        }
    });
    $("#usestrategy-part").find("tr").mousedown(function () {
        mouseIsDown = true;
        firstRowIndex = $(this).index();
        if(!operationButtonWasClicked){
            selectThisRow($(this));
        }
    });
    $("#usestrategy-part").find("tr").mouseup(function () {
        mouseIsDown = false;
        lastRowIndex = $(this).index();
        currentEle = $(this);
        if(!operationButtonWasClicked){
            selectRows(currentEle, firstRowIndex, lastRowIndex);
        }
    });
    $("#usestrategy-part").find("tr").mouseover(function () {
        if(mouseIsDown){
            if(!operationButtonWasClicked){
                selectThisRow($(this));
            }
        }
    });

    $("#history-part").find("tr").mousedown(function () {
        mouseIsDown = true;
        firstRowIndex = $(this).index();
        if(!operationButtonWasClicked){
            selectThisRow($(this));
        }
    });
    $("#history-part").find("tr").mouseup(function () {
        mouseIsDown = false;
        lastRowIndex = $(this).index();
        currentEle = $(this);
        if(!operationButtonWasClicked){
            selectRows(currentEle, firstRowIndex, lastRowIndex);
        }
    });
    $("#history-part").find("tr").mouseover(function () {
        if(mouseIsDown){
            if(!operationButtonWasClicked){
                selectThisRow($(this));
            }
        }
    });

    // $("table tr").mousedown(function () {
    //     mouseIsDown = true;
    //     firstRowIndex = $(this).index();
    //     if(!operationButtonWasClicked){
    //         selectThisRow($(this));
    //     }
    // });
    // $("tr").mouseup(function () {
    //     mouseIsDown = false;
    //     lastRowIndex = $(this).index();
    //     currentEle = $(this);
    //     if(!operationButtonWasClicked){
    //         selectRows(currentEle, firstRowIndex, lastRowIndex);
    //     }
    // });
    // $("tr").mouseover(function () {
    //     if(mouseIsDown){
    //         if(!operationButtonWasClicked){
    //             selectThisRow($(this));
    //         }
    //     }
    // });
    //为所有不是行的地方设置监听事件，点击之后选中的行取消选中，如果提示框在就隐藏
    // deEffect();


    // 为自选股和使用过的策略表格的删除按钮设置事件
    $("a[title=deleteButton]").mousedown(function (event) {
        event.stopPropagation();
        $(this).parent().parent().remove();
        rowChanged = true;
        operationButtonWasClicked = true;

        // addStyleToRow();
        var stockCode = $(this).parent().siblings("td").eq(1).text().trim();
        alert(stockCode);
        var list = [];

        //删除单值自选股：传入的list为空的list
        deleteMyStocks(stockCode, list);


    });
    $("a[title=deleteButton]").mouseup(function () {
        operationButtonWasClicked = false;

    });

    //为持仓记录表格所有的卖出按钮设置事件
    $("a[title=sellButton]").mousedown(function (event) {
        event.stopPropagation();
        $(this).parent().parent().remove();
        rowChanged = true;
        operationButtonWasClicked = true;
        var username = $(".loginButtonHolder").children().eq(0).children().eq(0).text();
        var stockCode = $(this).parent().siblings("td").eq(0).text();
        var num = $(this).parent().siblings("td").eq(3).text();
        alert(username);
        alert(stockCode);
        alert(num);


        //同时还得调用bl方法更新账户数据
        $.ajax({
            type: 'post',
            url: '/user/sell.do',
            data : {
                "username": username,
                "stockCode" : stockCode,
                "num": num
            },
            dataType: 'json',
            success: function (result) {
                if(result){
                    switch(result.singal){
                        case "Success":
                            slidein(0, "成功卖出！");
                            break;
                        default:
                            slidein(1, "网络故障，请稍后重试");
                            break;
                    }
                }
            },
            error: function () {
                alert("error UserCenterJS line 303");
            }

        });

        // addStyleToRow();
    });

    $("a[title=sellButton]").mouseup(function () {
        operationButtonWasClicked = false;
    });



    //为个人账户部分内部的切换标签页设置事件监听
    $("#second-tab1").click(function () {
        $(this).removeClass("secondTabDeActive");
        $(this).addClass("secondTabActive");
        $("#second-tab2").removeClass("secondTabActive");
        $("#second-tab2").addClass("secondTabDeActive");
    });
    $("#second-tab2").click(function () {
        $(this).removeClass("secondTabDeActive");
        $(this).addClass("secondTabActive");
        $("#second-tab1").removeClass("secondTabActive");
        $("#second-tab1").addClass("secondTabDeActive");
    });
    
    
    
    //修改个人信息的按钮
    $("#edit-button-one").click(function (e) {
        if(!edit_dialogIsShowed){
            edit_dialogIsShowed = true;
            e.stopPropagation();
            $(".edit-info-holder").removeClass("disappear");
        }
    });

    $(".edit-info-holder").mouseover(function () {
        $(this).removeClass("opacityEighty");
    });
    $(".edit-info-holder").mouseout(function () {
        $(this).addClass("opacityEighty");
    });


    $(document).click(function (e) {
        // e.stopPropagation();
        e = window.event || e;
        var target = e.srcElement || e.target;
        if(edit_dialogIsShowed && (!$(target).is("#edit-info-holder, #edit-info-holder *"))){
            $(".edit-info-holder").addClass("disappear");
            edit_dialogIsShowed = false;
        }
        if(qiandao_holderIsShowed && (!$(target).is(".new-qiandao-holder, .new-qiandao-holder *"))){
            $(".new-qiandao-holder").addClass("disappear");
            qiandao_holderIsShowed = false;
        }
    });

    //实现tooltip
    $("#username-two").tooltip({title: "点击进行编辑.", delay: {show: 300, hide: 100}, placement: "bottom"});


    //为编辑和保存按钮添加事件监听
    $("#edit-button-two").mouseover(function () {
        $(this).addClass("editAndSaveButtonOn");
    });
    $("#edit-button-two").mouseout(function () {
        $(this).removeClass("editAndSaveButtonOn");
    });
    $("#save-button").mouseover(function () {
        $(this).addClass("editAndSaveButtonOn");
    });
    $("#save-button").mouseout(function () {
        $(this).removeClass("editAndSaveButtonOn");
    });



    //先判断是否已签到
    var username = $(".loginButtonHolder").children().eq(0).children().eq(0).text();
    testHasLog(username);



    $("#qiandao-button").click(function (e) {
        e = window.event || e;
        var target = e.srcElement || e.target;

        if(!$(target).is("#qiandao-button-image")){
            if($(this).text() === "已签到"){
                slidein(2, "您今天已签到！");
            }else{
                $.ajax({
                    type: "post",
                    url: "/user/log.do",
                    data : {
                        "username": username
                    },
                    dataType: "json",
                    success: function (result) {
                        // alert("成功！");
                        if(result){
                            $("#qiandao-button").html('已签到<img id="qiandao-button-image" src="../images/bottom-arrow-tri.svg" alt="下拉框" style="margin-left: 5px">');
                            var conDays = result.conDays;
                            $("#conDaysHolder").text(conDays);
                            slidein(0, "签到成功！");
                        }
                    },
                    error: function () {
                        alert("签到失败，userCenterJS line 401");
                    }

                })
            }

        }


    });

    $("#qiandao-button-image").click(function (e) {
        e = window.e || e;
        e.stopPropagation();
        if($("#qiandao-button").text()!=="签到"){
            showQianDaoDialog($(this));
            qiandao_holderIsShowed = true;
            $(".new-qiandao-holder").removeClass("disappear");
        }

    });

    $("#head-image").click(function () {
       $("#upload-head-image-button").click();
    });

    $("#logoutButton").mouseover(function () {
        $(this).removeClass("opacityEighty");
    });
    $("#logoutButton").mouseout(function () {
        $(this).addClass("opacityEighty");
    })
    $("#logoutButton").click(function () {
        slidein(0, "登出成功");
        setTimeout(logout(),500);
    })



    $("#old-passwordInput").attr("disabled", true);
    $("#new-passwordInput").attr("disabled", true);



    $("#edit-button-two").click(function () {
        $("#old-passwordInput").attr("disabled", false);
        $("#new-passwordInput").attr("disabled", false);
    })

    $("#save-button").click(function () {
        var oldP = $("#old-passwordInput").val();
        var newP = $("#new-passwordInput").val();
        if(oldP !== newP){
            slidein(2, "两次输入不一致");
        }else{
            $.ajax({
                type: "post",
                url: "/user/alterUserInfo.do",
                data : {
                    "username": username,
                    "password": oldP
                },
                dataType: "json",
                success: function (result) {
                    // alert("成功！");
                    if(result){
                        slidein(0, "修改密码成功");
                        $(document).click();
                    }else {
                        slidein(1, "原密码错误");
                    }
                },
                error: function () {
                    alert("签到失败，userCenterJS line 401");
                }

            })
        }
    })
});





//为表格行添加样式
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
// var Y1, Y2;
// function getY1(event) {
//     alert("getY1");
//     event = event || window.event;
//     Y1 = event.clientY;
// }
// function getY2(event) {
//     alert("getY2");
//     event = event || window.event;
//     Y2 = event.clientY;
//     document.write(Y1 + "<br>");
//     document.write(Y2 + "<br>");
//     document.write(Y2 - Y1);
// }

//功能：当两行的差距大于1时才显示提示框
function selectRows(ele, frIndex, lrIndex) {
    if((lrIndex - frIndex)>=1){
        showDialog();
    }else{
        ele.removeClass("selectedClass");
    }

    // showDialog();
}
function deSelectTableRows(ele, frIndex, lrIndex) {
    //要用父级元素获得全部的行，在选中其中的某些行
    var tableRowList = ele[0].parentNode.rows;
    for(var i = 0;i < tableRowList.length;i++){
        if((i >= frIndex)&&(i <= lrIndex)){
            $(tableRowList[i]).removeClass("selectedClass")
        }
    }
    //本来灰色的行的样式都已经给去掉了，现在再加回去
    // addStyleToRow();

    hideDialog();
}

//该方法实现了单行即时选中效果
function selectThisRow(ele) {
    if(ele.hasClass("oddRowBg")||(ele.hasClass("evenRowBg"))){
        ele.removeClass("oddRowBg");
        ele.removeClass("evenRowBg");
    }
    ele.addClass("selectedClass");
}

//将要删除的多只自选股的每一只添加到要删除的自选股列表里面
function addToMyDeletedStocksList(identifier) {
    deletedMyStocks.push(identifier);
}
function clearMyDeletedStocksList() {
    //实现清空待删除的自选股列表
    deletedMyStocks = [];
}
//将要删除的多项策略记录的每一项添加到要删除的策略列表里面
function addToDeletedStrategyslIst(identifier) {
    deletedStrategysList.push(identifier);
}
function clearDeletedStrategyslIst() {
    deletedStrategysList = [];
}


function showDialog() {
    dialogIsShow = true;
    $("#attentionDialog").fadeIn();
}
function hideDialog() {
    dialogIsShow = false;
    $("#attentionDialog").fadeOut();
}


//为所有不是行的地方设置监听事件，点击之后选中的行取消选中，如果提示框在就隐藏
function deEffect() {
    $(":not(tr)").click(function () {
        alert($(this).nodeName);
        var trLists = $("tbody").find("tr");
        for(var i = 0;i < trLists.length;i++){
            if(trLists.eq(i).hasClass("selectedClass")){
                trLists.eq(i).removeClass("selectedClass")
            }
        }
        // addStyleToRow();
        hideDialog();
    })

}

function deleteManyEleOneTime() {
    var trLists = $("tbody").find("tr");
    for(var i = 0;i < trLists.length;i++){
        if(trLists.eq(i).hasClass("selectedClass")){
            trLists.eq(i).remove();
        }
    }
    // addStyleToRow();
    hideDialog();
}

//实现清空所有行
function clearAllItems() {
    var divLists = $("table").parent();
    for(var i = 0;i < divLists.length;i++){
        if(divLists.eq(i).is(":visible")){
            divLists.eq(i).find("tbody").find("tr").remove();
        }
    }
}

function showQianDaoDialog(jq_ele) {
    var dia = $(".new-qiandao-holder")[0];
    var offsetX = jq_ele.offset().left,
        offsetY = jq_ele.offset().top,
        width = jq_ele.width(),
        height = jq_ele.height();
    dia.style.left = offsetX + (width / 2) -20 +  "px";
    dia.style.top = offsetY + height+ 20 + "px";
    // dia.style.display = "block";
}

function deleteMyStocks(code, list) {
    $.ajax({
        type : "post",
        url : "/user/deleteMyStock.do",
        data : {
            "code" : code,
            "list" : list
        },
        dataType : "json",
        success : function (result) {
            if(result){
                slidein(0, "删除自选股成功！");
            }
        },
        error : function () {
            alert("error UserCenterPage line 688");
            console.log("UserCenterPage line 688");
        }
    })
}


function testHasLog(username) {
    $.ajax({
        type : "post",
        url : "/user/testHasLog.do",
        data : {
            "username": username
        },
        dataType : "json",
        success : function (result) {
            if(result){
                var value = result.hasLog;
                if(value === false){
                    return "false";

                }else{
                    $("#qiandao-button").html('已签到<img id="qiandao-button-image" src="../images/bottom-arrow-tri.svg" alt="下拉框" style="margin-left: 5px">');
                    $.ajax({
                        type: "post",
                        url: "/user/log.do",
                        data : {
                            "username": username
                        },
                        dataType: "json",
                        success: function (result) {
                            // alert("成功！");
                            if(result){
                                var conDays = result.conDays;
                                $("#conDaysHolder").text(conDays);
                            }
                        },
                        error: function () {
                            alert("签到失败，userCenterJS line 401");
                        }

                    })
                    return "true";
                }
            }
        },
        error : function () {
            alert("error UserCenterPage 签到检查 line 667");
        }
    })
}