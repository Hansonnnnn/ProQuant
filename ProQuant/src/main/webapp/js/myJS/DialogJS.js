/**
 * Created by xiezhenyu on 2017/5/31.
 */

//带三角提示框使用方法，导入DialogJS.js和Dialog.css
//在需要使用该提示框的html文件中加入以下三行代码
// <div id="entry"  class="entry">
//     <div class="entry-trangle-left"><!--本Div只来实现三角形，无其他作用--></div>
// </div>
//在需要使用该提示框的组件上添加类"dialog-hover"


//获取元素的绝对位置$("#elem").offset().top；$("#elem").offset().left
//获取元素的相对位置$("#elem").position().top； $("#elem").position().left
//$("#entry").animate({left:posX});  $("#entry").animate({top:posY});实现元素滑动效果
//获取元素高度和宽度$(this).height();$(this).width()

$(function () {
    $(".dialog-hover").mouseover(function (event) {
        showPromptDialog($(this));
    });
    $(".dialog-hover").mouseout(function () {
        $("#entry")[0].style.display = "none";
    })
});

function showPromptDialog(jq_ele) {
    var dia = $(".buy-page-holder")[0];
    var offsetX = jq_ele.offset().left,
        offsetY = jq_ele.offset().top,
        width = jq_ele.width(),
        height = jq_ele.height();
    dia.style.left = offsetX + (width / 2) -20 +  "px";
    dia.style.top = offsetY + height+ 20 + "px";
    // dia.style.display = "block";
}