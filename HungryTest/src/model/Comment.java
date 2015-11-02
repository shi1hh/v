package model;

import cn.bmob.v3.BmobObject;

public class Comment extends BmobObject{

	
	String content;
	Float favorValue;
	Boolean isGood;
	public void setContent(String content) {
		this.content=content;
	}
	public String getContent() {
		return this.content;
	}
	public void setFavorValue(Float value) {
		this.favorValue=value;
	}
	public Float getFavorValue() {
		return this.favorValue;
	}
	public void setIsGood(Boolean isBoolean) {
		this.isGood=true;
	}
	public Boolean getGood() {
		return this.isGood;
	}
	
	
}
