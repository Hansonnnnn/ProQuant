package VO;

/**
 * Created by LENOVO on 2017/5/25.
 */
public class StockVO {
    private String name;//股票名称
    private int id;//股票id

    private double price;//当前价格
    private double highValue;//最高值
    private double lowValue;//最低值
    private double openValue;//开盘价
    private double closeValue;//收盘价
    private double price_Of_Increase;//增长的价格，价格上涨为正，下跌为负，浮点数
    private double amount_Of_Increase;//涨幅，如果股票价格上涨，则为正，下跌则为负，百分数
    private int volume;//成交量
    private double turnover;//成交额
    private double marketValue;//市值
    private double circulationMarketValue;//流通市值
    private double amplitude;//振幅
    private double turnoverRate;//换手率
    private double marketRate;//市净率
    private double priceEarningsRatio;//市盈率

    private String companyIntro;//公司简介
    private String companyBusiness;//公司业务

    private double score;//股票评分
    private double rateOfDefeat;//击败的股票数，百分数

    public StockVO(String name, int id, double price, double highValue, double lowValue, double openValue, double closeValue,
                    double price_Of_Increase, double amount_Of_Increase, int volume, double turnover, double marketValue,
                    double circulationMarketValue, double amplitude, double turnoverRate, double marketRate, double priceEarningsRatio,
                    String companyIntro, String companyBusiness, double score, double rateOfDefeat) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.highValue = highValue;
        this.lowValue = lowValue;
        this.openValue = openValue;
        this.closeValue = closeValue;
        this.price_Of_Increase = price_Of_Increase;
        this.amount_Of_Increase = amount_Of_Increase;
        this.volume = volume;
        this.turnover = turnover;
        this.marketValue = marketValue;
        this.circulationMarketValue = circulationMarketValue;
        this.amplitude = amplitude;
        this.turnoverRate = turnoverRate;
        this.marketRate = marketRate;
        this.priceEarningsRatio = priceEarningsRatio;
        this.companyIntro = companyIntro;
        this.companyBusiness = companyBusiness;
        this.score = score;
        this.rateOfDefeat = rateOfDefeat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getHighValue() {
        return highValue;
    }

    public void setHighValue(double highValue) {
        this.highValue = highValue;
    }

    public double getLowValue() {
        return lowValue;
    }

    public void setLowValue(double lowValue) {
        this.lowValue = lowValue;
    }

    public double getOpenValue() {
        return openValue;
    }

    public void setOpenValue(double openValue) {
        this.openValue = openValue;
    }

    public double getCloseValue() {
        return closeValue;
    }

    public void setCloseValue(double closeValue) {
        this.closeValue = closeValue;
    }

    public double getPrice_Of_Increase() {
        return price_Of_Increase;
    }

    public void setPrice_Of_Increase(double price_Of_Increase) {
        this.price_Of_Increase = price_Of_Increase;
    }

    public double getAmount_Of_Increase() {
        return amount_Of_Increase;
    }

    public void setAmount_Of_Increase(double amount_Of_Increase) {
        this.amount_Of_Increase = amount_Of_Increase;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public double getCirculationMarketValue() {
        return circulationMarketValue;
    }

    public void setCirculationMarketValue(double circulationMarketValue) {
        this.circulationMarketValue = circulationMarketValue;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public double getMarketRate() {
        return marketRate;
    }

    public void setMarketRate(double marketRate) {
        this.marketRate = marketRate;
    }

    public double getPriceEarningsRatio() {
        return priceEarningsRatio;
    }

    public void setPriceEarningsRatio(double priceEarningsRatio) {
        this.priceEarningsRatio = priceEarningsRatio;
    }

    public String getCompanyIntro() {
        return companyIntro;
    }

    public void setCompanyIntro(String companyIntro) {
        this.companyIntro = companyIntro;
    }

    public String getCompanyBusiness() {
        return companyBusiness;
    }

    public void setCompanyBusiness(String companyBusiness) {
        this.companyBusiness = companyBusiness;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getRateOfDefeat() {
        return rateOfDefeat;
    }

    public void setRateOfDefeat(double rateOfDefeat) {
        this.rateOfDefeat = rateOfDefeat;
    }
}
