<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="VO.StockVO" %>
<%@ page import="java.util.Date" %>
<%@ page import="PO.StockData" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2017/6/6
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个股信息</title>
    <%@include file="common/head.jsp"%>
    <link rel="stylesheet" href="../../css/stockInfo.css">
    <link rel="stylesheet" href="../../css/myCSS/DialogCSS.css">
    <script src="../../js/echarts.min.js"></script>
    <script src="../../js/jqPaginator.js"></script>
</head>
<body>
<!--导航栏start-->

<nav class="navbar navbar-default navbar-fixed-top" style="background-color: #407FCE;height: 51px">
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
                <button id="loginButton"><i class="fa fa-user-circle" aria-hidden="true">未登录</i></button>
                <%}%>
            </li>
        </ul>
    </div>
</nav>
<!--导航栏end-->

<!--个股信息start-->
<div id="stockInfo">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2" style="margin-top: 100px;padding-left: 100px">
                <div class="headLabel dropdown" style="margin-right: -100px" ><p>创新高</p></div>
                <div class="whiteBackground" style="margin-right: -100px;padding-bottom: 10px">
                    <table class="table browseTable" style="margin-bottom: 10px">
                        <thead>
                        <tr>
                            <th>股票名称</th>
                            <th>连续上涨(天)</th>
                            <th>涨跌幅(%)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="ctu" items="${CTU}">
                            <tr>
                                <td>${ctu.stockName}</td>
                                <td>${ctu.continuingDays}</td>
                                <td>${ctu.riseOrdown}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <%--<div style="text-align: center"><button id="deleteBrowseButton">清空浏览记录</button></div>--%>
                </div>
            </div>
            <div class="col-sm-8" style="padding: 100px">
                <!--股票基本信息start-->
                <div id="basicInfo" style="margin-bottom: 40px">
                    <div class="headLabel"><p>个股信息</p></div>
                    <div id="info">
                        <div class="row">
                            <!--股票名称 代码 加入自选股-->
                            <div class="infoName col-sm-3">
                                <div>
                                    <div id="stockName">${stockVO.name}</div>
                                    <div style="font-size: 20px;margin-top: 10px;margin-bottom: 20px;color: #333333" id="stockIdHolder"><%=session.getAttribute("stockId")%></div>
                                    <a id="stockAddButton"><i class="fa fa-plus"
                                                                       aria-hidden="true"></i>加入自选股</a>
                                </div>
                            </div>
                            <!--股票当前价格 增幅-->
                            <div class="infoPrice col-sm-3">
                                <div style="text-align: center">

                                    <%
                                        StockVO stockVO =(StockVO) session.getAttribute("stockVO");
                                        if(stockVO.getAmount_Of_Increase() >= 0){
                                    %>
                                    <div style="margin-top: 30px;margin-bottom: 5px;color: #C10D01">
                                        <span style="font-size:30px;" class="priceHolder">${stockVO.price}</span>
                                        <img src="../../images/rise.svg" style="margin-top: -15px;height: 50px;width: 50px">
                                    </div>
                                    <div style="margin-bottom: 15px;color: #C10D01;">
                                        <span style="font-size:20px;">${stockVO.price_Of_Increase}</span>
                                        <span style="font-size:20px;">(${stockVO.amount_Of_Increase}%)</span>
                                    </div>
                                    <%   }else{%>
                                    <div style="margin-top: 30px;margin-bottom: 5px;color: #018101">
                                        <span style="font-size:30px;" class="priceHolder">${stockVO.price}</span>
                                        <img src="../../images/decline.png" style="margin-top: -15px;height: 50px;width: 50px">
                                    </div>
                                    <div style="margin-bottom: 15px;color: #018101">
                                        <span style="font-size:20px;">${stockVO.price_Of_Increase}</span>
                                        <span style="font-size:20px;">(${stockVO.amount_Of_Increase}%)</span>
                                    </div>
                                    <%}%>
                                    <div><button id="buyButton"><img src="../../images/buy.svg">买入</button></div>
                                </div>
                            </div>
                            <!--股票数据 开盘价 收盘价等-->
                            <div class="infoData col-sm-6">
                                <ul>
                                    <li class="stockData">今开：<span>${stockVO.openValue}</span></li>
                                    <li class="stockData">成交量：<span>${stockVO.volume}</span></li>
                                    <li class="stockData">振幅：<span>${stockVO.amplitude}%</span></li>
                                    <li class="stockData">最高：<span>${stockVO.highValue}</span></li>
                                    <li class="stockData">成交额：<span>${stockVO.turnover}万</span></li>
                                    <li class="stockData">换手：<span>${stockVO.turnoverRate}%</span></li>
                                    <li class="stockData">最低：<span>${stockVO.lowValue}</span></li>
                                    <li class="stockData">总市值：<span>${stockVO.marketValue}亿</span></li>
                                    <li class="stockData">市净率：<span>${stockVO.marketRate}</span></li>
                                    <li class="stockData">昨收：<span>${stockVO.closeValue}</span></li>
                                    <%--<li class="stockData">流通市值：<span>${stockVO.circulationMarketValue}亿</span></li>--%>
                                    <%--<li class="stockData">市盈率(动)：<span>${stockVO.priceEarningsRatio}</span></li>--%>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!--股票基本信息end-->

                <!--k线图start-->
                <div style="margin-bottom: 40px">
                    <div class="headLabel"><p>股票k线</p></div>
                    <div id="kLine">
                        <div>
                            <ul>
                                <li id="time" class="blueBackground"><button>分时</button></li>
                                <li id="dayLine"><button>日K</button></li>
                                <li id="weekLine"><button>周K</button></li>
                                <li id="monthLine"><button>月K</button></li>
                            </ul>
                        </div>
                        <!--k线图容器-->
                        <div>
                            <div id="main" style="height: 590px"></div>
                        </div>
                    </div>
                </div>
                <!--k线图end-->

                <div style="margin-bottom: 40px;margin-top: 50px">
                    <div class="row" >
                        <div class="col-sm-9">
                            <div class="headLabel"><p>历史数据趋势拟合</p></div>
                            <div style="border: 1px solid #f8f8f8; width: 100%;height: 300px;background-color: white;">
                                <div id="bpChart" style="height: 300px"></div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="headLabel"><p>明日趋势</p></div>
                            <div style="border: 1px solid #f8f8f8; width: 100%;height: 300px;background-color: white;text-align: center">
                                <div>
                                    <p id="typeHolder" style="color: #d75442;margin: 15px;padding-top: 10px;font-size: 48px;font-weight: 900;width: 100px;height: 100px;border: 5px solid #f8f8f8;border-radius: 50%;text-align: center;">涨</p>
                                </div>
                                <div style="margin-top: 20px">
                                    <div>
                                        <p style="text-align: center;color: #d75442;font-weight: 700;">预测置信度</p>
                                        <p id="trustValueHolder" style="margin-top: 5px">90%</p>
                                    </div>
                                    <div style="margin-top: 20px">
                                        <p style="text-align: center;color: #d75442;font-weight: 700;">涨跌幅</p>
                                        <p id="valueHolder" style="margin-top: 5px">30%</p>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <!--综合分析start-->
                <div style="margin-bottom: 40px;margin-top: 50px">
                    <div class="headLabel"><p>综合分析</p></div>
                    <div id="analysis" style="background-color: white">
                        <div class="row">
                            <div class="col-sm-6">
                                <div id="meter" style="height: 400px"></div>
                                <div align="center" style="margin-top: -60px;font-size: 25px">综合评分</div>
                            </div>
                            <div class="col-sm-6">
                                <div id="radar" style="height: 400px;"></div>
                                <div align="center" style="margin-top: -60px;font-size: 25px">分项得分</div>
                            </div>
                        </div>
                        <div align="center" style="font-size: 22px;margin-top: 10px">综合评判：<span id="score">5.5分</span></div>
                        <div align="center" style="font-size: 22px;padding-bottom: 20px">打败了<span id="beat">68%</span>的股票!</div>
                    </div>

                </div>
                <!--综合分析end-->

                <!--股票详细数据start-->
                <div style="margin-bottom: 40px">
                    <div class="headLabel"><p>详细数据</p></div>
                    <div id="historyData" style="background-color: white;padding-bottom: 20px">
                        <table class="table table-striped table-condensed">
                            <thead>
                            <tr>
                                <th>日期</th>
                                <th>开盘价</th>
                                <th>收盘价</th>
                                <th>最高价</th>
                                <th>最低价</th>
                                <th>涨跌幅</th>
                                <th>成交量</th>
                                <th>换手率</th>
                            </tr>
                            </thead>
                            <tbody>

                            <%
                                ArrayList<StockData> stockDataArrayList = (ArrayList<StockData>)session.getAttribute("stockDataArrayList");
                                ArrayList<String> stockHistoryDate = (ArrayList<String>)session.getAttribute("stockHistoryDate");
                                System.out.println("stockDataArrayList    "+stockDataArrayList.size());
                                for(int i = 0; i < stockDataArrayList.size(); i++){
                            %>
                                <tr class="disappear">
                                    <td><%=stockHistoryDate.get(i)%></td>
                                    <td><%=stockDataArrayList.get(i).getOpen()%></td>
                                    <td><%=stockDataArrayList.get(i).getClose()%></td>
                                    <td><%=stockDataArrayList.get(i).getHigh()%></td>
                                    <td><%=stockDataArrayList.get(i).getLow()%></td>
                                    <%
                                        if (stockDataArrayList.get(i).getChg() >= 0){
                                    %>
                                        <td style="color: #C10D01"><%=stockDataArrayList.get(i).getChg()%>%</td>
                                    <%
                                        }else{
                                    %>
                                        <td style="color: #008000"><%=stockDataArrayList.get(i).getChg()%>%</td>
                                    <%
                                        }
                                    %>
                                    <td><%=stockDataArrayList.get(i).getVolume()/100%>万</td>
                                    <td><%=stockDataArrayList.get(i).getTurnoverRatio()%>%</td>
                                </tr>
                            <%
                            }
                            %>
                            </tbody>
                        </table>
                        <div style="text-align: center">
                            <ul class="pagination" id="pagination1"></ul>
                        </div>
                    </div>
                </div>
                <!--股票详细数据end-->
            </div>
            <div class="col-sm-2" style="margin-top: 100px;margin-left: -100px">
                <!--公司简介-->
                <div class="headLabel"><p>公司简介</p></div>
                <div style="background-color: white;padding: 10px">
                    <div>
                        <p><span class="companyIntro">公司名称：</span>${companyData.cpName}</p>
                    </div>
                    <br>
                    <div>
                        <p><span class="companyIntro">英文名称：</span>${companyData.cpEnName}</p>
                    </div>
                    <br>
                    <div>
                        <p><span class="companyIntro">所在省份：</span>${companyData.cpProvince}</p>
                    </div>
                    <br>
                    <div>
                        <p><span class="companyIntro">所属行业：</span>${companyData.CName}</p>
                    </div>
                    <br>
                    <div>
                        <p><span class="companyIntro">公司网址：</span><a href="http://${companyData.cpWebsite}" style="cursor: hand">${companyData.cpWebsite}</a></p>
                    </div>
                    <br>
                    <div>
                        <p><span class="companyIntro">办公地址：</span>${companyData.cpAddress}</p>
                    </div>
                    <br>
                    <div>
                        <p><span class="companyIntro">业务：</span>${companyData.cpBusiness}</p>
                    </div>
                    <br>
                    <div>
                        <p><span class="companyIntro">简介：</span>${companyData.cpInfo}</p>
                    </div>
                    <br>
                    <div>
                        <p><span class="companyIntro"></span></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--个股信息end-->


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
                <a onclick="function showC() {
                    $('#imageModal').modal('show') ;
                }"><img src="../../images/weibo.png" alt="微博"></a>
                <a onclick="function showC() {
                    $('#imageModal').modal('show') ;
                }"><img src="../../images/weixin.png" alt="微信"></a>
                <a onclick="function showC() {
                    $('#imageModal').modal('show') ;
                }"><img src="../../images/qq.png" alt="QQ"></a>

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

