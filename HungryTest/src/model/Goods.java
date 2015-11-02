package model;

import cn.bmob.v3.BmobObject;

public class Goods extends BmobObject {


	String name;
	int price;
	String category;
	float favourValue;
	int sells;
	String note;
	public Goods(String name,int price,String category,float favourValue,int sells,String note)
	{
		this.favourValue=favourValue;
	   this.name=name;
	   this.price=price;
	   this.category=category;
	   this.note=note;
	}
	public void setFavourValue(float favourValue)
	{
		this.favourValue=favourValue;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setSells(int sells) {
		this.sells=sells;
	}
	public void setNote(String note) {
		this.note=note;
	}
	public void setPrice(int price) {
		this.price=price;
	}
	public String  getName()
	{
		return name;
	}
	public int  getPrice() {
		return price;
	}
	public String getCategory() {
		return this.category;
	}
	public float getFavourValue() {
		return this.favourValue;
	}
	public int  getSells()
	{
		return sells;
	}
	public String  getNote()
	{
		return note;
	}
}
