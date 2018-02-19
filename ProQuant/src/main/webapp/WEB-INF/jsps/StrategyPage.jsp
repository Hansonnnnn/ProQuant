<%--
  Created by IntelliJ IDEA.
  User: xiezhenyu
  Date: 2017/6/9
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp"%>
<html>
<head>
    <title>策略回测</title>
    <%@include file="common/head.jsp"%>
    <link href="../../css/myCSS/StrategyPageCSS.css" rel="stylesheet">
    <link rel="stylesheet" href="../../css/myCSS/DialogCSS.css">
    <script src="../../js/echarts.min.js"></script>
</head>
<body>
<!--导航栏start-->
<nav class="navbar navbar-default navbar-fixed-top">
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
            <li class="active"><a href="/Strategy/toStrategy.do">策略回测</a></li>
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
                <button id="loginButton"><i class="fa fa-user-circle" aria-hidden="true">未登录</i></button>
                <%}%>
            </li>

        </ul>
    </div>
</nav>
<!--导航栏end-->
<div class="main">
    <div class="bg"></div>
    <div>
        <h1>策略回测</h1>
        <p>这里有最精准的策略，供您购买前对趋势的预测。</p>
    </div>
    <div class="pic-holder">
        <input type="button" value="使用策略" id="use-strategy-button">
    </div>
</div>

<div class="three-strategy">
    <%--<div class="strategy-one">--%>
        <%--<h1>BP神经网络算法</h1>--%>
        <%--<!--content-holder是策略浮块中间的部分-->--%>
        <%--<div class="content_holder">--%>
            <%--&lt;%&ndash;<h4>趋势拟合指标：</h4>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<select class="select-item">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<option value="">开盘价</option>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<option value="">收盘价</option>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<option value="">最高价</option>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<option value="">最低价</option>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<option value="">成交量</option>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</select>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<div class="graph-holder">&ndash;%&gt;--%>

            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--<br>--%>
            <%--<br>--%>
            <%--<img src="../../images/bp.JPG" width="360px" height="200px" alt="动量回测策略">--%>
            <%--<div class="message-holder">--%>
                <%--<p>注：上图展示使用2014年-2015年期间10年股票数据训练算法模型，进行预测2015年-2016年股市各项指标与真实数据走势的拟合程度，经海量数据训练算法模型，预测准确程度已达95%。</p>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<!--btn-holder是放置按钮的地方-->--%>
        <%--<div class="btn_holder">--%>
            <%--<input type="button" value="趋势预测" id="use-strategy-button-one">--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="strategy-two">
        <h1>动量交易算法</h1>
        <!--content-holder是策略浮块中间的部分-->
        <div class="content_holder">
            <br>
            <br>
            <img src="../../images/movement.png" width="500px" height="250px" alt="动量回测策略">
            <div class="message-holder">
                <p>注：动量交易策略认为股票或者其他资产的价格在一段时间内的趋势能够延续，通过成功捕获价格趋势的延续来获取收益。动量策略的思路和均值回归策略刚好相反，均值回归依赖于趋势终将会回归。</p>
            </div>
        </div>
        <div class="btn_holder">
            <input type="button" value="使用策略" id="use-strategy-button-two">
        </div>
    </div>
    <div class="strategy-three">
        <h1>均值回归算法</h1>
        <!--content-holder是策略浮块中间的部分-->
        <div class="content_holder">
            <br>
            <br>
            <img src="../../images/mean.png" width="500px" height="250px" alt="均值回归策略">
            <div class="message-holder">
                <p>注：均值回归，又称Mean Reversion，是在价格震荡中博取反弹的交易思路，它是基于Poterba和Summers（1987）首先提出的一种现象，如果要用一句话总结，那就是“跌下去的迟早要涨上来”。</p>
            </div>
        </div>
        <div class="btn_holder">
            <input type="button" value="使用策略" id="use-strategy-button-three">
        </div>
    </div>
