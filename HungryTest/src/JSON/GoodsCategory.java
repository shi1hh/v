package JSON;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

import model.Goods;

public class GoodsCategory{

	
	public static  List<model.GoodsCategory> getData()
	{   List<model.GoodsCategory> lsGoodsCategories=new ArrayList<model.GoodsCategory>();
		Goods goods1=new Goods("德国进口教士白瓶", 12, "好评榜", 3, 5,"酒精度5.0%，净含量500ml");
		Goods goods2=new Goods("德国进口凯撒白啤", 12, "好评榜", 3, 5,"酒精度5.0%，净含量500ml");
		Goods goods3=new Goods("德国进口凯撒黑啤酒，一箱24瓶", 12, "好评榜", 3, 5,"酒精度5.0%，净含量500ml");
		Goods goods4=new Goods("德国原装进口，拜仁至尊小麦白啤酒500ML,一箱24瓶", 12, "好评榜", 3, 5,"酒精度5.0%，净含量500ml");
		Goods goods5=new Goods("爱尔兰进口", 12, "好评榜", 3, 5,"酒精度5.0%，净含量500ml");
		List<Goods> lsGoods=new ArrayList<Goods>();
		lsGoods.add(goods5);
		lsGoods.add(goods4);
		lsGoods.add(goods3);
		lsGoods.add(goods1);
		lsGoods.add(goods2);
	    model.GoodsCategory goodsCategory=new model.GoodsCategory("好评榜", "大家好，点了错不了", lsGoods);
		lsGoodsCategories.add(goodsCategory);
		
		Goods goods6=new Goods("外带全家桶", 86, "超值多人餐", 3, 5,"5块原味鸡+6块鸡翅+1份土豆泥");
		Goods goods7=new Goods("特惠经典全家餐", 97, "超值多人餐", 3, 0,"2个香辣鸡腿堡+1个新奥良尔鸡翅+2块原味鸡");
		Goods goods8=new Goods("五味小吃桶", 54, "超值多人餐", 3, 0,"新奥里昂二尺5块+红豆派2个");
		Goods goods9=new Goods("包包双人餐", 64, "超值多人餐", 5, 0,"香辣鸡翅1个+5块汉堡");
		Goods goods10=new Goods("爱尔兰进口", 12, "超值多人餐", 3, 5,"酒精度5.0%，净含量500ml");
		List<Goods> lsGoods1=new ArrayList<Goods>();
		lsGoods1.add(goods6);
		lsGoods1.add(goods7);
		lsGoods1.add(goods8);
		lsGoods1.add(goods9);
		lsGoods1.add(goods10);
	    model.GoodsCategory goodsCategory1=new model.GoodsCategory("超值多人餐", "大家好，点了错不了", lsGoods1);
			lsGoodsCategories.add(goodsCategory1);
			
			Goods goods11=new Goods("外带全家桶", 86, "缤纷小食", 3, 5,"5块原味鸡+6块鸡翅+1份土豆泥");
			Goods goods12=new Goods("特惠经典全家餐", 97, "缤纷小食", 3, 0,"2个香辣鸡腿堡+1个新奥良尔鸡翅+2块原味鸡");
			Goods goods13=new Goods("香脆海苔虾", 54, "缤纷小食", 3, 0,"新奥里昂二尺5块+红豆派2个");
			Goods goods14=new Goods("包包双人餐", 64, "缤纷小食", 5, 0,"香辣鸡翅1个+5块汉堡");
			Goods goods15=new Goods("爱尔兰进口", 12, "缤纷小食", 3, 5,"酒精度5.0%，净含量500ml");
			List<Goods> lsGoods2=new ArrayList<Goods>();
			lsGoods2.add(goods11);
			lsGoods2.add(goods12);
			lsGoods2.add(goods13);
			lsGoods2.add(goods14);
			lsGoods2.add(goods15);
		    model.GoodsCategory goodsCategory2=new model.GoodsCategory("缤纷小食", "大家好，点了错不了", lsGoods2);
				lsGoodsCategories.add(goodsCategory2);
				
	
			
		return lsGoodsCategories;
		
	}
	
}
