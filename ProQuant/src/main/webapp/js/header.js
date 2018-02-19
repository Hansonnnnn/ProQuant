/**
 * Created by xiaoJun on 2017/5/13.
 */
var username;

var notShow = false;

$(function () {
    $("#forgetPassword").click(function () {
        alert("请联系客服找回密码，电话：15905199008.");
    })

    headSearch();

    //当用户点击登录按钮时，调用登录方法
    //当用户点击注册按钮时，调用注册方法，并且在注册之后要进入到登录状态
    //1.判断用户是要登录还是注册
    $("#submitButton").click(function () {
        //如果是登录，调用登录模块
        if($(this).text() === "登录"){
            loginPart.signIn.init();
            if(hasLogin){
                $("#loginModal").modal("hide");
            }
        }
        //如果是注册，调用注册模块
        else if($(this).text() === "注册" ){
            loginPart.signUp.init();
            $("#loginModal").modal("hide");
        }
    });

    // testHasLogin();

    //设置搜索框未获得焦点时长度缩小，获得焦点时长度变大
    $("#searchInput").blur(function (){
        if($(this).val() == "" || $(this).val() == null){
            $(this).attr("placeholder","搜索");
            $(this).animate({width:"35px"},400);
        }
    }).focus(function () {
        $(this).animate({width:"120px"},400);
        $(this).attr("placeholder","请输入股票代码");
    });

    //为导航栏的点击添加监听
    $("#navigation li").click(function () {
        $(this).addClass("active");
        $(this).siblings().removeClass("active");
    });

    // document.querySelector('.cont_centrar').className = "cont_centrar cent_active";

    //点击登陆按钮，显示登陆弹出框
    //如果已经登录，界面跳转到用户中心
    $(".loginButtonHolder").click(function () {
        if($(".loginButtonHolder").children().eq(0).children().eq(0).text() !== "未登录"){
            window.location.href = "/user/toUserCenter.do";
        }else{
            $("#loginModal").modal("show");
        }

    });



    //为用户名输入框绑定键盘监听
    $("#userNameField").bind('input porpertychange',function(){
        if($("#submitButton").text() === "登录"){
            //如果是在登录状态下才自动补全密码，在注册状态下若有与已输入部分匹配的用户名，不应该补全
            var tempUsername = $(this).val();
            var tempPassword = decodeBase64($.cookie(tempUsername));
            if(tempPassword !== ""){
                $("#passwordField").val(tempPassword);
            }
        }
    })



})

/**
 * 滑出提示框
 * @param index 0表示成功，1表示失败，2表示提醒
 * @param remindness 提示的文字
 */
function slidein(index, remindness) {

    var pics = ["green_pic", "red_pic", "yellow_pic"];
    var words = ["green_word", "red_word", "yellow_word"];

    document.getElementById("pic_div").setAttribute("class", pics[index]);
    document.getElementById("remind").setAttribute("class", words[index]);
    document.getElementById("remind").innerHTML = remindness;

    if (remindness.length < 8) {
        document.getElementById("remind").style.marginLeft = "65px";
    } else if(remindness.length > 8) {
        document.getElementById("remind").style.fontSize = "15px";
        document.getElementById("remind").style.marginLeft = "55px";
    }

    window.location.href = '#toaster';
    setTimeout("window.location.href='#toaster_close'", 1500);
}

function sign_up(){
    var inputs = document.querySelectorAll('.input_form_sign');
    document.querySelectorAll('.ul_tabs > li')[0].className="";
    document.querySelectorAll('.ul_tabs > li')[1].className="active";

    for(var i = 0; i < inputs.length ; i++  ) {
        if(i == 2  ){

        }else{
            document.querySelectorAll('.input_form_sign')[i].className = "input_form_sign d_block";
        }
    }

    setTimeout( function(){
        for(var d = 0; d < inputs.length ; d++  ) {
            document.querySelectorAll('.input_form_sign')[d].className = "input_form_sign d_block active_inp";
        }
    },100 );

    document.querySelector('.link_forgot_pass').style.opacity = "0";
    document.querySelector('.link_forgot_pass').style.top = "-5px";
    document.querySelector('.btn_sign').innerHTML = "注册";
    setTimeout(function(){
        document.querySelector('.terms_and_cons').style.opacity = "1";
        document.querySelector('.terms_and_cons').style.top = "5px";
    },500);
    setTimeout(function(){
        document.querySelector('.link_forgot_pass').className = "link_forgot_pass d_none";
        document.querySelector('.terms_and_cons').className = "terms_and_cons d_block";
    },200);

}



