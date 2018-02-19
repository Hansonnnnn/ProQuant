<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%--<meta charset="UTF-8">--%>
    <title>用户中心</title>
    <%@include file="common/head.jsp"%>
    <link href="../../css/myCSS/UserCenterCSS2.css" rel="stylesheet">
    <script type="text/javascript" src="../../js/myJS/UserCenterJS.js"></script>

</head>
<body>
<!--导航栏start-->
<nav class="navbar navbar-default navbar-fixed-top" style="height: 51px">
    <div class="container">
        <div class="navbar-header">
            <div>
                <a class="navbar-brand" href="#">
                    <svg style="display: inline-block;" version="1.1"
                         xmlns="http://www.w3.org/2000/svg"
                         x="0px" y="0px" width="25px" viewBox="0 0 48 34" enable-background="new 0 0 48 34" xml:space="preserve">
                    <defs>
                        <clipPath id="mask">
                            <polygon transform="rotate(180 18 11)" points="10,15.2 13.5,10.9 23.2,20.8 35.7,7.9 35.7,0 10,0 "/>
                        </clipPath>
                    </defs>
                        <g transform="rotate(180 18 11)" clip-path="url(#mask)">
                            <rect x="0" y="0" width="7" height="14" fill="#f2f2f2">
                                <animateTransform  attributeType="xml"
                                                   attributeName="transform" type="scale"
                                                   values="1,1; 1,1.5; 1,1"
                                                   begin="0.4s" dur="0.8s" repeatCount="indefinite" />
                            </rect>
                            <rect x="9" y="0" width="7" height="19" fill="#f2f2f2">
                                <animateTransform  attributeType="xml"
                                                   attributeName="transform" type="scale"
                                                   values="1,0.2; 1,1.2; 1,0.2"
                                                   begin="0.3s" dur="0.8s" repeatCount="indefinite" />
                            </rect>
                            <rect x="18" y="0" width="8" height="14" fill="#f2f2f2">
                                <animateTransform  attributeType="xml"
                                                   attributeName="transform" type="scale"
                                                   values="1,0.2; 1,1.5; 1,0.2"
                                                   begin="0s" dur="0.8s" repeatCount="indefinite" />
                            </rect>
                        </g>

                        <polygon fill="#E67E25" points="38.6,2 41.4,4.2 36.1,10.2 29.5,17 26.8,19.8 23.3,23.3 19.9,19.8 17.1,17 13.7,13.5 10.2,17.7
                    0,30.2 10,20.8 10,34 16.9,34 16.9,22.2 19.7,25.1 19.7,34 26.7,34 26.7,25.3 29.5,22.5 29.5,34 36.4,34 36.4,15.5 44.1,7.1 46.4,9.7 48,0   "/>
                </svg>
                    <div style="display: inline-block;font-size: 25px">ProQuant</div>
                </a>
            </div>
        </div>
        <ul id="navigation" class="nav navbar-nav">
            <li><a href="/HomePage/ToHomePage.do">首页</a></li>
            <li><a href="/MarketPage/toMarketPage.do">市场情况</a></li>
            <li><a href="/StockCompare/toStockCompare.do">股票比较</a></li>
            <li><a href="/Strategy/toStrategy.do">策略回测</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li id="searchText">
                <input id="searchInput" type="text" placeholder="搜索">
                <button style="background-color: transparent;color: #1F4374;"><i class="fa fa-search" aria-hidden="true"></i></button>
            </li>
            <li class="loginButtonHolder">
                <%if(session.getAttribute("username") != null){%>
                <button id="loginButton"><i class="fa fa-user-circle" aria-hidden="true"><%=session.getAttribute("username")%></i></button>
                <%}else{%>
                <button id="loginButton"><i class="fa fa-user-circle " aria-hidden="true">未登录</i></button>
                <%}%>
            </li>
        </ul>
    </div>
