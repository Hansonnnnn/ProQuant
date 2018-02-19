/**
 * Created by xiaoJun on 2017/5/17.
 */


function meterChart(mycharts,score) {
    window.onresize = mycharts.resize;
    // var myChart = echarts.init(document.getElementById("meter"));
    option = {
        tooltip : {
            formatter: "{a} <br/>{b} : {c}分"
        },
        series : [
            {
                name:'股票评分',
                type:'gauge',
                min : 0,
                max : 10,
                detail : {formatter:'{value}分'},
                data:[{value: score, name: '评分'}]
            }
        ]
    };
    mycharts.setOption(option, true);
}
