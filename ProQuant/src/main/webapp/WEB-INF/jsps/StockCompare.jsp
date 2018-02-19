<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2017/6/6
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="PO.recommendedStock.PeakPO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>股票对比</title>
    <%@include file="common/head.jsp"%>
    <link rel="stylesheet" href="../../css/stockCompare.css">
    <link rel="stylesheet" href="../../css/myCSS/DialogCSS.css">
    <script src="../../js/echarts.min.js"></script>
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
            <li  class="active"><a href="/StockCompare/toStockCompare.do">股票比较</a></li>
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

<!--股票对比主要内容start-->
<div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <!--<div class="headLabel">股票对比</div>-->
                <div>
                    <div class="col-sm-4" style="padding-left:0;padding-right: 10px">
                        <div class="headLabel">选择对比股票</div>
                        <!--添加对比股票-->
                        <div style="background-color: white;padding: 15px;margin-bottom: 10px">
                            <div class="centerAlignment">
                                <label>添加对比：</label>
                                <input type="text" id="searchTextField" placeholder="请输入股票名称或代码">
                            </div>
                            <ul id="stockContainer">
                            </ul>

                            <div class="centerAlignment" style="margin: 10px 0">
                                <p class="yellow_word">提示：最多对比五只股票</p>
                            </div>
                            <div style="padding-left:8%; ">
                                <button id="deleteAllButton">清空</button>
                                <button id="compareButton">开始对比</button>
                            </div>
                        </div>
                        <div style="background-color: white;padding: 15px">
                            <div>
                                <button class="hotStockButton hotStockBottomBorder" id="hotCompareStockButton">热门股票</button>
                                <button class="hotStockButton" id="browseCompareStockButton">最近访问</button>
                            </div>
                            <div id="hotCompareStock">
                                <table class="table">
                                    <tbody>
                                    <% ArrayList<PeakPO> arrayList = (ArrayList<PeakPO>) session.getAttribute("peakPOS");
                                        for (int i = 0; i < arrayList.size(); i++){
                                    %>
                                        <tr>
                                            <td><a><%=arrayList.get(i).getStockName()%></a></td>
                                            <% if (!arrayList.get(i).getRiseOrDown().substring(0,1).equals("-")){ %>
                                                <td class="redFont"><%=arrayList.get(i).getUptodate()%></td>
                                                <td class="redFont"><%=arrayList.get(i).getRiseOrDown()%></td>
                                            <%}else{%>
                                                <td class="greenFont"><%=arrayList.get(i).getUptodate()%></td>
                                                <td class="greenFont"><%=arrayList.get(i).getRiseOrDown()%></td>
                                            <%}%>
                                            <td><button class="addCompareStockButton"><i class="fa fa-plus" aria-hidden="true"></i>对比</button></td>
                                        </tr>
                                    <%
                                        }
                                    %>
                                    </tbody>
                                </table>
                            </div>

                            <div class="displayNone" id="hotBrowseStock">
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <td><a>杭州园林</a></td>
                                        <td class="redFont">61.60</td>
                                        <td class="redFont">10.00%</td>
                                        <td><button class="addCompareStockButton"><i class="fa fa-plus" aria-hidden="true"></i>对比</button></td>
                                    </tr>
                                    <tr>
                                        <td><a>奥翔药业</a></td>
                                        <td class="redFont">26.18</td>
                                        <td class="redFont">10.00%</td>
                                        <td><button class="addCompareStockButton"><i class="fa fa-plus" aria-hidden="true"></i>对比</button></td>
                                    </tr>
                                    <tr>
                                        <td><a>超频三</a></td>
                                        <td class="redFont">35.18</td>
                                        <td class="redFont">1.85%</td>
                                        <td><button class="addCompareStockButton"><i class="fa fa-plus" aria-hidden="true"></i>对比</button></td>
                                    </tr>
                                    <tr>
                                        <td><a>展鹏科技</a></td>
                                        <td class="greenFont">24.81</td>
                                        <td class="greenFont">4.72%</td>
                                        <td><button class="addCompareStockButton"><i class="fa fa-plus" aria-hidden="true"></i>对比</button></td>
                                    </tr>
                                    <tr>
                                        <td><a>寿仙谷</a></td>
                                        <td class="greenFont">42.56</td>
                                        <td class="greenFont">5.55%</td>
                                        <td><button class="addCompareStockButton"><i class="fa fa-plus" aria-hidden="true"></i>对比</button></td>
                                    </tr>
                                    <tr>
                                        <td><a>金石资源</a></td>
                                        <td class="redFont">22.20</td>
                                        <td class="redFont">2.33%</td>
                                        <td><button class="addCompareStockButton"><i class="fa fa-plus" aria-hidden="true"></i>对比</button></td>
                                    </tr>
                                    <tr>
                                        <td><a>万通智控</a></td>
                                        <td class="greenFont">18.01</td>
                                        <td class="greenFont">9.99%</td>
                                        <td><button class="addCompareStockButton"><i class="fa fa-plus" aria-hidden="true"></i>对比</button></td>
                                    </tr>
                                    <tr>
                                        <td><a>伟隆股份</a></td>
                                        <td class="greenFont">46.66</td>
                                        <td class="greenFont">4.39%</td>
                                        <td><button class="addCompareStockButton"><i class="fa fa-plus" aria-hidden="true"></i>对比</button></td>
                                    </tr>
                                    <tr>
                                        <td><a>森特股份</a></td>
                                        <td class="redFont">27.15</td>
                                        <td class="redFont">4.06%</td>
                                        <td><button class="addCompareStockButton"><i class="fa fa-plus" aria-hidden="true"></i>对比</button></td>
                                    </tr>
                                    <tr>
                                        <td><a>金溢科技</a></td>
                                        <td class="redFont">52.76</td>
                                        <td class="redFont">1.66%</td>
                                        <td><button class="addCompareStockButton"><i class="fa fa-plus" aria-hidden="true"></i>对比</button></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-8">
                        <div class="headLabel">走势对比<span style="font-size: 15px;font-weight: 500">  (请在左侧添加对比股票)</span></div>
                        <div class="whiteBackground" style="padding-top: 15px;margin-bottom: 10px">
                            <div style="margin-bottom: 5px;margin-left: 15px">
                                <ul>
                                    <li style="display: inline-block"><button class="chartChooseButton hotStockBottomBorder" id="timeChartButton">分时图</button></li>
                                    <li style="display: inline-block"><button class="chartChooseButton" id="rangeChartButton">涨跌幅</button></li>
                                    <li style="display: inline-block"><button class="chartChooseButton" id="priceChartButton">收盘价</button></li>
                                </ul>
                            </div>
                            <div id="compareTimeContainer" style="height: 480px"></div>
                            <div id="compareRangeContainer" style="height: 480px;display: none"></div>
                            <div id="comparePriceContainer" style="height: 480px;display: none"></div>
                        </div>

                        <div class="headLabel" style="margin-top: 10px">基本指标对比</div>
                        <div class="whiteBackground" style="padding: 15px;margin-bottom: 15px">
                            <table class="table table-bordered table-condensed table-striped">
                                <thead>
                                <tr id="compareTableHead">
                                    <th style="width: 16%">对比项</th>
                                    <th style="width: 16%">--</th>
                                    <th style="width: 16%">--</th>
                                    <th style="width: 16%">--</th>
                                    <th style="width: 16%">--</th>
                                    <th style="width: 16%">--</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr id="weekChangeRatio">
                                    <td class="compareIndex">一周涨跌</td>
                                    <td >--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="monthChangeRatio">
                                    <td class="compareIndex">一月涨跌</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="seasonChangeRatio">
                                    <td class="compareIndex">三月涨跌</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="halfayearChangeRatio">
                                    <td class="compareIndex">半年涨跌</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="yearChangeRatio">
                                    <td class="compareIndex">一年涨跌</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="pe">
                                    <td class="compareIndex">市盈率</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="pb">
                                    <td class="compareIndex">市净率</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="pcf">
                                    <td class="compareIndex">市现率</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="ps">
                                    <td class="compareIndex">市销率</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="perShareEarnings">
                                    <td class="compareIndex">每股收益</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="netProfitMarginOnSales">
                                    <td class="compareIndex">销售净利润</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="roa">
                                    <td class="compareIndex">资产收益率</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="debtAssetRatio">
                                    <td class="compareIndex">资产负债率</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="currentRatio">
                                    <td class="compareIndex">流动比率</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="mainBusinessIncome">
                                    <td class="compareIndex">主营收入</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="netProfit">
                                    <td class="compareIndex">净利润</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="totalAssets">
                                    <td class="compareIndex">总资产</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                <tr id="shareholdersEquity">
                                    <td class="compareIndex">股东权益</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                    <td>--</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-sm-2"></div>
        </div>
    </div>
</div>
<!--股票对比主要内容end-->


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
                                <a  class="link_forgot_pass d_block" id="forgetPassword">忘记密码？</a>
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


<!--滑出提示框-->
<div id="toaster_close">
    <div id="toaster">
        <div id="pic_div" class="green_pic"></div>
        <div id="remind" class="green_word">提示消息</div>
    </div>
</div>

<div class="displayNone" id="holder">
    <div style="overflow: hidden;display: inline-block;padding: 3px">
        <table class="table table-striped table-condensed">
            <tbody id="compareHolder">
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
                <tr class="displayNone"></tr>
            </tbody>
        </table>
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
<script src="../../charts/lineChart.js"></script>
<script src="../../charts/stackChart.js"></script>
<script type="text/javascript" src="../../js/myJS/DialogJS.js"></script>
<script src="../../js/stockCompare.js"></script>
</body>
</html>
