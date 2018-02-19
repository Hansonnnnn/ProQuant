/**
 * Created by xiezhenyu on 2017/5/27.
 */
var cycleChart = echarts.init(document.getElementById("cycleChart"));
var scoreChart = echarts.init(document.getElementById("scoreChart"));
var result0 = [];
var result1 = [];
var visible = false;
$(function () {
    // if($("#loginButton").eq(0).text()!=="未登录"){
    //     hasLogin = true;
    // }else{
    //     hasLogin = false;
    //     $("#stockPlate").find("option[value='zixuangu']").css("display", "none");
    // }

    // $(".left-and-right-holder").css("display", "none");
    // showCallbackResult();
    //为使用策略和制定策略两个按钮设定事件响应
    $("#use-strategy-button").click(function () {
        $("body").scrollTo(400,600);

    });
    $("#make-strategy-button").click(function () {
        $("body").scrollTo(980,600);
    });

    //为tab设置切换响应
    $("#use-strategy-tab").click(function () {
        // $("body").scrollTo(980);
        if(!$(this).hasClass("active")){
            hideStrategyResult();
        }
        $("#make-strategy-model").css("display", "none");
        $("#main-body").css("height","270px");
        $("#use-strategy-model").css("display", "block");
        $("#use-strategy-tab").parent().addClass("active");
        $("#make-strategy-tab").parent().removeClass("active");

    });


    $("#make-strategy-tab").click(function () {
        if(!$(this).hasClass("active")){
            hideResult();
        }
        $("#use-strategy-model").css("display", "none");
        $("#main-body").css("height","130px");
        $("#make-strategy-model").css("display", "block");
        $("#make-strategy-tab").parent().addClass("active");
        $("#use-strategy-tab").parent().removeClass("active");

    });

    $("#stockPlate").find("option[value='zixuangu']").click(function () {
        if($("#loginButton").eq(0).text()==="未登录"){
            slidein(2,"登录后即可使用自选股回测。");
            $("#stockPlate").find("option[value='hushen300']").attr("selected",true);
        }

    })


    /*为中部界面的三个算法的使用策略按钮设置事件响应*/
    $("#use-strategy-button-one").click(function () {
        $("body").scrollTo(980,500);
        $("#algorithm-list").find("option[value='BP神经网络算法']").attr("selected",true);
    });
    $("#use-strategy-button-two").click(function () {
        $("body").scrollTo(980,500);
        $("#algorithm-list").find("option[value='动量交易算法']").attr("selected",true);
    });
    $("#use-strategy-button-three").click(function () {
        $("body").scrollTo(980,500);
        $("#algorithm-list").find("option[value='均值回归算法']").attr("selected",true);
    });

    //设置连带反应，当选择的股票池变了之后，策略基准也变
    $("#stockPlate").change(function () {
        var currentContent = $(this).val();
        if(currentContent === "hushen300"){
            $("#baseStandard").find("option[value='hushen300']").attr("selected", true);
            $("#baseStandard").attr("disabled", true);
        //    $('#areaSelect').attr("disabled",true);
        }else if(currentContent === "chuangyeban"){
            $("#baseStandard").find("option[value='chuangyeban']").attr("selected", true);
            $("#baseStandard").attr("disabled", true);
        }else if(currentContent === "zhongxiaoban"){
            $("#baseStandard").find("option[value='zhongxiaoban']").attr("selected", true);
            $("#baseStandard").attr("disabled", true);
        }else{
            $("#baseStandard").attr("disabled", false);
        }
    });

    $("#lineButton").click(function () {
        if(visible){
            // $(this).addClass("active");
            // $("#barButton").removeClass("active");
            strategyLineChart(cycleChart,result0[2],result0[0],result0[1]);
        }
    });

    $("#barButton").click(function () {
        if(visible){
            // $(this).addClass("active");
            // $("#lineButton").removeClass("active");
            strategyBarChart(cycleChart,result1[0],result1[1],result1[3]);
        }
    });

    /*为使用策略界面设置相应*/
    //开始回测按钮设置相应
    $("#startButtonOne").click(function () {
        // if(!hasLogin){
            // slidein(2, "登录后系统为您保存回测记录。");
        // }

        //获取元素内容
        var algorithmName = $("#algorithm-list option:selected").val();//选中的值:BP,movement,average
        var stockPlate = $("#stockPlate option:selected").val();//allStocks,hushen300,chuangyeban,zhongxiaoban,zixuangu
        var baseStandard = $("#baseStandard option:selected").val();//hushen300,chuangyeban,zhongxiaoban
        var processingDays = $("#processingDays option:selected").val();//5,10,15,20,25,30
        var holdDays = $("#holdDays option:selected").val();//5,10,15,20,25,30
        var maxHoldNum = $("#maxHoldNum option:selected").val();//5,10,20
        var startDate = $("#startDatePicker").val();
        var endDate = $("#endDatePicker").val();



        if(startDate==""||endDate==""){
            // alert("请输入完整的开始日期和结束日期");
            slidein(2, "请输入完整日期信息");
        }else{
            //换算开始日期和结束日期的毫秒，放置开始日期比结束日期晚
            var startStr = startDate.split("-");
            var endStr = endDate.split("-");
            var startSecond = parseInt(startStr[0]) * 31536000 + parseInt(startStr[1])*2502200 + parseInt(startStr[2])*86400;
            var endSecond = parseInt(endStr[0]) * 31536000 + parseInt(endStr[1])*2502200 + parseInt(endStr[2])*86400;
            if(startSecond > endSecond){
                // alert("开始日期应在结束日期之前");
                slidein(2, "开始日期应在结束日期之前");
                $("#startDatePicker").val("");
                $("#endDatePicker").val("");
            }else{
                showCallbackResult();
                cycleChart.showLoading();
                scoreChart.showLoading();
                if(hasLogin){
                    // alert("hasLogin" + "******************");

                    $.ajax({
                        type : "post",
                        url : "/Strategy/callback.do",
                        data : {
                            "username" : $("#loginButton").eq(0).text(),
                            "algorithmName": algorithmName,
                            "stockPlate": stockPlate,
                            "baseStandard": baseStandard,
                            "processingDays": processingDays,
                            "holdDays": holdDays,
                            "maxHoldNum": maxHoldNum,
                            "startDate": startDate,
                            "endDate": endDate
                        },
                        dataType : "json",
                        success : function (result) {
                            if(result){
                                // alert("获取数据成功---已登录");
                                cycleChart.hideLoading();
                                scoreChart.hideLoading();
                            }
                        },
                        error : function () {
                            // alert("StrategyJS line 137");
                        }
                    })

                }else{
                    // alert("unhasLogin" + "******************");
                    $.ajax({
                        type : "post",
                        url : "/Strategy/callback.do",
                        data : {
                            "username": "null",
                            "algorithmName": algorithmName,
                            "stockPlate": stockPlate,
                            "baseStandard": baseStandard,
                            "processingDays": processingDays,
                            "holdDays":holdDays,
                            "maxHoldNum": maxHoldNum,
                            "startDate": startDate,
                            "endDate": endDate
                        },
                        dataType : "json",
                        success : function (result) {
                            if(result){
                                console.log(result);
                                cycleChart.hideLoading();
                                scoreChart.hideLoading();
                                $("#thisStrategyTable td").eq(1).text(result[3][0].rateOfTotalReturn+'%');
                                $("#thisStrategyTable td").eq(2).text(result[3][0].annualizedRateOfReturn+'%');
                                $("#thisStrategyTable td").eq(3).text(result[3][0].sharpeRatio);
                                $("#thisStrategyTable td").eq(4).text(result[3][0].biggestReturn+'%');
                                $("#thisStrategyTable td").eq(5).text(result[3][0].beta);
                                $("#thisStrategyTable td").eq(6).text(result[3][0].alpha+'%');

                                $("#baseStrategyTable td").eq(1).text(result[3][1].rateOfTotalReturn+'%');
                                $("#baseStrategyTable td").eq(2).text(result[3][1].annualizedRateOfReturn+'%');
                                // $("#baseStrategyTable td").eq(3).text(result[3][1].sharpeRatio);
                                // $("#baseStrategyTable td").eq(4).text(result[3][1].biggestReturn+'%');
                                // $("#baseStrategyTable td").eq(5).text(result[3][1].beta);
                                // $("#baseStrategyTable td").eq(6).text(result[3][1].alpha+'%');
                                // alert("获取数据成功----未登录");

                                strategyRadarChart(scoreChart,result[2]);
                                // strategyBarChart(cycleChart,result[1][0],result[1][2],result[1][3]);
                                strategyLineChart(cycleChart,result[0][2],result[0][0],result[0][1]);
                                result0 = result[0];
                                result1 = result[1];
                                visible =true;
                            }
                        },
                        error : function () {
                            // alert("StrategyJS line 137");
                        }
                    })

                }
                //开始向bl发送

            }


        }


        // if()




    });

    /*为制定策略界面设置相应*/
    $("#startButtonTwo").click(function () {
        var code = $("#codeInput").val();
        if(code === ""){
            // alert("请输入正确股票代码!");
            // slidein(2, "请输入正确股票代码!");
        }else{
            $.ajax({
                type : "post",
                url : "/Strategy/bp.do",
                data : {
                    "code" : code
                },
                dataType : "json",
                success : function (result) {
                    if(result){
                        // alert("获取bp数据结果成功");
                        console.log(result);
                        showBPResult();
                    }
                },
                error : function () {
                    // alert("StrategyJS line 226");
                }
            })
        }
    });



    //为界面加载中间部分展示精确程度的图获得数据
    // alert("开始加载图数据");
    // $.ajax({
    //     type: "post",
    //     async: false,
    //     url: "/Strategy/getPrecisionVO.do",
    //     dateType:"json",
    //     success:function (result) {
    //         if(result){
    //             alert("Success! line 219.");
    //         }
    //     },
    //     error: function () {
    //         alert("Error! line 222");
    //     }
    // })
    // alert("结束加载图数据");
});


