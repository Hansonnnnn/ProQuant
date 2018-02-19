/**
 * Created by xiaoJun on 2017/6/14.
 */

function marketTimeChart(mychart,time,price) {

    window.onresize = mychart.resize;

    option = {
        backgroundColor: '#fff',
        animation: true,
        color : ["#528FCC"],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: [
            {
                show: false,
                top: '2%',
                left: '15%',
                right: '5%',
                height: '80%'
            }
        ],
        xAxis: [
            {
                type: 'category',
                data: time,
                scale: true,
                boundaryGap : false,
                axisLine: {
                    onZero: false,
                    show: false
                },
                axisTick: {
                    show: false
                },
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
                },
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
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