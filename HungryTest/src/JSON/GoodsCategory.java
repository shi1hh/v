package JSON;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

import model.Goods;

public class GoodsCategory{

	
	public static  List<model.GoodsCategory> getData()
	{   List<model.GoodsCategory> lsGoodsCategories=new ArrayList<model.GoodsCategory>();
		Goods goods1=new Goods("�¹����ڽ�ʿ��ƿ", 12, "������", 3, 5,"�ƾ���5.0%��������500ml");
		Goods goods2=new Goods("�¹����ڿ�����ơ", 12, "������", 3, 5,"�ƾ���5.0%��������500ml");
		Goods goods3=new Goods("�¹����ڿ�����ơ�ƣ�һ��24ƿ", 12, "������", 3, 5,"�ƾ���5.0%��������500ml");
		Goods goods4=new Goods("�¹�ԭװ���ڣ���������С���ơ��500ML,һ��24ƿ", 12, "������", 3, 5,"�ƾ���5.0%��������500ml");
		Goods goods5=new Goods("����������", 12, "������", 3, 5,"�ƾ���5.0%��������500ml");
		List<Goods> lsGoods=new ArrayList<Goods>();
		lsGoods.add(goods5);
		lsGoods.add(goods4);
		lsGoods.add(goods3);
		lsGoods.add(goods1);
		lsGoods.add(goods2);
	    model.GoodsCategory goodsCategory=new model.GoodsCategory("������", "��Һã����˴���", lsGoods);
		lsGoodsCategories.add(goodsCategory);
		
		Goods goods6=new Goods("���ȫ��Ͱ", 86, "��ֵ���˲�", 3, 5,"5��ԭζ��+6�鼦��+1��������");
		Goods goods7=new Goods("�ػݾ���ȫ�Ҳ�", 97, "��ֵ���˲�", 3, 0,"2���������ȱ�+1���°���������+2��ԭζ��");
		Goods goods8=new Goods("��ζС��Ͱ", 54, "��ֵ���˲�", 3, 0,"�°��ﰺ����5��+�춹��2��");
		Goods goods9=new Goods("����˫�˲�", 64, "��ֵ���˲�", 5, 0,"��������1��+5�麺��");
		Goods goods10=new Goods("����������", 12, "��ֵ���˲�", 3, 5,"�ƾ���5.0%��������500ml");
		List<Goods> lsGoods1=new ArrayList<Goods>();
		lsGoods1.add(goods6);
		lsGoods1.add(goods7);
		lsGoods1.add(goods8);
		lsGoods1.add(goods9);
		lsGoods1.add(goods10);
	    model.GoodsCategory goodsCategory1=new model.GoodsCategory("��ֵ���˲�", "��Һã����˴���", lsGoods1);
			lsGoodsCategories.add(goodsCategory1);
			
			Goods goods11=new Goods("���ȫ��Ͱ", 86, "�ͷ�Сʳ", 3, 5,"5��ԭζ��+6�鼦��+1��������");
			Goods goods12=new Goods("�ػݾ���ȫ�Ҳ�", 97, "�ͷ�Сʳ", 3, 0,"2���������ȱ�+1���°���������+2��ԭζ��");
			Goods goods13=new Goods("��ຣ̦Ϻ", 54, "�ͷ�Сʳ", 3, 0,"�°��ﰺ����5��+�춹��2��");
			Goods goods14=new Goods("����˫�˲�", 64, "�ͷ�Сʳ", 5, 0,"��������1��+5�麺��");
			Goods goods15=new Goods("����������", 12, "�ͷ�Сʳ", 3, 5,"�ƾ���5.0%��������500ml");
			List<Goods> lsGoods2=new ArrayList<Goods>();
			lsGoods2.add(goods11);
			lsGoods2.add(goods12);
			lsGoods2.add(goods13);
			lsGoods2.add(goods14);
			lsGoods2.add(goods15);
		    model.GoodsCategory goodsCategory2=new model.GoodsCategory("�ͷ�Сʳ", "��Һã����˴���", lsGoods2);
				lsGoodsCategories.add(goodsCategory2);
				
	
			
		return lsGoodsCategories;
		
	}
	
}
