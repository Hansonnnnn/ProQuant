/**
 * Created by xiaoJun on 2017/6/15.
 */

function strategyBarChart(chart,xdata,ydata1,ydata2) {
    option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
            }, axisLabel : {
                formatter: '{value}%'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : xdata,
                axisTick: {
                    alignWithLabel: true
                },
                axisLabel : {
                    formatter: '{value}%'
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'正收益周期数',
                type:'bar',
                data:ydata1,
                itemStyle: {
                    normal: {
                        color: '#C23531'
                        //     function (params) {
                        //     return params.dataIndex < 5 ? '#008000':'#C23531';
                        // }
                    },
                },
            },
            {
                name:'负收益周期数',
                type:'bar',
                data:ydata2,
                itemStyle: {
                    normal: {
                        color: '#008000'
                    },
                },
            }
        ]
    };
    chart.setOption(option,true);
}

