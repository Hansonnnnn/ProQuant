/**
 * Created by LENOVO on 2017/6/15.
 */

function marketPieChart(chart, score) {
    option = {
        legend:{
            x : 'center',
            y : 'center',
            data:score+'分'
        },
        color:['#D53A35','#f5f5f5'],
        series: [
            {
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: true,
                        position: 'center',
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    },
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:score, name:score+'分'},
                    {value:10-score,name:''}
                ]
            }
        ]
    };
    chart.setOption(option,true);
}