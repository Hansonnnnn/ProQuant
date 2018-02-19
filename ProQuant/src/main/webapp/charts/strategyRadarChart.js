/**
 * Created by LENOVO on 2017/6/15.
 */

function strategyRadarChart(myChart,data) {
    window.onresize = myChart.resize;
    console.log(data);
    option = {
        tooltip : {
            trigger: 'axis'
        },
        // color:['#'],
        grid: [
            {
                top: '2%',
                left: '15%',
                right: '15%',
                height: '70%'
            },
        ],
        radar : [
            {
                indicator : [
                    { name: '收益指数', max: 100},
                    { name: '实盘指数', max: 100},
                    { name: '稳定性', max: 100},
                    { name: '流动性', max: 100},
                    { name: '抗风险性', max: 100},
                ],
            }
        ],
        series : [
            {
                type: 'radar',
                itemStyle: {
                    normal: {
                        areaStyle: {
                            type: 'default'
                        }
                    }
                },
                data : [
                    {
                        value : data,
                    },
                ]
            }
        ]
    };
    myChart.setOption(option);
}