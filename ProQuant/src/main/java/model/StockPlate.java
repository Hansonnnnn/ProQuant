package model;


/**
 * @author 凡
 * 板块名称，分别是：中小板、创业板、沪深板(主板)、
 *
 */
public enum StockPlate {
    SME,CHINEXT,CSI300,POOLOFUSER,ALLSTOCKS;
    
    public static String codeOf(StockPlate stockPlate){
    	switch (stockPlate) {
		case CSI300:
			return "000300";
		case SME:
			return "399005";
		case CHINEXT:
			return "399006";
		default:
			return null;
		}
    	
    }
    
    public byte getOrder() {
		return (byte) this.ordinal();
	}
    
    public static String getName(Byte byte1){
    	switch (byte1){
    	case 0: return StockPlate.SME.toString();
    	case 1:return StockPlate.CHINEXT.toString();
    	case 2:return StockPlate.CSI300.toString();
    	case 3:return StockPlate.POOLOFUSER.toString();
    	case 4:return StockPlate.ALLSTOCKS.toString();
    	}
        return null;
    }
}
