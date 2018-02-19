/**
 * Created by xiezhenyu on 2017/6/10.
 */
$(function () {
    $("#marketPart").click(function () {
        window.location.href = "/MarketPage/toMarketPage.do";
    })

    $("#stockinfoPart").click(function () {
        window.location.href = "/StockInfo/603998/toStockInfo.do";
    })

    $("#strategyPart").click(function () {
        window.location.href = "/Strategy/toStrategy.do";
    })

    $("#comparePart").click(function () {
        window.location.href = "/StockCompare/toStockCompare.do";
    })
});

