$(function () {
    var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
    var lineoption = {
        title: {
            text: '每小时实际生产产量'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['标准小时产量']
        },
        grid: {
            x: 40,
            x2: 40,
            y2: 24
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: ['00:00', '01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00', '13:00',
                    '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00', '23:59']
            }
        ],
        yAxis: [
            {
                type: 'value',
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: [
            {
                name: '每小时生产产量',
                type: 'line',
                itemStyle : {
                    normal : {
                        lineStyle:{
                            color:'blue'  // 设置折线的颜色
                        }
                    }
                },
                data: [11, 11, 15, 13, 12, 13, 10, 11, 11, 15, 13, 12, 13, 10, 11, 11, 15, 13, 12, 13, 10, 11, 20, 11, 14],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'},
                    ]
                },
                //设置警戒线
                markLine : {
                    symbol:"none",               //去掉警戒线最后面的箭头
                    label:{
                        position:"end",         //将警示值放在哪个位置，三个值“start”,"middle","end"  开始  中点 结束
                        formatter: "警戒值"
                    },
                    data : [{
                        silent:false,             //鼠标悬停事件  true没有，false有
                        lineStyle:{               //警戒线的样式  ，虚实  颜色
                            type:"solid",
                            color:"red"
                        },
                        name: '警戒线',
                        yAxis: 11   // 警戒线的值
                    }]
                }
            },
        ]
    };
    lineChart.setOption(lineoption);
    $(window).resize(lineChart.resize);
})