/**
 * Created by xiaoJun on 2017/6/11.
 */

var colorArray = ["#AED4C2","#DDA490","#D0616B","#CFE2F3","#FE9616"];
var lineColor = ["#91C7A1","#618B55","#62A1A8","#c646a2","#52BBD4"];

function stackChart(chart,stockNameArray,xData,yData) {

    window.onresize = chart.onresize;

    option = {

        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        legend: {
            data:stockNameArray,
            color:lineColor
        },
        color:lineColor,
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : xData
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        // series : [
        //     {
        //         name:'邮件营销',
        //         type:'line',
        //         stack: '总量',
        //         areaStyle: {normal: {}},
        //         data:[120, 132, 101, 134, 90, 230, 210]
        //     },
        //     {
        //         name:'联盟广告',
        //         type:'line',
        //         stack: '总量',
        //         areaStyle: {normal: {}},
        //         data:[220, 182, 191, 234, 290, 330, 310]
        //     },
        //     {
        //         name:'视频广告',
        //         type:'line',
        //         stack: '总量',
        //         areaStyle: {normal: {}},
        //         data:[150, 232, 201, 154, 190, 330, 410]
        //     },
        //     {
        //         name:'直接访问',
        //         type:'line',
        //         stack: '总量',
        //         areaStyle: {normal: {}},
        //         data:[320, 332, 301, 334, 390, 330, 320]
        //     },
        //     {
        //         name:'搜索引擎',
        //         type:'line',
        //         stack: '总量',
        //         label: {
        //             normal: {
        //                 show: true,
        //                 position: 'top'
        //             }
        //         },
        //         areaStyle: {normal: {}},
        //         data:[820, 932, 901, 934, 1290, 1330, 1320]
        //     }
        // ]
    };

    var seriesData = [];
    for(var i = 0; i < stockNameArray.length; i++){
        var items = {
            name:stockNameArray[i],
            type:'line',
            data:yData[i],
            smooth:true,
            stack : "总量",
            areaStyle: {
                normal:{
                    color:colorArray[i]
                }},
            lineStyle:{
                normal:{
                    areaStyle:{
                        type:"default"
                    },
                    // // width:1,
                    color:lineColor[i]
                }
            }
        };
        seriesData.push(items);
    };
    option.series = seriesData;
    chart.setOption(option,true);
}