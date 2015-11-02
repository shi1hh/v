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

	private ListView lv1;//���LISTVIEW
	private ListView lv2;//�ұ�LISTVIEW
	private Activity context;//�Լ���ʵ��
	private TextView selectv;//ѡ�е����LISTVIEW�����ڸı���ѡ��ɫ
	/*FoodRightadp rigadp;//�ұ�ADP����
*/	FoodLeftadp lfadp;//���
	ValueAnimator valueAnimator;//��������
	Button imgAdd;//���ڶ�������
	Resources resouces;//��Դ�ļ�������
	/**
	 * �Ա����ݳ�ʼ��
	 */
	String[] category={"��������","˫���ײ�","����","����","�׷�","С�Է���"};
	String[] food1={"����","�׷�","����","ţ����"};
	String[] food2={"����","�׷�","ţ����","����"};
	String[] food3={"����","�׷�","����","ţ����"};
	String[] food4={"�ŹǷ�","�ն췹","���շ�","����������"};
	String[] food5={"�ŹǷ�","�ն췹","���շ�","����������"};
	String[] food6={"�ŹǷ�","�ն췹","���շ�","����������"};
	String[] price={"��30","��20","��8","��10"};
	
	/**
	 * ��ʼ����������FoodCategory
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
    	 * ��ʼ����LISTVIEWĬ��ѡ�е�һ��
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
     * ��ӹ��ﶯ��
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
 * ���򶯻�
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
         
                // x����200px/s ����y����0.5 * 10 * t
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
