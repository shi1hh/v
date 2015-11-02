package model;

import java.util.List;

import cn.bmob.v3.BmobObject;

import android.R.string;

public class GoodsCategory extends BmobObject {

	String categoryName;
	String note;
	List<Goods> lsgoods;
	public GoodsCategory(String categoryName,String note,List<Goods> lsgoods) {
		// TODO Auto-generated constructor stub
      this.categoryName=categoryName;
      this.note=note;
      this.lsgoods=lsgoods;
	}
	public String getCategoryName() {
		return this.categoryName;
	}
	public void setCategoryname(String categoryName) {
		this.categoryName=categoryName;
	}
	public String getNote(String note) {
		return this.note;
	}
	public void setNote(String note) {
		this.note=note;
	}
	public List<Goods> getGoods() {
		return this.lsgoods;
	}
	public void setGoods(List<Goods> lsgoods) {
		this.lsgoods=lsgoods;
	}
	
	
}