function sign_in(){
    var inputs = document.querySelectorAll('.input_form_sign');
    document.querySelectorAll('.ul_tabs > li')[0].className = "active";
    document.querySelectorAll('.ul_tabs > li')[1].className = "";

    for(var i = 0; i < inputs.length ; i++  ) {
        switch(i) {
            case 1:
                console.log(inputs[i].name);
                break;
            case 2:
                console.log(inputs[i].name);
            default:
                document.querySelectorAll('.input_form_sign')[i].className = "input_form_sign d_block";
        }
    }

    setTimeout( function(){
        for(var d = 0; d < inputs.length ; d++  ) {
            switch(d) {
                case 1:
                    break;
                case 2:

                default:
                    document.querySelectorAll('.input_form_sign')[d].className = "input_form_sign d_block";
                    document.querySelectorAll('.input_form_sign')[2].className = "input_form_sign d_block active_inp";
            }
        }
    },100 );

    document.querySelector('.terms_and_cons').style.opacity = "0";
    document.querySelector('.terms_and_cons').style.top = "-5px";

    setTimeout(function(){
        document.querySelector('.terms_and_cons').className = "terms_and_cons d_none";
        document.querySelector('.link_forgot_pass').className = "link_forgot_pass d_block";

    },300);

    setTimeout(function(){

        document.querySelector('.link_forgot_pass').style.opacity = "1";
        document.querySelector('.link_forgot_pass').style.top = "5px";
        for(var d = 0; d < inputs.length ; d++  ) {
            switch(d) {
                case 1:
                    break;
                case 2:
                    break;
                default:
                    document.querySelectorAll('.input_form_sign')[d].className = "input_form_sign";
            }
        }
    },700);
    document.querySelector('.btn_sign').innerHTML = "登录";
}


//实现验证登录和登出、注册的逻辑

var loginPart = {
    validatePassword : function (userName, password) {
        $.ajax({
            type : "post",
            async : false,
            url : "/Login/validate.do",
            data : {
                "userName" : userName,
                "password" : password
            },
            dataType : "json",
            success : function (result) {
                // alert(result.logInSuccess);
                if(result.logInSuccess){
                    username  = userName;
                    hasLogin = true;
                    // alert("要改变标签内容");
                    $(".loginButtonHolder").html('<button id="loginButton"><i class="fa fa-user-circle" aria-hidden="true"></i></button>').
                    children().eq(0).children().eq(0).text(username);
                    // alert($(".loginButtonHolder").html());
                    // alert("已改变标签内容");
                    password = encodeBase64(password);
                    $.cookie(userName, password, { expires: 7 });
                    return true;
                }else{
                    alert("登录失败");
                    return false;
                }
            },
            error : function () {
                alert("验证用户名和密码错误！");
                return false;
            }
        })
        return false;
    },
    //如果登录成功，进行全局登录状态的设置，和头像的切换等等操作
    loginOperation : function () {
    },
    signup : function (name, userName, password, confirmPassword) {
        $.ajax({
            type : "post",
            url : "/Login/signUp.do",
            async : false,
            data : {
                "name" : name,
                "username" : userName,
                "password" : password,
                "confirmPassword" : confirmPassword
            },
            dataType : "json",
            success : function (result) {
                //如果注册成功，调用登录模块
                if(result.signupSuccess){
                    slidein(0,"注册成功");
                    loginPart.validatePassword(userName, password);
                }else{
                    slidein(1,"该用户名已被注册!")
                }
            },
            error : function () {
                slidein(1,"注册失败，服务器访问错误.#260");
            }
        })
    },
    signIn : {
        //登录界面初始化
        init : function () {
            // var userName = $.cookie('userName');
            //逻辑：
            //判断用户是否登录信息填写完整
            var userName = $("#userNameField").val();
            var password = $("#passwordField").val();
            // alert(userName);
            // alert(password);
            if(userName !== "" && password !== ""){
                if(loginPart.validatePassword(userName, password)){
                    $(".loginButtonHolder").html('<button id="loginButton"><i class="fa fa-user-circle" aria-hidden="true"></i></button>').
                    children().eq(0).children().eq(0).text(username);
                }
            }else{
                slidein(1, "请输入完整账号和密码！")
            }

        }
    },
    signUp : {
        init : function () {
            var name = $("#nameField").val();
            var userName = $("#userNameField").val();
            var password = $("#passwordField").val();
            var confirmPassword = $("#confirmPasswordField").val();
            //判断用户是否注册信息填写完整
            if(name !== ""&&userName !== ""&&password !== ""&&confirmPassword !== ""){
                //验证"验证密码"和密码是否一致
                if(password !== confirmPassword){
                    slidein(1,"两次输入的密码不一致，请确认后再输入。");
                }else{
                    loginPart.signup(name, userName, password, confirmPassword);
                }

            }else{
                slidein(1, "请输入完整注册信息！")
            }
        }
    }
}

// var loginButtonHtmlText = "<button id='loginButton'><i class='fa fa-user-circle' aria-hidden='true' onclick='showModel()'></i>未登录</button>";
// var logoutButtonHtmlText = "<button id='logoutButton'><i class='fa fa-user-circle' aria-hidden='true' onclick='logout()'></i>登出</button>";

function showModel() {
    $("#loginModal").modal("show");
}
function logout() {
    $.ajax({
        type : "get",
        url : "/Login/logout.do",
        async : false,
        dataType : "json",
        success : function (result) {
            if(result.logoutResult){
                // $("#loginButtonHolder").html(loginButtonHtmlText);
                $(".loginButtonHolder").html('<button id="loginButton"><i class="fa fa-user-circle" aria-hidden="true"></i></button>').
                children().eq(0).children().eq(0).text("未登录");
                window.location.href = "/HomePage/ToHomePage.do";
                hasLogin = false;
            }
        },
        error : function () {
            alert("Error! header.js line 295.")
        }
    })
}