</nav>
<!--导航栏end-->
<div class="user-main-body">
    <div class="content-holder">
        <div class="personal-info-holder">
            <script>
                function readFile(input) {

////                        alert(ele.value);
////                        document.getElementById("head-image").src = ele.value;
//                        var file = null;
//                        if(node.files && node.files[0] ){
//                            file = node.files[0];
//                        }else if(node.files && node.files.item(0)) {
//                            file = node.files.item(0);
//                        }
//                        alert(file.path);

                    if (input.files && input.files[0]) {

                        var reader = new FileReader();
                        reader.onload = function (e) {
                            $('#head-image').attr('src', e.target.result);
//                                var sss = e.target.result;
//                                   alert(sss);
                            alert("haha");
                            alert($('#head-image').attr('src'));
                            document.write($('#head-image').attr('src'));
                        };
                        reader.readAsDataURL(input.files[0]);

                    }


                }
            </script>
            <input name="file" type="file" id="upload-head-image-button" onchange="readFile(this)">
            <img src="../images/temp-head.png" alt="Head Image" style="cursor: pointer" id="head-image">
            <span>Welcome!</span>
            <span id="username-one"><%=session.getAttribute("username")%></span>
            <span><img id="edit-button-one" src="../images/edit.png" style="width: 20px;height: 20px;border-radius: 0px;margin-left: -10px;border: none;cursor: pointer"></span>
            <button id="qiandao-button">签到<img id="qiandao-button-image" src="../images/bottom-arrow-tri.svg" alt="下拉框" style="margin-left: 5px"></button>
            <button id="logoutButton" class="opacityEighty">登出</button>
        </div>
        <div class="function-zone">
            <div class="bootstrap-tab">
                <ul class="nav nav-tabs">
                    <li class="active" id="tab0"><a href="#" style="cursor: pointer">个人账户</a></li>
                    <li  id="tab1"><a href="#">自选股</a></li>
                    <%--<li id="tab3"><a href="#">使用策略记录</a></li>--%>
                    <%--<li id="tab4"><a href="#">浏览个股历史</a></li>--%>
                    <%--<button id="clear-button">清空</button>--%>
                </ul>
            </div>
            <!--标签页下面的功能区部分-->
            <div class="function-content" id="user-account-part">
                <div class="user-data-holder">
                    <div>
                        <script>
                            function changeToCNY() {
                                $("#popover").webuiPopover('hide');
                                $("#popover").html("总资产-人民币计 ∨");
                            }
                            function changeToUSD() {
                                $("#popover").webuiPopover('hide');
                                $("#popover").html("总资产-美元计 ∨");
                            }
                        </script>
                        <h4><a href="#" id="popover" data-title="货币类型" data-content="<a href='#' id='cnyLink' style='cursor: pointer' onclick='changeToCNY()'>人民币CNY</a></br><a href='#' id='usdLink' style='cursor: pointer' onclick='changeToUSD()'>美元USD</a>" data-placement="right">总资产-人民币计 ∨</a></h4>
                        <h2 id="totalProperty">￥${accountPageTotalVO.accountVO.totalProperty}</h2>
                        <div class="user-item">
                            <h2>可用现金</h2>
                            <div><h4 id="availableProperty">${accountPageTotalVO.accountVO.availableProperty}</h4></div>
                        </div>
                        <div class="user-item">
                            <h2>总盈亏</h2>
                            <div><h4>￥${accountPageTotalVO.accountVO.totalProfitAndLoss}</h4></div>

                        </div>
                        <div class="user-item">
                            <h2>今日盈亏</h2>
                            <div><h4>￥${accountPageTotalVO.accountVO.dayProfitAndLoss}</h4></div>
                        </div>
                        <div class="user-item">
                            <%--<h2 style="border-left: 3px solid #296ec2">投资表现</h2>--%>
                            <%--<div id="graph-holder">--%>

                            <%--</div>--%>
                        </div>
                    </div>
                </div>
                <div class="deal-record-holder">
                    <h2 class="secondTabActive" id="second-tab1"><a>持仓记录</a></h2>
                    <h2 class="secondTabDeActive" id="second-tab2"><a>成交记录</a></h2>
                    <table class="table-hover table-responsive table-striped" id="chicang-table">
                        <thead>
                        <tr>
                            <th>股票代码</th>
                            <th>股票名称</th>
                            <th>最新价</th>
                            <th>持仓数量</th>
                            <%--<th>浮动盈亏</th>--%>
                            <th>卖出</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="chicangRecord" items="${accountPageTotalVO.ownedStocksVOs}">
                            <tr>
                                <td>${chicangRecord.code}</td>
                                <td>${chicangRecord.stockName}</td>
                                <td>${chicangRecord.newestPrice}</td>
                                <td>${chicangRecord.ownedNum}</td>
                                <td>
                                    <a title="sellButton">
                                        <img src="../../images/sell.svg" alt="卖出">
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <table class="table-hover table-responsive disappear table-striped" id="chengjiao-table">
                        <thead>
                        <tr>
                            <th>交易时间</th>
                            <th>股票名称</th>
                            <th>交易类型</th>
                            <th>交易数量</th>
                            <th>均价</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="dealRecord" items="${accountPageTotalVO.dealRecordsVOs}">
                            <tr>
                                <td>${dealRecord.dealDate}</td>
                                <td>${dealRecord.stockName}</td>
                                <td>${dealRecord.dealType}</td>
                                <td>${dealRecord.dealNum}</td>
                                <td>${dealRecord.averagePrice}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="function-content zixuangu-part disappear" id="zixuangu-part">
                <table class="table-hover table-responsive table-striped">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>股票ID</th>
                        <th>股票名称</th>
                        <th>现价</th>
                        <th>涨跌幅</th>
                        <th>成交量</th>
                        <th>删除自选</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% int i = 0;%>
                    <c:forEach var="myStock" items="${optionalStocks}" >
                        <tr>
                            <td><%=i%></td>
                            <td>
                                <a href="/StockInfo/${myStock.id}/toStockInfo.do" target="_blank">${myStock.id}</a>
                            </td>
                            <td>
                                <a href="/StockInfo/${myStock.id}/toStockInfo.do" target="_blank">${myStock.name}</a>
                            </td>
                            <td>${myStock.currentPrice}</td>
                            <td>${myStock.chg}</td>
                            <td>${myStock.volume}</td>
                            <td>
                                <a href="javascript:void(0);" title="deleteButton">
                                    <img src="../../images/delete.png">
                                </a>
                            </td>
                        </tr>
                        <% i++; %>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="function-content usestrategy-part disappear" id="usestrategy-part">
                <table class="table-hover table-responsive table-striped">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>策略算法</th>
                        <th>开始时间</th>
                        <th>结束时间</th>
                        <th>股票池</th>
                        <th>策略基准</th>
                        <th>形成期</th>
                        <th>持有期</th>
                        <%--<th>调仓周期(天)</th>--%>
                        <th>最大持股数(支)</th>
                        <%--<th>使用策略</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                    <%int j = 0;%>
                    <c:forEach var="strategyRecord" items="${strategyRecords}">
                        <tr>
                            <td><%=j%></td>
                            <td style="display: none;">${strategyRecord.index}</td>
                            <td>${strategyRecord.strategAlgorithmName}</td>
                            <td>${strategyRecord.startDate}</td>
                            <td>${strategyRecord.endDate}</td>
                            <td>${strategyRecord.stockPlate}</td>
                            <td>${strategyRecord.strategyBaseRule}</td>
                            <%--<td>${strategyRecord.tiaoCangChouQi}</td>--%>
                            <td>${strategyRecord.possessDays}</td>
                            <td>${strategyRecord.holdDays}</td>
                            <td>${strategyRecord.maxHoldStocksNum}</td>
                            <%--<td>--%>
                                <%--<a href="#" title="userStrategy">使用</a>--%>
                            <%--</td>--%>
                            <%--<td style="display: none;">120001</td>--%>
                        </tr>
                        <%j++;%>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%--<div class="function-content history-part disappear" id="history-part">--%>
                <%--<div style="display: inline-block;">--%>
                    <%--<table class="table-hover table-responsive table-striped">--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th>序号</th>--%>
                            <%--<th>股票ID</th>--%>
                            <%--<th>股票名称</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<tr>--%>
                            <%--<td>1</td>--%>
                            <%--<td>--%>
                                <%--<a>000000</a>--%>
                            <%--</td>--%>
                            <%--<td>--%>
                                <%--<a>测试股</a>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                <%--</div>--%>
                <%--<div class="Easter-Egg">--%>
                    <%--<div class="head-image-box">--%>
                        <%--<img src="../../images/upbg.jpg" style="width: 460px;height: 310px;border: 3px solid #8c8c8c;border-radius: 3px">--%>
                        <%--<p style="width: 100%;text-align: right">--Pic : Trade area of the VOC around 1700</p>--%>
                    <%--</div>--%>
                    <%--<div class="text-box">--%>
                        <%--<p style="margin: 5px;padding-left: 10px;padding-right: 10px;line-height: 18px">&nbsp;&nbsp;&nbsp;&nbsp;VOC is widely known as the "Dutch East India Company".The VOC is generally considered to be--%>
                            <%--the first multinational enterprise to issue shares of stock to the public.</p>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        </div>
    </div>
