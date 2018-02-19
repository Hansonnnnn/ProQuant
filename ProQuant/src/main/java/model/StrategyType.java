package model;

public enum StrategyType {

	//BP神经网络策略
	BPNetwork,

	//均值回归策略
	ReversionDriven,
    
	//动量策略
	MomentumDriven;
	
	public byte getOrder(){
		return (byte) this.ordinal();
	}
	
	public static String getName(Byte byte1){
		switch (byte1) {
		case 0: return StrategyType.BPNetwork.toString();
		case 1: return StrategyType.ReversionDriven.toString();
		case 2: return StrategyType.MomentumDriven.toString();
		}
		return null;
	}
}
