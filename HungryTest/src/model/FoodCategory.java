package model;
public class FoodCategory
{
	 String category;
	 String[] food;
	 String[] price;
	public FoodCategory(String category,String[] food,String[] price)
	{
		this.category=category;this.food=food;this.price=price;
	}
	public FoodCategory(String category)
	{
		this.category=category;
		
	}
	public void setFood(String[] food)
	{
		this.food=food;
	}
	public void setPrice(String[]price) {
		this.price=price;
	}
	public String[] getFood()
	{
		return food;
	}
	public String[] getPrice() {
		return price;
	}
	public String getCategory() {
		return this.category;
	}
}