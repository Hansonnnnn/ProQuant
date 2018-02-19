/**
 * Created by LENOVO on 2017/6/15.
 */


function strategyLineChart(chart,time,base,strategy) {
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
                name:'基准收益率',
                type:'line',
                data: base,
                showSymbol : false,
            },
            {
                name:'策略收益率',
                type:'line',
                data:strategy,
                showSymbol : false,
            }
        ]
    };
    chart.setOption(option,true);
}