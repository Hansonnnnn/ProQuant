package bl.helper.strategy;

import VO.strategyPageVO.ParamDataVO;

/**
 * Created by lenovo on 2017/6/14.
 */
public class BaseAndStrategyParam {
    private ParamDataVO paramStrategyDataVO;
    private ParamDataVO paramBaseDataVO;

    public BaseAndStrategyParam(ParamDataVO paramStrategyDataVO, ParamDataVO paramBaseDataVO) {
        this.paramStrategyDataVO = paramStrategyDataVO;
        this.paramBaseDataVO = paramBaseDataVO;
    }

    public ParamDataVO getParamStrategyDataVO() {
        return paramStrategyDataVO;
    }

    public void setParamStrategyDataVO(ParamDataVO paramStrategyDataVO) {
        this.paramStrategyDataVO = paramStrategyDataVO;
    }

    public ParamDataVO getParamBaseDataVO() {
        return paramBaseDataVO;
    }

    public void setParamBaseDataVO(ParamDataVO paramBaseDataVO) {
        this.paramBaseDataVO = paramBaseDataVO;
    }
}
