package model;

public class MyFood {

	String name;
	String price;
	String category;
	public MyFood(String name,String price,String category)
	{
	   this.name=name;
	   this.price=price;
	   this.category=category;
	}

	public void setName(String name)
	{
		this.name=name;
	}
	public void setPrice(String price) {
		this.price=price;
	}
	public String  getName()
	{
		return name;
	}
	public String  getPrice() {
		return price;
	}
	public String getCategory() {
		return this.category;
	}
	
}