function showResult() {
    $(".left-and-right-holder").css("display", "block");
}
function hideResult() {
    $(".left-and-right-holder").css("display", "none");
}
function showStrategyResult() {
    $(".strategyResultPart").removeClass("disappear");
}
function hideStrategyResult() {
    $(".strategyResultPart").addClass("disappear");
}


function showCallbackResult() {
    // 样式改变
    $("#main-body").css("height","1055px");
    showResult();
    $("#make-strategy-model").css("display","none");
    $("#use-strategy-model").css("display","block");
    $(".left-and-right-holder").css("padding-top", "250px");

}

function showBPResult() {
    $("#main-body").css("height","690px");
    showStrategyResult();
    $("#use-strategy-model").css("display","none");
    $("#make-strategy-model").css("display","block");
    $(".strategyResultPart").css("padding-top", "70px");
}

function addStrategyRecord(username, algorithmName, stockPlate, baseStandard, processingDays, holdDays, maxHoldNum, startDate, endDate) {
    $.ajax({
        type : "post",
        url : "/Strategy/addStrategyRecord.do",
        data : {
            "username" : username,
            "algorithmName": algorithmName,
            "stockPlate": stockPlate,
            "baseStandard": baseStandard,
            "processingDays": processingDays,
            "holdDays": holdDays,
            "maxHoldNum": maxHoldNum,
            "startDate": startDate,
            "endDate": endDate
        },
        dataType : "json",
        success : function (result) {
            if(result){
                slidein(0, "生成回测记录！");
            }
        },
        error : function () {
            // alert("StrategyJS line 274");
        }
    })
}
