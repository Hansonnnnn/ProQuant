/**
 * Created by LENOVO on 2017/6/15.
 */

function bpLineChart(chart,time,base,strategy) {
    option = {
        tooltip : {
            trigger: 'axis'
        },
        color : ['#C23531','#008000','#b6b6b6'],
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : time
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
            }
        ],
        series : [
            {
                name:'预测收盘价',
                type:'line',
                data: base,
                showSymbol : false,
            },
            {
                name:'真实收盘价',
                type:'line',
                data:strategy,
                showSymbol : false,
            }
        ]
    };
    chart.setOption(option,true);
}