function testHasLogin() {
    $.ajax({
        type : "get",
        url : "/Login/testHasLogin.do",
        dataType : "json",
        success : function (result) {
            // alert("testHasLogin()" + result);
            if(result.hasLogin){
                // $("#loginButtonHolder").html(logoutButtonHtmlText);
                hasLogin = true;
            }else{
                hasLogin = false;
            }
        },
        error : function () {
            // alert("testHasLogin()" + " error" );
        }
    })
    return hasLogin;
}


function getCurrentUsername() {
    $.ajax({
        type : "get",
        url : "/Login/getCurrentUsername.do",
        dateType : "json",
        success : function (result) {
            // alert("获取用户名成功");
            username = result.username;
        },
        error : function () {
            alert("获取当前用户出错header.js line 329");
        }
    })
    return username;
}


//加密方法。没有过滤首尾空格，即没有trim.
//加密可以加密N次，对应解密N次就可以获取明文
function encodeBase64(mingwen,times){
    var code="";
    var num=1;
    if(typeof times=='undefined'||times==null||times==""){
        num=1;
    }else{
        var vt=times+"";
        num=parseInt(vt);
    }

    if(typeof mingwen=='undefined'||mingwen==null||mingwen==""){

    }else{
        $.base64.utf8encode = true;
        code=mingwen;
        for(var i=0;i<num;i++){
            code=$.base64.btoa(code);
        }
    }
    return code;
}


//解密方法。没有过滤首尾空格，即没有trim
//加密可以加密N次，对应解密N次就可以获取明文
function decodeBase64(mi,times){
    var mingwen="";
    var num=1;
    if(typeof times=='undefined'||times==null||times==""){
        num=1;
    }else{
        var vt=times+"";
        num=parseInt(vt);
    }


    if(typeof mi=='undefined'||mi==null||mi==""){

    }else{
        $.base64.utf8encode = true;
        mingwen=mi;
        for(var i=0;i<num;i++){
            mingwen=$.base64.atob(mingwen);
        }
    }
    return mingwen;
}

//添加到自选股
function addToHistoryRecord(username, code, name) {
    var codeAndName = code + ";" + name;
    $.cookie(username, codeAndName, {expire: 7});
}

function headSearch() {
    $("#headHolder tr a").click(function () {
        notShow = false;

        // var name = $(this).attr("id");
        // if(isRepeat(name)){
        //     setTimeout(slidein(2,"股票已在对比列表中"),1800);
        // }else{
        //     if(stockNameArray.length < 5){
        //         $("#holder").addClass("displayNone");
        //         $("#searchTextField").val("");
        //         stockNameArray.push(name);
        //         addNodeToStockContainer(name);
        //         isShow = false;
        //         console.log(stockNameArray);
        //     }else{
        //         setTimeout(slidein(2,"最多对比五只股票"),1800);
        //     }
        // }
    });

    window.onresize = function () {
        if(!$("#headContainer").hasClass("displayNone")){
            showPromptDialogs($("#searchInput"));
        }
    }

    //点击除买入提示框以外的提示框时，关闭提示框
    $(document).click(function (e) {
        // e.stopPropagation();
        e = window.event || e;
        var target = e.srcElement || e.target;
        if(notShow && (!$(target).is("#headContainer, #headContainer *"))){
            $("#headContainer").addClass("displayNone");
            notShow= false;
        }
    });


    $("#searchInput").change(function () {
        $("#headContainer").addClass("displayNone");
        for(var i = 0; i < 20; i++){
            $("#headHolder tr").eq(i).text("");
            $("#headHolder tr").eq(i).addClass("displayNone");
        }
        $.ajax({
            type: "post",
            async: true,//异步执行
            url: "/StockCompare/compareSearch.do",
            dataType: "json",
            data : {"nameOrCode" : $(this).val()},
            success : function (result) {
                console.log(result);
                for(var i = 0; i < result.length; i++){
                    var $a = $("<a></a>")
                    $a.text(result[i].name+"("+result[i].code+")");
                    $a.attr("href","/StockInfo/"+result[i].code+"/toStockInfo.do");
                    $("#headHolder tr").eq(i).append($a);
                    $("#headHolder tr").eq(i).removeClass("displayNone");
                }
                showPromptDialogs($("#searchInput"));
                $("#headContainer").removeClass("displayNone");
                notShow = true;
            },
            error: function () {
            }
        })
    });
}

function showPromptDialogs(jq_ele) {
    var dia = $("#headContainer")[0];
    var offsetX = jq_ele.offset().left,
        offsetY = jq_ele.offset().top,
        width = jq_ele.width(),
        height = jq_ele.height();
    dia.style.left = offsetX +  "px";
    dia.style.top = offsetY + height+ 20 + "px";
}