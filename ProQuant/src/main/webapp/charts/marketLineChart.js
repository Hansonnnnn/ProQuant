/**
 * Created by xiaoJun on 2017/6/15.
 */

function marketLineChart(chart,time,up,down,five) {
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
                name:'涨停数量',
                type:'line',
                data: up,
                showSymbol : false,
            },
            {
                name:'跌停数量',
                type:'line',
                data:down,
                showSymbol : false,
            },
            {
                name:'五日平均涨停数量',
                type:'line',
                data:five,
                showSymbol : false,
            }
        ]
    };
    chart.setOption(option,true);
}

