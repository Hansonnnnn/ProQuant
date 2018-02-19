/**
 * Created by xiaoJun on 2017/6/14.
 */

function marketBarChart(chart,data) {
    console.log(data);
    // var data1 = [0,0,0,0,0,0,0,0,0,0];
    // var data2 = [0,0,0,0,0,0,0,0,0,0];
    // for(var i = 0; i < data.length; i++){
    //     if(i<5){
    //         data1[i] = data[i];
    //     }else{
    //         data2[i] = data[i];
    //     }
    // }
    option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
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
                data : ['涨停～-8%', '-8%～-6%', '-6%～-4%', '-4%～-2%', '-2%～0%', '0%～2%', '2%～4%', '4%～6%', '6%～8%', '8%～涨停'],
                axisTick: {
                    alignWithLabel: true
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
                name:'数量',
                type:'bar',
                barWidth: '60%',
                data:data,
                itemStyle: {
                    normal: {
                        color: function (params) {
                            return params.dataIndex < 5 ? '#008000':'#C23531';
                        }
                    },
                },
            }
        ]
    };
    chart.setOption(option,true);
}