<!--滑动提示框start-->
<div id="toaster_close">
    <div id="toaster">
        <div id="pic_div" class="green_pic"></div>
        <div id="remind" class="green_word">提示消息</div>
    </div>
</div>
<!--滑动提示框end-->


<div style="margin-bottom: 50px;" class="buy-page-holder disappear">
    <div class="top-triangle"><!--本Div只来实现三角形，无其他作用--></div>
    <div style="overflow: hidden;display: inline-block;padding: 10px">

        <div style="overflow: hidden">
            <div style="padding-top:7px;border: 1.5px solid #dddddd;border-radius: 2px;color: #333;height: 40px;display: inline-block;text-align: center;float: left;width: 200px">
                <span style="margin-left: 10px;padding-right: 10px;font-size: 18px;">数 量</span>
                <img src="../../images/line.svg">
                <span style="margin-right: 30px;font-size: 18px;" id="numInput">2100</span>
            </div>
            <div style="display: inline-block;">
                <button class="addPriceButton" id="deletePriceButton">
                    <img src="../../images/reducePrice.svg" >
                    <span style="font-size: 10px;color: #3999ff">100</span>
                </button>
            </div>
            <div style="display: inline-block;">
                <button class="addPriceButton"  id="addPriceButton">
                    <img src="../../images/addPrice.svg">
                    <span style="font-size: 10px;color:#3999ff">100</span>
                </button>
            </div>
        </div>
        <!--<div style="padding: 0">-->

        <!--</div>-->
        <div style="margin-top: 5px">
            <span style="font-size: 12px;margin-bottom: 5px;margin-right: 20px">最大数量：<span style="color: #3999FF;" id="maxNum">41839</span></span>
            <span style="font-size: 12px;margin-bottom: 5px">可用资金：<span style="color: #3999FF;" id="availableProperty">￥1954061</span></span>
        </div>
        <div>
            <button style="float: right" id="buyConfirmButton">确认</button>
        </div>

    </div>
</div>


<div class="modal fade" id="imageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <img src="../images/c.JPG" width="350px" height="500px">
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

<script type="text/javascript" src="../../charts/kLineChart.js"></script>
<script type="text/javascript" src="../../charts/meterChart.js"></script>
<script type="text/javascript" src="../../charts/radarChart.js"></script>
<script type="text/javascript" src="../../charts/bpLineChart.js"></script>
<script type="text/javascript" src="../../charts/stockTimeLineChart.js"></script>
<script type="text/javascript" src="../../js/myJS/DialogJS.js"></script>
<script type="text/javascript" src="../../js/stockInfo.js"></script>
</body>
</html>
