package model;

import cn.bmob.v3.BmobObject;

public class Evaluate extends BmobObject {
	
	
	
	private Integer valueEvaluate;
	private Float mServiceEvaluate;
	private Float mGoodsEvaluate;
	
	public void setValueEvaluate(Integer valueInteger)
	{
		this.valueEvaluate=valueInteger;
	}
	public void setServiceEvaluate(Float mServiceEvaluate)
	{
		this.mServiceEvaluate=mServiceEvaluate;
	}
	public void setGoodsEvaluate(Float mGoodsEvaluate) {
		this.mGoodsEvaluate=mGoodsEvaluate;
	}
	

}
