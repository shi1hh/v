package com.example.hungrytest;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import JSON.login;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GuiActivity extends Activity implements OnPageChangeListener{

	List<View> lsViews=new ArrayList<View>();
	ViewPager vpPager;
	ImageView[] imageViews=new ImageView[3];
	final String SHAREDPREFERENCES_NAME="first_prf";
	
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_gui_1);
        init();
       
    }
	
	void init()
	{
		initVP();
		initDot();

	}
	void initVP()
	{
		vpPager=(ViewPager) findViewById(R.id.viewpager_home);
		LayoutInflater lInflater=LayoutInflater.from(this);
        View v1=lInflater.inflate(R.layout.home_gui_3, null);
        lsViews.add(v1);
        v1=lInflater.inflate(R.layout.home_gui_2, null);
        lsViews.add(v1);
        v1=lInflater.inflate(R.layout.home_gui, null);
        lsViews.add(v1);
        vpPager.setAdapter(new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0==arg1;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return lsViews.size();
			}
			@Override
			public Object instantiateItem(ViewGroup v,int arg0)
			{
				v.addView(lsViews.get(arg0));
				if (arg0==imageViews.length-1) {
					ImageButton imgbutton=(ImageButton) findViewById(R.id.imageButton_gomain);
					imgbutton.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							goMain();
						}
					});
				}
				
				
				
				return lsViews.get(arg0);
			}
			@Override
			public void destroyItem(ViewGroup v,int arg0,Object object)
			{
				v.removeView(lsViews.get(arg0));
			}
		});
        vpPager.setOnPageChangeListener(this);
	}
	void initDot()
	{
		imageViews[0]=(ImageView)findViewById(R.id.imageView1);
		imageViews[1]=(ImageView)findViewById(R.id.imageView2);
		imageViews[2]=(ImageView)findViewById(R.id.imageView3);
		imageViews[0].setEnabled(true);
		imageViews[1].setEnabled(false);  
		imageViews[2].setEnabled(false);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		Log.d("onpage", ""+arg0);
		for(int i=0;i<imageViews.length;i++)
		{
			if(arg0==i)
			{
				imageViews[i].setEnabled(true);
			}
			else {
				imageViews[i].setEnabled(false);
			}
		}
		
	}
	public void goMain()
	{
		SharedPreferences sharedPreferences=this.getSharedPreferences(SHAREDPREFERENCES_NAME, MODE_PRIVATE);
		Editor et=sharedPreferences.edit();
		et.putBoolean("isfirst", false);
		et.commit();
		Intent intent=new Intent(GuiActivity.this,IndexActivity.class);
		startActivity(intent);
		this.finish();
	}
}