</div>
<!--页脚start-->
<div class="footer  container">
    <div class="row">
        <div class="left-footer col-sm-9">
            <div>
                <h2>关于ProQuant</h2>
                <br>
                <p>ProQuant是一款基于提供股票市场情况展示，个股信息展示，股票各项指标预测功能于一体的多功能股票查询、
                    <br>分析的软件。</p>
            </div>
        </div>
        <div class="right-footer col-sm-3">
            <div>
                <h2>关于我们</h2>
                <br>
                <p></p>
            </div>
            <div>
                <a href=""><img src="../../images/weibo.png" alt="微博"></a>
                <a href=""><img src="../../images/weixin.png" alt="微信"></a>
                <a href=""><img src="../../images/qq.png" alt="QQ"></a>

            </div>
        </div>
    </div>
</div>
<!--页脚end-->
<!--隐藏对话框：功能：当用户选择多只删除时弹出-->
<div id="attentionDialog" class="fadeIn">
    <h2>提示</h2>
    <h4>您确认删除已选中的多只股票吗？</h4>
    <div style="width: 50%;margin: 5px auto;">
        <input type="button" value="确认" id="confirm-button">
        <input type="button" value="取消" style="background-color: #8c8c8c" id="cancel-button">
    </div>
</div>

<div class="edit-info-holder opacityEighty container disappear" id="edit-info-holder">
    <div class="left-triangle"><!--本Div只来实现三角形，无其他作用--></div>
    <form class="form-horizontal" role="form">
        <!--<div class="id-holder form-group row">-->
        <!--<label class="col-sm-4 control-label" style="padding-right: 20px;">账号</label>-->
        <!--<label class="col-sm-8 control-label" style="padding-right: 50%;">0000001</label>-->
        <!--</div>-->
        <div class="username-holder form-group row">
            <label class="col-sm-4 control-label" >用户名</label>
            <label class="col-sm-8 control-label" style="padding-right: 50%;"><a id="username-two">Luminary</a></label>
        </div>
        <div class="form-group row">
            <label class="col-sm-4 control-label" for="old-passwordInput">原密码</label>
            <div class="col-sm-8">
                <input class="form-control" id="old-passwordInput" type="password" value="" >
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-4 control-label" for="new-passwordInput">新密码</label>
            <div class="col-sm-8">
                <input class="form-control" id="new-passwordInput" type="password" value="">
            </div>
        </div>
    </form>
    <div class="save-button-holder">
        <button type="button" id="edit-button-two" class="editAndSaveButtonOut">编辑</button>
        <button type="button" id="save-button" class="editAndSaveButtonOut">保存</button>
    </div>
