package com.example.hungrytest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Store;

import Fragments.FragmentCartEvaluate;
import Fragments.FragmentCartMenu;
import Fragments.FragmentCartSeller;
import Fragments.FragmentSellerMap;
import adp.MyFragmentPagerAdapter;
import android.R.anim;
import android.R.integer;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

public class CartActivity extends FragmentActivity implements ActionBar.TabListener {

	ViewPager viewPager;

	List<Fragment> fragments=new ArrayList<Fragment>();
	private Store mStore;

	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        Intent intent=getIntent();
        Bundle mBundle=intent.getExtras();
        mStore=(Store) mBundle.getSerializable("store");
        setContentView(R.layout.cart);
        init();
   
    }
    void init()
    {
    	initViewPager();
    	initActionBar();
    	addListener();
    }
    public Store getStore() {
		return this.mStore;
	}

	public void setAnim(final int[] start,final int[] end,final View target) {
	
		
		ValueAnimator mValueAnimator=new ValueAnimator();
		mValueAnimator.setDuration(1000);
		mValueAnimator.setObjectValues(new PointF(0,0));
		mValueAnimator.setInterpolator(new LinearInterpolator());
		mValueAnimator.setEvaluator(new TypeEvaluator<PointF>() {

			@Override
			public PointF evaluate(float fraction, PointF arg1, PointF arg2) {
				// TODO Auto-generated method stub

			   Log.d("33", "start[1]="+start[1]+"   "+end[1]);
			    PointF point = new PointF();
			    
			    point.x=(start[0]-end[0])*(1-fraction)+end[0];
			    point.y =(end[1]-start[1])*fraction*fraction+start[1];
			   
			   
			   
			   
			   Log.d("33", ""+point.y);
				
				return point;
			}
		});
		mValueAnimator.start();
		mValueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				// TODO Auto-generated method stub
				PointF mPoint=(PointF)arg0.getAnimatedValue();
				Log.d("1", ""+mPoint.y);
				target.setX(mPoint.x);
				target.setY(mPoint.y);
				if(target.getY()==end[1])
				{target.setVisibility(View.GONE);}
				
				
			}
		});
	}
    void initViewPager()
    {
    	viewPager=(ViewPager) findViewById(R.id.viewpager_main);
    	FragmentCartEvaluate fragmentCartEvaluate=new FragmentCartEvaluate();
    	FragmentCartMenu fragmentCartMenu=new FragmentCartMenu();
    	FragmentCartSeller fragmentCartSeller=new FragmentCartSeller();
    	FragmentSellerMap fragmentSellerMap=new FragmentSellerMap();
      	fragments.add(fragmentCartMenu);
    	fragments.add(fragmentCartSeller);
    	fragments.add(fragmentCartEvaluate);
    	fragments.add(fragmentSellerMap);
    	MyFragmentPagerAdapter fragmentPagerAdapter=new MyFragmentPagerAdapter(this.getSupportFragmentManager(),fragments);
    	viewPager.setAdapter(fragmentPagerAdapter);
    }
	void initActionBar()
	{ 
		  setupTest1();  
	        setupTest2();  
	        setupTest3();  

	         
	 
	 
	        getActionBar().setDisplayShowTitleEnabled(true);  
	        getActionBar().setTitle(mStore.getName());
	        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	        getActionBar().setDisplayUseLogoEnabled(false);
	       getActionBar().setDisplayShowHomeEnabled(false);
	   
	       
			
	}
	  private void setupTest1(){  
	        Tab tab = this.getActionBar().newTab();  
	        tab.setContentDescription("Tab 1");  
	        tab.setText( this.getString(R.string.cart_tab_menu));  
	        tab.setTabListener(this);  
	        getActionBar().addTab(tab);  
	    }  
	  
	    private void setupTest2(){  
	        Tab tab = this.getActionBar().newTab();  
	        tab.setContentDescription("Tab 2");  
	        tab.setText( this.getString(R.string.cart_tab_evaluate));  
	        tab.setTabListener(this);  
	        getActionBar().addTab(tab);  
	    }  
	  
	    private void setupTest3(){  
	        Tab tab = this.getActionBar().newTab();  
	        tab.setContentDescription("Tab 3");  
	        tab.setText( this.getString(R.string.cart_tab_seller));  
	        tab.setTabListener(this);  
	        getActionBar().addTab(tab);  
	    }  
void addListener()
{
	OnPageChangeListener pageChangeListener=new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
	
				getActionBar().setSelectedNavigationItem(arg0);
				
	
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
	};
   viewPager.setOnPageChangeListener(pageChangeListener);

}
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(arg0.getPosition());
	}
	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	 public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.cart, menu);
	        return true;
	    }
	
	   public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
		   switch (item.getItemId()) {
		case android.R.id.home:
            Intent intent=new Intent(this,IndexActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            this.overridePendingTransition(R.anim.pagetoleft,R.anim.pagetoleft2);
			
			
			return true;
			

		default:
			  return super.onOptionsItemSelected(item);
		   }
}}
