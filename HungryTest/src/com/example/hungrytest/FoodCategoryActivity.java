package com.example.hungrytest;

import java.util.ArrayList;

import adp.FoodLeftadp;
/*import adp.FoodRightadp;*/
import android.widget.AbsListView.LayoutParams;
import java.util.List;

import model.FoodCategory;
import model.MyFood;

import android.R.integer;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class FoodCategoryActivity extends Activity{

	private ListView lv1;//左边LISTVIEW
	private ListView lv2;//右边LISTVIEW
	private Activity context;//自己的实例
	private TextView selectv;//选中的左边LISTVIEW，用于改变所选颜色
	/*FoodRightadp rigadp;//右边ADP引用
*/	FoodLeftadp lfadp;//左边
	ValueAnimator valueAnimator;//动画对象
	Button imgAdd;//用于动画表现
	Resources resouces;//资源文件的引用
	/**
	 * 自备数据初始化
	 */
	String[] category={"人气热销","双人套餐","主餐","饮料","米饭","小吃分享"};
	String[] food1={"白粥","米饭","豆奶","牛腩面"};
	String[] food2={"白粥","米饭","牛腩面","豆奶"};
	String[] food3={"白粥","米饭","豆奶","牛腩面"};
	String[] food4={"排骨饭","烧鹅饭","叉烧饭","鱼香茄子煲"};
	String[] food5={"排骨饭","烧鹅饭","叉烧饭","鱼香茄子煲"};
	String[] food6={"排骨饭","烧鹅饭","叉烧饭","鱼香茄子煲"};
	String[] price={"￥30","￥20","￥8","￥10"};
	
	/**
	 * 初始化数据类型FoodCategory
	 */
	List<FoodCategory> lsfoodcategory=new ArrayList<FoodCategory>();
	{
		for(int i=0;i<category.length;i++)
		{
		FoodCategory newone=new FoodCategory(category[i]);
		newone.setPrice(price);
		lsfoodcategory.add(newone);
		}
		lsfoodcategory.get(0).setFood(food1);
		lsfoodcategory.get(1).setFood(food2);
		lsfoodcategory.get(2).setFood(food3);
		lsfoodcategory.get(3).setFood(food4);
		lsfoodcategory.get(4).setFood(food5);
		lsfoodcategory.get(5).setFood(food6);
	}
	 
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.foodcategory);
    	resouces=getResources();
        context=this;
        initWidget();
       initList();
      addListener();

    }
    public void initWidget()
    {
    	lv1=(ListView) findViewById(R.id.lvfood1);

    	lv2=(ListView) findViewById(R.id.lvfood2);


    }

    public void initList()
    {
		
/*	rigadp=new FoodRightadp(this,lsfoodcategory,lfadp);
	lfadp=new FoodLeftadp(this,lsfoodcategory,rigadp);
	rigadp.setLadp(lfadp);*/
	
 /*  	lv1.setAdapter(lfadp);
    lv2.setAdapter(rigadp);*/
    lv1.addFooterView(LayoutInflater.from(this).inflate(R.layout.lv_foot, null));
    lv2.addFooterView(LayoutInflater.from(this).inflate(R.layout.lv_foot, null));
    	
    }
    public void addListener()
    {
    	lv1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView tv_select=(TextView) view.findViewById(R.id.tv_lft_name);
				tv_select.setBackgroundColor(resouces.getColor(R.color.lvlft_select));
			
				if(tv_select!=selectv)
				{
					selectv.setBackgroundColor(resouces.getColor(R.color.lvlft_black));
					
				}
				/*rigadp.setLv1Position(position);
			    selectv=tv_select;
		        rigadp.notifyDataSetChanged();*/
			}
    		   
    	});
    	lv2.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}     
    		
    	});
    	/**
    	 * 初始化让LISTVIEW默认选中第一项
    	 */
    	 lv1.post(new Runnable() {
             @Override
             public void run() {
                 selectv = (TextView) lv1.getChildAt(0).findViewById(R.id.tv_lft_name);
                 selectv.setBackgroundColor(Color.WHITE);
                 lv1.setItemChecked(0, true);
             }
         });
   
    }
    
    /**
     * 添加购物动画
     * @param slocation
     */
    public void setAnima(View v,int[] slocation)
    {
       imgAdd=(Button) findViewById(R.id.button_add2);
       imgAdd.setVisibility(View.VISIBLE);
    	ImageView shopcar=(ImageView) findViewById(R.id.iv_add_cart);
    	int[] elocation=new int[2];
    	shopcar.getLocationOnScreen(elocation);
    	Log.d("shop"," "+elocation[0]+" "+elocation[1]);
    	paowuxian(imgAdd,slocation,elocation);
    }
/**
 * 购买动画
 * @param view
 * @param start
 * @param end
 */
    public void paowuxian(final View view,int[] start,int[] end)
    {
	final float x1=this.getWindowManager().getDefaultDisplay().getWidth()/540;
	final float y1=this.getWindowManager().getDefaultDisplay().getHeight()/960;
	Log.d("vvvvv ", " start");
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(1000);
        valueAnimator.setObjectValues(new PointF(0,0));
        valueAnimator.setInterpolator(new LinearInterpolator());

        valueAnimator.setEvaluator(new TypeEvaluator<PointF>()
        {
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                    PointF endValue)
            {
         
                // x方向200px/s ，则y方向0.5 * 10 * t
            	Log.d("fff", " "+fraction);
                PointF point = new PointF();
                point.x = x1*200 * (1-fraction) * 3;
              
                point.y = 0.5f * 200*y1 * (fraction * 3) * (fraction * 3);
               
                return point;

                
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                PointF point = (PointF) animation.getAnimatedValue();
                view.setX(point.x);
                view.setY(point.y);

            }
        });
    }
}
