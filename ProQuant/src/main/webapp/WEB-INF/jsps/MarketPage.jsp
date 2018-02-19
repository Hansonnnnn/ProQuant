<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2017/6/6
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" import="PO.recommendedStock.PeakPO"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="VO.marketPageVO.HotStockListVO" %>
<%@ page import="PO.StockCurrentData" %>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>市场行情</title>
    <%@include file="common/head.jsp"%>
    <link href="../../css/myCSS/MarketPageCSS.css" rel="stylesheet">
    <link href="../../css/myCSS/MarketPageChartsCSS.css" rel="stylesheet">
    <link rel="stylesheet" href="../../css/myCSS/DialogCSS.css">
    <link rel="stylesheet" href="../../css/jquery.webui-popover.css">
    <script src="../../js/jquery-3.2.1.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/echarts.min.js"></script>
    <script src="../../js/jquery.webui-popover.js"></script>
    <script src="../../js/jqPaginator.js"></script>
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
            <li class="active"><a href="/MarketPage/toMarketPage.do">市场情况</a></li>
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
<div class="m-container">
    <div class="m-box">
        <div class="m-head">
            <p class="headLabel">市场行情</p>
        </div>
        <div class="m-body">
            <div class="market-tab">
            </div>
            <div class=" hcharts clearfix">
                <div class="hcharts-left tab-hv-box">
                    <div class="hcharts-cont tab-hv-cont">
                        <div style="height: 378px;width: 240px;display:inline-block;">
                            <button class="marketChartButton chartButtonLeftBorder" id="downupButton">
                                <div class="marketChartLabel"><img src="../../images/downup.svg">涨跌分布</div>
                                <div style="text-align: center">
                                    <span class="redLabel" style="margin-right: 20px"><span>上涨 : </span><span id="rise">1350支</span></span>
                                    <span class="greenLabel"><span>下跌 : </span><span id="decline">1479支</span></span>
                                </div>
                            </button>
                            <button class="marketChartButton " id="downupStopButton">
                                <div class="marketChartLabel"><img src="../../images/downup2.svg">涨跌停</div>
                                <div style="text-align: center">
                                    <span class="redLabel" style="margin-right: 20px"><span>涨停 : </span><span id="riseStop">43支</span></span>
                                    <span class="greenLabel"><span>跌停 : </span><span id="declineStop">8支</span></span>
                                </div>
                            </button>
                        </div>
                        <div id="chartContainer" style="height: 378px;width: 640px;display: inline-block"></div>
                    </div>
                </div>
                <div class="hcharts-right">
                    <p style="font-size: 20px;border-bottom: 1px solid #bbb">大盘评分</p>
                    <div>
                        <div id="pieContainer" style="height: 250px"></div>
                    </div>
                    <div style="font-size: 20px;text-align: center">股市有风险</div>
                    <div style="font-size: 20px;text-align: center">投资须谨慎</div>
                </div>
            </div>
            <div class="mt30 clearfix">

                <div class="flash-single flash-small flash-sec1">
                    <div>
                        <strong class="m-off" style="padding-left: 10px">沪深300</strong>
                    </div>
                    <!--*********************************************下面的div是用来存放echarts的*********************************************-->
                    <div class="price-box" id="hu" style="height: 230px;"></div>
                </div>
                <div class="flash-single flash-small flash-sec2">
                    <div>
                        <strong class="m-off" style="padding-left: 10px">创业板</strong>
                    </div>
                    <!--*********************************************下面的div是用来存放echarts的*********************************************-->
                    <div class="price-box" id="chuang" style="height: 230px;"></div>
                </div>
                <div class="flash-single flash-small">
                    <div>
                        <strong class="m-off" style="padding-left: 10px">中小板</strong>
                    </div>
                    <!--*********************************************下面的div是用来存放echarts的*********************************************-->
                    <div class="price-box" id="zhong" style="height: 230px;"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="m-box">
        <div>
            <p class="headLabel">个股行情</p>
        </div>
        <div class="m-body m-pager-box">
            <div class="table-tab">
                <button id="allStocksTab" class="allStockButtonClick">全部股票</button>
                <button id="hushen300Tab">沪深300</button>
                <button id="chuangyebanTab">创业板</button>
                <button id="zhongxiaobanTab">中小板</button>
            </div>
            <div id="maincont" data-fixedthead="true" style="background-color: white">
                <div>
                    <table class="table table-striped table-condensed" id="allStock">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>股票ID</th>
                            <th>股票名称</th>
                            <th>现价</th>
                            <th>涨跌幅</th>
                            <th>涨跌价</th>
                            <th>换手率</th>
                            <th>成交量</th>
                            <th>成交额</th>
                            <th>流通市值</th>
                            <th>市盈率</th>
                            <th>市净率</th>
                            <th>总市值</th>
                            <th>加自选</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            ArrayList<StockCurrentData> arrayList =(ArrayList<StockCurrentData>)session.getAttribute("allStocks");
                            ArrayList<Double> allVolume = (ArrayList<Double>)session.getAttribute("allStockVolume");
                            ArrayList<Double> allAmount = (ArrayList<Double>)session.getAttribute("allStockAmount");
                            ArrayList<Double> allChange = (ArrayList<Double>)session.getAttribute("allStockChange");
                            for(int j = 0; j < arrayList.size(); j++){
                        %>
                            <tr  class="disappear">
                                <td><%=j%></td>
                                <td><a href="/StockInfo/<%=arrayList.get(j).getCode()%>/toStockInfo.do"><%=arrayList.get(j).getCode()%></a></td>
                                <td><a href="/StockInfo/<%=arrayList.get(j).getCode()%>/toStockInfo.do" class="kLineHover" id="<%=arrayList.get(j).getCode()%>"><%=arrayList.get(j).getName()%></a></td>
                                <%if(arrayList.get(j).getChangepercent() >= 0){%>
                                    <td style="color: #C10D01"><%=arrayList.get(j).getTrade()%></td>
                                    <td style="color: #C10D01"><%=arrayList.get(j).getChangepercent()%>%</td>
                                    <td style="color: #C10D01"><%=allChange.get(j)%></td>
                                <%}else{%>
                                    <td style="color: #008000"><%=arrayList.get(j).getTrade()%></td>
                                    <td style="color: #008000"><%=arrayList.get(j).getChangepercent()%>%</td>
                                    <td style="color: #008000"><%=allChange.get(j)%></td>
                                <%}%>

                                <td><%=arrayList.get(j).getTurnoverratio()%>%</td>
                                <td><%=allVolume.get(j)%>万</td>
                                <td><%=allAmount.get(j)%>万</td>
                                <%--<td>${stock.value.}</td>--%>
                                <td><%=arrayList.get(j).getNmc()%>亿</td>
                                <td><%=arrayList.get(j).getPer()%></td>
                                <td><%=arrayList.get(j).getPb()%></td>
                                <td><%=arrayList.get(j).getMktcap()%>亿</td>
                                <td>
                                    <a href="javascript:void(0);" title="addStocks" class="j_addstock">
                                        <img src="../../images/addstock.svg">
                                    </a>
                                </td>
                            </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                    <table class="table table-striped table-condensed disappear" id="hushen300">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>股票ID</th>
                            <th>股票名称</th>
                            <th>现价</th>
                            <th>涨跌幅</th>
                            <th>涨跌价</th>
                            <th>换手率</th>
                            <th>成交量</th>
                            <th>成交额</th>
                            <th>流通市值</th>
                            <th>市盈率</th>
                            <th>市净率</th>
                            <th>总市值</th>
                            <th>加自选</th>
                        </tr>
                        </thead>
                        <%--<tbody>--%>
                        <%--<%--%>
                            <%--ArrayList<StockCurrentData> hushen300Lists =(ArrayList<StockCurrentData>)session.getAttribute("hushen300Lists");--%>
                            <%--ArrayList<Double> hushenVolume = (ArrayList<Double>)session.getAttribute("hushenVolume");--%>
                            <%--ArrayList<Double> hushenAmount = (ArrayList<Double>)session.getAttribute("hushenAmount");--%>
                            <%--ArrayList<Double> hushenChange = (ArrayList<Double>)session.getAttribute("hushenChange");--%>
                            <%--for(int j = 0; j < hushen300Lists.size(); j++){--%>
                        <%--%>--%>
                        <%--<tr  class="disappear">--%>
                            <%--<td><%=j%></td>--%>
                            <%--<td><a href="/StockInfo/<%=hushen300Lists.get(j).getCode()%>/toStockInfo.do"><%=hushen300Lists.get(j).getCode()%></a></td>--%>
                            <%--<td><a href="/StockInfo/<%=hushen300Lists.get(j).getCode()%>/toStockInfo.do" class="kLineHover" id="<%=hushen300Lists.get(j).getCode()%>"><%=hushen300Lists.get(j).getName()%></a></td>--%>
                            <%--<%if(hushen300Lists.get(j).getChangepercent() >= 0){%>--%>
                            <%--<td style="color: #C10D01"><%=hushen300Lists.get(j).getTrade()%></td>--%>
                            <%--<td style="color: #C10D01"><%=hushen300Lists.get(j).getChangepercent()%>%</td>--%>
                            <%--<td style="color: #C10D01"><%=hushenChange.get(j)%></td>--%>
                            <%--<%}else{%>--%>
                            <%--<td style="color: #008000"><%=hushen300Lists.get(j).getTrade()%></td>--%>
                            <%--<td style="color: #008000"><%=hushen300Lists.get(j).getChangepercent()%>%</td>--%>
                            <%--<td style="color: #008000"><%=hushenChange.get(j)%></td>--%>
                            <%--<%}%>--%>

                            <%--<td><%=hushen300Lists.get(j).getTurnoverratio()%>%</td>--%>
                            <%--<td><%=hushenVolume.get(j)%>万</td>--%>
                            <%--<td><%=hushenAmount.get(j)%>万</td>--%>
                            <%--&lt;%&ndash;<td>${stock.value.}</td>&ndash;%&gt;--%>
                            <%--<td><%=hushen300Lists.get(j).getNmc()%>亿</td>--%>
                            <%--<td><%=hushen300Lists.get(j).getPer()%></td>--%>
                            <%--<td><%=hushen300Lists.get(j).getPb()%></td>--%>
                            <%--<td><%=hushen300Lists.get(j).getMktcap()%>亿</td>--%>
                            <%--<td>--%>
                                <%--<a href="javascript:void(0);" title="addStocks" class="j_addstock">--%>
                                    <%--<img src="../../images/addstock.svg">--%>
                                <%--</a>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                        <%--<%--%>
                            <%--}--%>
                        <%--%>--%>
                        <%--</tbody>--%>
                    </table>
                    <table class="table table-striped table-condensed disappear" id="chuangyeban">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>股票ID</th>
                            <th>股票名称</th>
                            <th>现价</th>
                            <th>涨跌幅</th>
                            <th>涨跌价</th>
                            <th>换手率</th>
                            <th>成交量</th>
                            <th>成交额</th>
                            <th>流通市值</th>
                            <th>市盈率</th>
                            <th>市净率</th>
                            <th>总市值</th>
                            <th>加自选</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            ArrayList<StockCurrentData> chuangyebanLists =(ArrayList<StockCurrentData>)session.getAttribute("chuangyebanLists");
                            ArrayList<Double> chuangyeVolume = (ArrayList<Double>)session.getAttribute("chuangyeVolume");
                            ArrayList<Double> chuangyeAmount = (ArrayList<Double>)session.getAttribute("chuangyeAmount");
                            ArrayList<Double> chuangyeChange = (ArrayList<Double>)session.getAttribute("chuangyeChange");
                            for(int j = 0; j < chuangyebanLists.size(); j++){
                        %>
                        <tr  class="disappear">
                            <td><%=j%></td>
                            <td><a href="/StockInfo/<%=chuangyebanLists.get(j).getCode()%>/toStockInfo.do"><%=chuangyebanLists.get(j).getCode()%></a></td>
                            <td><a href="/StockInfo/<%=chuangyebanLists.get(j).getCode()%>/toStockInfo.do" class="kLineHover" id="<%=chuangyebanLists.get(j).getCode()%>"><%=chuangyebanLists.get(j).getName()%></a></td>
                            <%if(chuangyebanLists.get(j).getChangepercent() >= 0){%>
                            <td style="color: #C10D01"><%=chuangyebanLists.get(j).getTrade()%></td>
                            <td style="color: #C10D01"><%=chuangyebanLists.get(j).getChangepercent()%>%</td>
                            <td style="color: #C10D01"><%=chuangyeChange.get(j)%></td>
                            <%}else{%>
                            <td style="color: #008000"><%=chuangyebanLists.get(j).getTrade()%></td>
                            <td style="color: #008000"><%=chuangyebanLists.get(j).getChangepercent()%>%</td>
                            <td style="color: #008000"><%=chuangyeChange.get(j)%></td>
                            <%}%>

                            <td><%=chuangyebanLists.get(j).getTurnoverratio()%>%</td>
                            <td><%=chuangyeVolume.get(j)%>万</td>
                            <td><%=chuangyeAmount.get(j)%>万</td>
                            <%--<td>${stock.value.}</td>--%>
                            <td><%=chuangyebanLists.get(j).getNmc()%>亿</td>
                            <td><%=chuangyebanLists.get(j).getPer()%></td>
                            <td><%=chuangyebanLists.get(j).getPb()%></td>
                            <td><%=chuangyebanLists.get(j).getMktcap()%>亿</td>
                            <td>
                                <a href="javascript:void(0);" title="addStocks" class="j_addstock">
                                    <img src="../../images/addstock.svg">
                                </a>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                    <table class="table table-striped table-condensed disappear" id="zhongxiaoban">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>股票ID</th>
                            <th>股票名称</th>
                            <th>现价</th>
                            <th>涨跌幅</th>
                            <th>涨跌价</th>
                            <th>换手率</th>
                            <th>成交量</th>
                            <th>成交额</th>
                            <th>流通市值</th>
                            <th>市盈率</th>
                            <th>市净率</th>
                            <th>总市值</th>
                            <th>加自选</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            ArrayList<StockCurrentData> zhongxiaobanLists =(ArrayList<StockCurrentData>)session.getAttribute("zhongxiaobanLists");
                            ArrayList<Double> zhongxiaoVolume = (ArrayList<Double>)session.getAttribute("zhongxiaoVolume");
                            ArrayList<Double> zhongxiaoAmount = (ArrayList<Double>)session.getAttribute("zhongxiaoAmount");
                            ArrayList<Double> zhongxiaoChange = (ArrayList<Double>)session.getAttribute("zhongxiaoChange");
                            for(int j = 0; j < zhongxiaobanLists.size(); j++){
                        %>
                        <tr class="disappear">
                            <td><%=j%></td>
                            <td><a href="/StockInfo/<%=zhongxiaobanLists.get(j).getCode()%>/toStockInfo.do"><%=zhongxiaobanLists.get(j).getCode()%></a></td>
                            <td><a href="/StockInfo/<%=zhongxiaobanLists.get(j).getCode()%>/toStockInfo.do" class="kLineHover" id="<%=zhongxiaobanLists.get(j).getCode()%>"><%=zhongxiaobanLists.get(j).getName()%></a></td>
                            <%if(zhongxiaobanLists.get(j).getChangepercent() >= 0){%>
                            <td style="color: #C10D01"><%=zhongxiaobanLists.get(j).getTrade()%></td>
                            <td style="color: #C10D01"><%=zhongxiaobanLists.get(j).getChangepercent()%>%</td>
                            <td style="color: #C10D01"><%=zhongxiaoChange.get(j)%></td>
                            <%}else{%>
                            <td style="color: #008000"><%=zhongxiaobanLists.get(j).getTrade()%></td>
                            <td style="color: #008000"><%=zhongxiaobanLists.get(j).getChangepercent()%>%</td>
                            <td style="color: #008000"><%=zhongxiaoChange.get(j)%></td>
                            <%}%>

                            <td><%=zhongxiaobanLists.get(j).getTurnoverratio()%>%</td>
                            <td><%=zhongxiaoVolume.get(j)%>万</td>
                            <td><%=zhongxiaoAmount.get(j)%>万</td>
                            <%--<td>${stock.value.}</td>--%>
                            <td><%=zhongxiaobanLists.get(j).getNmc()%>亿</td>
                            <td><%=zhongxiaobanLists.get(j).getPer()%></td>
                            <td><%=zhongxiaobanLists.get(j).getPb()%></td>
                            <td><%=zhongxiaobanLists.get(j).getMktcap()%>亿</td>
                            <td>
                                <a href="javascript:void(0);" title="addStocks" class="j_addstock">
                                    <img src="../../images/addstock.svg">
                                </a>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <div style="text-align: center">
                    <ul class="pagination" id="pagination2"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="kLineHolder disappear">
    <div class="entry-trangle-left"><!--本Div只来实现三角形，无其他作用--></div>
    <div style="display: inline-block">
        <div id="kLinePreview">
            <div>
                <ul>
                    <li id="previewStock" style="color: #777; font-size: 19px"></li>
                    <li id="previewTime" class="blueBackground"><button>分时</button></li>
                    <li id="previewDayLine"><button>日K</button></li>
                    <li id="previewWeekLine"><button>周K</button></li>
                    <li id="previewMonthLine"><button>月K</button></li>
                </ul>
            </div>
            <div>
                <div id="preview" style="height: 280pt;width: 350pt"></div>
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
<%--<div id="attentionDialog" class="fadeIn">--%>
    <%--<h2>提示</h2>--%>
    <%--<h4>同时加入自选股？</h4>--%>
    <%--<div style="width: 50%;margin: 5px auto;">--%>
        <%--<input type="button" value="确认" id="confirm-button">--%>
        <%--<input type="button" value="取消" style="background-color: #8c8c8c" id="cancel-button">--%>
    <%--</div>--%>
<%--</div>--%>

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

<div id="toaster_close">
    <div id="toaster">
        <div id="pic_div" class="green_pic"></div>
        <div id="remind" class="green_word">提示消息</div>
    </div>
</div>
</body>
<script type="text/javascript" src="../../charts/kLineChart.js"></script>
<%--<script type="text/javascript" src="../../charts/areaChart.js"></script>--%>
<script type="text/javascript" src="../../charts/marketTimeChart.js"></script>
<script type="text/javascript" src="../../charts/stockTimeLineChart.js"></script>
<script type="text/javascript" src="../../charts/marketBarChart.js"></script>
<script type="text/javascript" src="../../charts/marketLineChart.js"></script>
<script type="text/javascript" src="../../charts/marketPieChart.js"></script>
<script type="text/javascript" src="../../js/myJS/DialogJS.js"></script>
<script src="../../js/myJS/MarketTable.js" type="text/javascript"></script>
<script src="../../js/myJS/MarketJS.js" type="text/javascript"></script>
</html>