</div>
<!--制定策略和使用策略的部分-->
<div class="main-body" id="main-body">
    <div class="bootstrap-tab">
        <ul class="nav nav-tabs">
            <li class="active"><a id="use-strategy-tab" style="cursor: pointer;">使用策略</a></li>
            <%--<li><a id="make-strategy-tab" style="cursor: pointer;">趋势预测</a></li>--%>
        </ul>
    </div>
    <div class="parent-holder">
        <div id="use-strategy-model">
            <div class="m-input-section">
                <div class="strategy-type-holder m-input-section-cell">
                    <h4>策略回测算法</h4>
                    <select class="select-item" id="algorithm-list">
                        <option value="movement">动量交易算法</option>
                        <option value="average">均值回归算法</option>
                    </select>
                </div>
                <div class="h-holder">
                    <h2>回测条件</h2>
                </div>
                <div class="m-input-section-cell">
                    <h4>股票池</h4>
                    <select class="select-item" id="stockPlate">
                        <option value="hushen300">沪深300</option>
                        <option value="chuangyeban">创业板</option>
                        <option value="zhongxiaoban">中小板</option>
                        <option value="zixuangu">自选股</option>
                    </select>
                </div>
                <div class="m-input-section-cell">
                    <h4>策略基准</h4>
                    <select class="select-item" id="baseStandard">
                        <option value="hushen300">沪深300</option>
                        <option value="chuangyeban">创业板</option>
                        <option value="zhongxiaoban">中小板</option>
                    </select>
                </div>
                <div class="m-input-section-cell">
                    <h4>形成期</h4>
                    <select class="select-item" id="processingDays">
                        <option value="10">10</option>
                        <option value="30">30</option>
                        <option value="90">90</option>
                        <option value="180">180</option>
                        <option value="365">365</option>
                    </select>
                </div>
                <div class="m-input-section-cell">
                    <h4>持有期</h4>
                    <select class="select-item" id="holdDays">
                        <option value="10">10</option>
                        <option value="30">30</option>
                        <option value="90">90</option>
                        <option value="180">180</option>
                        <option value="365">365</option>
                    </select>
                </div>
                <div class="m-input-section-cell">
                    <h4>最大持股数</h4>
                    <select class="select-item" id="maxHoldNum">
                        <option value="5">5</option>
                        <option value="10">10</option>
                        <option value="20">20</option>
                    </select>
                </div>
                <br>
                <div class="m-input-section-cell">
                    <label>开始日期<input type="date" placeholder="开始日期" id="startDatePicker"></label>
                    <label>结束日期<input  class="end-datepicker" type="date" placeholder="结束日期" id="endDatePicker"></label>
                </div>
                <div class="m-input-section-cell">
                    <button id="startButtonOne">开始回测</button>
                </div>
            </div>

        </div>
        <!--标签页下面左边的放置图表的位置-->
        <!--第一个DIV是输入的区域-->
        <div id="make-strategy-model">
            <div class="makeStrategyPart">
                <div class="" style="display: inline-block;">
                    <h4 style="display: inline-block; margin-right: 10px">股票代码</h4><input type="text" placeholder="请输入股票代码！" name="codeInput" id="codeInput"  style="line-height: 30px;height: 30px;border-radius: 5px">
                </div>
                <div class="make-strategy-button">
                    <button id="startButtonTwo">开始回测</button>
                </div>
            </div>
        </div>
        <div class="left-and-right-holder disappear">
            <div class="param-holder">
                <div>
                    <h4>收益统计</h4>
                </div>
                <table>
                    <thead>
                    <tr>
                        <th>投资组合</th>
                        <th>总收益</th>
                        <th>年化收益</th>
                        <th>夏普比率</th>
                        <th>最大回撤率</th>
                        <th>Beta</th>
                        <th>Alpha</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="thisStrategyTable">
                        <td>本策略</td>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                    </tr>
                    <tr id="baseStrategyTable">
                        <td>基准策略</td>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                        <td>--</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="left-part">
                <h4>策略回测结果</h4>
                <div class="bootstrap-tab">
                    <ul class="nav nav-tabs">
                        <li class="active"><a id="lineButton" data-toggle="tab">收益曲线</a></li>
                        <li><a id="barButton" data-toggle="tab">收益周期</a></li>
                    </ul>
                </div>
                <div><div class="chart-holder" id="cycleChart"></div></div>
            </div>
            <!--标签页下面右边的放置评分栏的位置-->
            <div class="right-part">
                <h4>策略评测</h4>
                <div class="score-holder" id="scoreChart"></div>
            </div>

            <div class="strategyResultPart disappear">
                <div class="left-part">
                    <h4>历史数据趋势拟合</h4>
                    <div class="chart-holder"></div>
                </div>
                <!--标签页下面右边的放置评分栏的位置-->
                <div class="right-part">
                    <h4>明日趋势</h4>
                    <div class="score-holder">
                        <div>
                            <p id="typeHolder" class="typeHolderTextColor">涨</p>
                        </div>
                        <div>
                            <div class="valueTotalHolder lHolder">
                                <p><span>预测置信度</span><p id="trustValueHolder">90%</p></p>
                            </div>
                            <div class="valueTotalHolder rHolder">
                                <p><span>涨跌幅</span><p id="valueHolder">30%</p></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
<!--登录弹出框start-->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="model-content">
            <div class="cont_principal">
                <div class="cont_centrar">
                    <div class="cont_login">
                        <form action="">
                            <div class="cont_tabs_login">
                                <ul class='ul_tabs'>
                                    <li class="active"><a onclick="sign_in()">登录</a>
                                        <span class="linea_bajo_nom"></span>
                                    </li>
                                    <li><a onclick="sign_up()">注册</a><span class="linea_bajo_nom"></span>
                                    </li>
                                </ul>
                            </div>
                            <div class="cont_text_inputs">
                                <input type="text" class="input_form_sign " placeholder="姓名" name="name_us" id="nameField"/>
                                <input type="text" class="input_form_sign d_block active_inp" placeholder="用户名" name="emauil_us" id="userNameField"/>
                                <input type="password" class="input_form_sign d_block  active_inp" placeholder="密码" name="pass_us" id="passwordField"/>
                                <input type="password" class="input_form_sign" placeholder="确认密码" name="conf_pass_us" id="confirmPasswordField"/>
                                <a  class="link_forgot_pass d_block" id="forgetPassword">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;忘记密码？</a>
                                <div class="terms_and_cons d_none">
                                    <div><input type="checkbox" name="terms_and_cons" /> <label>接受用户协议</label></div>
                                </div>
                            </div>
                            <div class="cont_btn">
                                <a  class="btn_sign" id="submitButton">登录</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--登录弹出框end-->
<div id="toaster_close">
    <div id="toaster">
        <div id="pic_div" class="green_pic"></div>
        <div id="remind" class="green_word">提示消息</div>
    </div>
</div>

<div class="displayNone" id="headContainer">
    <div style="overflow: hidden;display: inline-block;padding: 3px">
        <table class="table table-striped table-condensed">
            <tbody id="headHolder">
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            <tr class="displayNone"></tr>
            </tbody>
        </table>
    </div>
</div>

</body>
<script src="../../charts/strategyLineChart.js"></script>
<script src="../../charts/strategyBarChart.js"></script>
<script src="../../charts/strategyRadarChart.js"></script>
<script src="../../js/myJS/StrategyJS.js" type="text/javascript"></script>
</html>
