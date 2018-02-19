/**
 * Created by xiaoJun on 2017/5/25.
 */

function areaChart(mychart,time,price,volume) {

    window.onresize = mychart.resize;

    option = {
        backgroundColor: '#fff',
        animation: true,
        color : ["#C23531"],
        grid: [
            {
                top: '2%',
                left: '8%',
                right: '8%',
                height: '55%'
            },
            {
                left: '8%',
                right: '8%',
                top: '68%',
                height: '16%'
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
            },
            {
                type: 'category',
                gridIndex: 1,
                data: time,
                scale: true,
                boundaryGap : false,
                axisLine: {onZero: false},
                axisTick: {show: false},
                splitLine: {show: false},
                axisLabel: {show: false},
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
            },
            {
                scale: true,
                gridIndex: 1,
                splitNumber: 2,
                axisLabel: {show: false},
                axisLine: {show: false},
                axisTick: {show: false},
                splitLine: {show: false}
            }
        ],
        dataZoom: [
            {
                type: 'inside',
                xAxisIndex: [0, 1],
                start: 0,
                end: 100
            },
            {
                show: true,
                xAxisIndex: [0, 1],
                type: 'slider',
                top: '90%',
                start: 0,
                end: 100
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
            },
            {
                name: 'Volume',
                type: 'bar',
                xAxisIndex: 1,
                yAxisIndex: 1,
                data: volume,
                itemStyle: {
                    normal: {
                        color: '#3488ff'
                    }
                }
            }
        ]
    };

    mychart.setOption(option,true);
}