</div>

<!--<div class="qiandao-holder">-->
<!--&lt;!&ndash;放置四个数据&ndash;&gt;-->
<!--<div>-->
<!--<div class="my-row">-->
<!--<label class="fixed-label">连续签到</label>-->
<!--<label class="unfixed-label">3</label>-->
<!--<label class="fixed-label">当月签到</label>-->
<!--<label class="unfixed-label">10</label>-->
<!--</div>-->
<!--<div class="my-row">-->
<!--<label class="fixed-label">总签到</label>-->
<!--<label class="unfixed-label">20</label>-->
<!--<label class="fixed-label">签到奖励</label>-->
<!--<label class="">10000</label>-->
<!--</div>-->
<!--</div>-->
<!--&lt;!&ndash;放置两个日期&ndash;&gt;-->
<!--<div>-->
<!--<div class="my-row">-->
<!--<div class="month-selector">-->
<!--<img src="../images/left-arrow-tri.svg">-->
<!--<label>2017-06</label>-->
<!--<img src="../images/right-arrow-tri.svg">-->
<!--</div>-->
<!--<label class="year-label">2017-06-04</label>-->
<!--</div>-->
<!--</div>-->
<!--&lt;!&ndash;放置日历&ndash;&gt;-->
<!--<div>-->
<!---->
<!--</div>-->
</div>
<div class="new-qiandao-holder disappear">
    <div class="top-triangle"></div>
    <div>
        <h4>您已连续签到</h4><h1 id="conDaysHolder">5</h1><h4>天</h4>
    </div>
    <div>
        <h4>当前每日获得奖励</h4><h1>1000</h1><h4>元</h4>
    </div>
    <div>
        <h4>累计连续签到</h4><h1>7</h1><h4>天</h4>
    </div>
    <div>
        <h4>每日将获得奖励</h4><h1>5000</h1><h4>元</h4>
    </div>
</div>




<div id="toaster_close">
    <div id="toaster">
        <div id="pic_div" class="green_pic"></div>
        <div id="remind" class="green_word">提示消息</div>
    </div>
</div>
</body>
</html>


