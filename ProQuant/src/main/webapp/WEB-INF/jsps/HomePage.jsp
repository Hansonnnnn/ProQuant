<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2017/6/5
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <%@include file="common/head.jsp"%>>
    <link rel="stylesheet" href="../../css/home.css">
    <link rel="stylesheet" href="../../css/myCSS/DialogCSS.css">
    <script src="../../js/homePage.js" type="text/javascript"></script>
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
            <li class="active"><a href="/HomePage/ToHomePage.do">首页</a></li>
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

<div class="container-fluid" style="height: 600px;background-color: #407FCE">
    <div class="row" style="margin-top: 100px;width: 100%;text-align: center;padding: 150px">
        <!--<div class="col-sm-7" id="animation">-->
        <!--&lt;!&ndash;<canvas id="c" width="600" height="600" style="position: absolute;top: 100px;left: 0;top: 0"></canvas>&ndash;&gt;-->
        <!--</div>-->
        <!--<div class="col-sm-1"></div>-->
        <!--<div class="col-sm-4" style="background-color:lavenderblush;">欢迎来到ProQuant</div>-->
        <!--<div class="col-sm-1"></div>-->
        <div>
            <canvas id="nokey" style="position: absolute;z-index: 0;width: 800px;height: 500px;left: 0;top: 0;"></canvas>
            <svg style="display: inline-block;" version="1.1"
                 xmlns="http://www.w3.org/2000/svg"
                 x="0px" y="0px" width="200px" viewBox="0 0 48 34" enable-background="new 0 0 48 34" xml:space="preserve">
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
            <div class="homeTxt">欢迎来到ProQuant</div>
            <div class="homeTxt">获取第一手股票资讯</div>
        </div>
    </div>
</div>

<!--功能简介start-->
<div>
    <div id="funcIntro" style="text-align: center">
        <div class="intro" id="marketPart">
            <img src="../../images/market.svg">
            <div class="introTitle">大盘数据</div>
            <div class="introTxt">获取最新的市场信息</div>
        </div>
        <div class="introGap"></div>
        <div class="intro" id="strategyPart">
            <img src="../../images/create.svg">
            <div class="introTitle">使用策略</div>
            <div class="introTxt">最大化提升投资收益</div>
        </div>
        <div class="introGap"></div>
        <div class="intro" id="comparePart">
            <img src="../../images/compare.svg">
            <div class="introTitle">股票对比</div>
            <div class="introTxt">全方位比较股票信息</div>
        </div>
    </div>
</div>
<!--功能简介end-->

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

<!--&lt;!&ndash;滑出提示框start&ndash;&gt;-->
<!--<div id="sliderTip">-->
<!--<div>-->
<!--<span style="border-right: 2px solid #528FCC"><i class="fa fa-exclamation-circle fa-2x" aria-hidden="true"></i></span>-->
<!--<span>提示消息</span>-->
<!--</div>-->
<!--</div>-->
<!--&lt;!&ndash;滑出提示框end&ndash;&gt;-->
<div id="toaster_close">
    <div id="toaster">
        <div id="pic_div" class="green_pic"></div>
        <div id="remind" class="green_word">提示消息</div>
    </div>
</div>
</body>
<script src="../../js/animation.js"></script>
</html>

