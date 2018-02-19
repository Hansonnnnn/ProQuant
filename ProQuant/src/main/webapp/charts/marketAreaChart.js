/**
 * Created by xiaoJun on 2017/5/25.
 */

// $(function () {
//     var xData = ['09:00','09:01','09:02','09:03','09:04','09:05','09:06','09:07','09:08','09:09','09:10','09:11','09:12','09:13','09:14','09:15'
//         ,'09:16','09:17','09:18','09:19','09:20','09:21','09:22','09:23','09:24','09:25','09:26','09:27','09:28','09:29','09:30','09:31','09:32'
//         ,'09:33','09:34','09:35','09:36','09:37','09:38','09:39','09:40','09:41','09:42','09:43','09:44','09:45','09:46','09:47','09:48','09:49'
//         ,'09:50','09:51','09:52','09:53','09:54','09:55','09:56','09:57','09:58','09:59'];
//         // ,'10:00','10:01','10:02','10:03','10:04','10:05','10:06'
//         // ,'10:07','10:08','10:09','10:10','10:11','10:12','10:13','10:14','10:15','10:16','10:17','10:18','10:19','10:20','10:21','10:22','10:23'
//         // ,'10:24','10:25','10:26','10:27','10:28','10:29','10:30','10:31','10:32','10:33','10:34','10:35','10:36','10:37','10:38','10:39','10:40'];
//
//     var yData1 = [];
//     for(var i = 0; i < 60; i++){
//         yData1[i] = Math.random()*40+60;
//     }
//     var mychart1 = echarts.init(document.getElementById("hu"));
//     areaChart(mychart1,xData,yData1);
//
//
//     var yData2 = [];
//     for(var i = 0; i < 60; i++){
//         yData2[i] = Math.random()*40+60;
//     }
//     var mychart2 = echarts.init(document.getElementById("chuang"));
//     areaChart(mychart2,xData,yData2);
//
//     var yData3 = [];
//     for(var i = 0; i < 60; i++){
//         yData3[i] = Math.random()*40+60;
//     }
//     var mychart3 = echarts.init(document.getElementById("zhong"));
//     areaChart(mychart3,xData,yData3);
// })


/**
 * Created by xiaoJun on 2017/5/25.
 */

function marketAreaChart(mychart,time,price) {

    window.onresize = mychart.resize;

    option = {
        backgroundColor: '#fff',
        animation: true,
        color : ["#528FCC"],
        brush: {
            xAxisIndex: 'all',
            brushLink: 'all',
            outOfBrush: {
                colorAlpha: 0.1
            }
        },
        grid: [
            {
                top: '2%',
                left: '8%',
                right: '8%',
                height: '55%'
            }
        ],
        xAxis: [
            {
                type: 'category',
                data: time,
                scale: true,
                boundaryGap : false,
                axisLine: {onZero: false},
                splitLine: {show: false},
                splitNumber: 20,
                min: 'dataMin',
                max: 'dataMax'
            }
        ],
        yAxis: [
            {
                scale: true,
                splitArea: {
                    show: true
                }
            }
        ],
        series: [
            {
                name: 'k线',
                type: 'line',
                data: price,
                showSymbol : false,
                lineStyle: {
                    normal : {
                        color: "#528FCC",
                        width: 0.5
                    }
                },
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: 'default',
                            color: '#CFE2F3'
                        }
                    }
                },
            }
        ]
    };

    mychart.setOption(option,true);
}

