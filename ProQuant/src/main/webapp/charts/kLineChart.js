/**
 * 绘制个股信息K线图
 * @param mycharts  K线图容器
 * @param type   K线图的类型，0为日K线，1为周K线，2为月K线
 */

function kLine(mycharts,type,data) {
    window.onresize = mycharts.resize;
    // var requestUrl = "";
    var start = 0;
    if(type == 0){
        // requestUrl = "/StockInfo/getStockDayKLine.do";
        start = 85;
    }else if(type == 1){
        // requestUrl = "/StockInfo/getStockWeekKLine.do";
        start = 70;
    }else{
        // requestUrl = "/StockInfo/getStockMonthKLine.do"
        start = 55;
    }

    dealOption(mycharts,data,start);

    // $.ajax({
    //     type: "post",
    //     async: true,  //异步执行
    //     url: requestUrl,
    //     dataType: "json",
    //     success: function (result) {
    //         if (result) {
    //             console.log(result);
    //             console.log(result[0]);
    //             console.log(result[1]);
    //         }
    //     },
    //     error : function () {
    //         alert("失败咯");
    //     }
    // });


}


function dealOption(mycharts,result,start) {
    option = {
        backgroundColor: '#fff',
        animation: true,
        legend: {
            data: ['k线', 'MA5', 'MA10', 'MA20']
        },
        grid: [
            {
                top: '5%',
                left: '8%',
                right: '8%',
                height: '55%'
            },
            {
                left: '8%',
                right: '8%',
                top: '72%',
                height: '16%'
            }
        ],
        xAxis: [
            {
                type: 'category',
                data: result[0],
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
                data: result[0],
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
                start: start,
                end: 100
            },
            {
                show: true,
                xAxisIndex: [0, 1],
                type: 'slider',
                top: '90%',
                start: start,
                end: 100
            }
        ],
        series: [
            {
                name: 'k线',
                type: 'candlestick',
                data: result[1],
                itemStyle: {
                    normal: {
                        color0: '#008000',
                        borderColor: null,
                        borderColor0: null
                    }
                },
                markPoint: {
                    label: {
                        normal: {
                            formatter: function (param) {
                                return param != null ? Math.round(param.value) : '';
                            }
                        }
                    },
                    data: [
                        {
                            name: 'highest value',
                            type: 'max',
                            valueDim: 'highest'
                        },
                        {
                            name: 'lowest value',
                            type: 'min',
                            valueDim: 'lowest'
                        },
                        {
                            name: 'average value on close',
                            type: 'average',
                            valueDim: 'close'
                        }
                    ],
                    tooltip: {
                        formatter: function (param) {
                            return param.name + '<br>' + (param.data.coord || '');
                        }
                    }
                },
                markLine: {
                    symbol: ['none', 'none'],
                    data: [
                        [
                            {
                                name: 'from lowest to highest',
                                type: 'min',
                                valueDim: 'lowest',
                                symbol: 'circle',
                                symbolSize: 10,
                                label: {
                                    normal: {show: false},
                                    emphasis: {show: false}
                                }
                            },
                            {
                                type: 'max',
                                valueDim: 'highest',
                                symbol: 'circle',
                                symbolSize: 10,
                                label: {
                                    normal: {show: false},
                                    emphasis: {show: false}
                                }
                            }
                        ],
                        {
                            name: 'min line on close',
                            type: 'min',
                            valueDim: 'close'
                        },
                        {
                            name: 'max line on close',
                            type: 'max',
                            valueDim: 'close'
                        }
                    ]
                },
                tooltip: {
                    formatter: function (param) {
                        var param = param[0];
                        return [
                            'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
                            'Open: ' + param.data[0] + '<br/>',
                            'Close: ' + param.data[1] + '<br/>',
                            'Lowest: ' + param.data[2] + '<br/>',
                            'Highest: ' + param.data[3] + '<br/>'
                        ].join('');
                    }
                }
            },

            {
                name: 'MA5',
                type: 'line',
                data: result[3],
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA10',
                type: 'line',
                data: result[4],
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA20',
                type: 'line',
                data: result[5],
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'Volume',
                type: 'bar',
                xAxisIndex: 1,
                yAxisIndex: 1,
                data: result[2],
                itemStyle: {
                    normal: {
                        color: '#3488ff'
                    }
                }
            }
        ]
    }
    mycharts.setOption(option,true);
}







