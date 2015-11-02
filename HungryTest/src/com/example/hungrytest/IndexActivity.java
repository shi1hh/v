package com.example.hungrytest;

import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiSearch;

import Fragments.Fragmentdingdan;
import Fragments.Fragmentfaxian;
import Fragments.Fragmentwaimai;
import Fragments.Fragmentwode;
import Widget.MarqueeTextView;
import adp.MyFragmentPagerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class IndexActivity extends FragmentActivity {

	/**
	 * ViewPager+Radio+Fragment控制导航
	 */
	ViewPager vpPager;
	RadioGroup radioGroup;
	List<View> views=new ArrayList<View>();//ViewPager数据
	MarqueeTextView marqueeTextView; //走马灯
	List<Fragment> fragments;//各个导航到的Fragment
	
	static final String LOG_TAG ="IndexActivity";
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index);
        init();
        goIntent();
   
    }
    private void goIntent() {
		Intent intent=getIntent();
		int fmNum=intent.getIntExtra("fragment",-1);
    	if(fmNum!=-1)
    	{
    		vpPager.setCurrentItem(fmNum);
    	}
	}
    public void init()
    {    
    	initViewPager();
    	initRadio();
    	addListener();
    }

    public void  initViewPager() {
     	vpPager=(ViewPager) findViewById(R.id.viewpager_main);
    	fragments =new ArrayList<Fragment>();
    	Fragmentdingdan fragmentdingdan=new Fragmentdingdan();
    	Fragmentfaxian fragmentfaxian=new Fragmentfaxian();
    	Fragmentwode fragmentwode=new Fragmentwode();
    	Fragmentwaimai fragmentwaimai=new Fragmentwaimai();
        fragments.add(fragmentwaimai);
        fragments.add(fragmentdingdan);
        fragments.add(fragmentfaxian);
      	fragments.add(fragmentwode);
        MyFragmentPagerAdapter fragmentPagerAdapter=new MyFragmentPagerAdapter(this.getSupportFragmentManager(),fragments);
    	vpPager.setAdapter(fragmentPagerAdapter);
        vpPager.setCurrentItem(0);
        vpPager.setOffscreenPageLimit(3);
	}
    
    
    
    
    
    public void initRadio()
    {
    	radioGroup=(RadioGroup) findViewById(R.id.radioGroup_navigation);
    
	
    }
    /**
     * 监听Radio选择事件+ViewPager选择撤换事件
     */
    public void addListener()
    {    	
    	MyOnCheckedChangeListenr checkedChangeListenr=new MyOnCheckedChangeListenr(vpPager);
	    radioGroup.setOnCheckedChangeListener(checkedChangeListenr);
	    MyOnPageChangeListener onPageChangeListener=new MyOnPageChangeListener(radioGroup);
	    vpPager.setOnPageChangeListener(onPageChangeListener);	
    }
    /**
     * 底部导航监听，用于控制ViewPager撤换F
     * @author Administrator
     *
     */
    class MyOnCheckedChangeListenr implements OnCheckedChangeListener
    {
    	private ViewPager vPager;

        public MyOnCheckedChangeListenr(ViewPager vPager)
        {
        	this.vPager=vPager;
        }
    	
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.radio_waimai:
				vpPager.setCurrentItem(0);
				break;
			case R.id.radio_dingdan:
				vpPager.setCurrentItem(1);
				break;
			case R.id.radio_faxian:
				vpPager.setCurrentItem(2);
				break;
			case R.id.radio_wode:
				vpPager.setCurrentItem(3);
				break;
			}
		}
    	
    }
    /**
     * ViewPager翻页监听
     * 用于控制底部导航
     * @author Administrator
     *
     */
    class MyOnPageChangeListener implements OnPageChangeListener
    {
    	private RadioGroup radioGroup;
    	
    	public MyOnPageChangeListener(RadioGroup radioGroup)
    	{
    		this.radioGroup=radioGroup;
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
			switch (arg0) {
			case 0:
				this.radioGroup.check(R.id.radio_waimai);
				break;
			case 1:
				this.radioGroup.check(R.id.radio_dingdan);
				break;
			case 2:
				this.radioGroup.check(R.id.radio_faxian);
				break;
			case 3:
				this.radioGroup.check(R.id.radio_wode);
				break;

			}
		}

    }
	//接受GPS定位
    /**
     * 注释为备用地图显示
     * @param requestCode
     * @param resultCode
     * @param data
     */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data); 
		if(resultCode == RESULT_OK)
		{
			//map.setVisibility(View.VISIBLE);
			//mBaiduMap.setMyLocationEnabled(true);
			Bundle bundle=data.getExtras();
			marqueeTextView=(MarqueeTextView) findViewById(R.id.Textview_waimai_tile_2);
			marqueeTextView.setText(bundle.getString("address"));
			/*		MyLocationData locationdata=new MyLocationData.Builder().accuracy(bundle.getFloat("radius"))
					.latitude(bundle.getDouble("latitude"))
					.longitude(bundle.getDouble("longitude"))
					.direction(bundle.getFloat("direction")).build();
	  	mBaiduMap.setMyLocationData(locationdata);
			mCurrentMarker=BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
			mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,true,mCurrentMarker));
        LatLng l=new LatLng(bundle.getDouble("latitude"),bundle.getDouble("longitude"));

            MapStatusUpdate mapup=MapStatusUpdateFactory.newLatLngZoom(l, 18);
            mBaiduMap.animateMapStatus(mapup);
			
            
            mPoiSearch=PoiSearch.newInstance();
            mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
            mPoiSearch.searchNearby(new PoiNearbySearchOption().location(l).keyword("美食").pageNum(0).radius(1000));
            mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener(){

						@Override
						public boolean onMarkerClick(Marker arg0) {
							// TODO Auto-generated method stub
							Bundle bd=arg0.getExtraInfo();
							LatLng ll = arg0.getPosition(); 
							TextView tv=new TextView(getApplicationContext());
							tv.setBackgroundResource(R.drawable.popup);
							tv.setPadding(30, 20, 30, 50); 
							tv.setText(bd.getString("name"));	
							InfoWindow infowindow=new InfoWindow(BitmapDescriptorFactory.fromView(tv), ll,-47,new OnInfoWindowClickListener(){

								@Override
								public void onInfoWindowClick() {
									// TODO Auto-generated method stub
									mBaiduMap.hideInfoWindow();
								}
								
							});
							mBaiduMap.showInfoWindow(infowindow);
							Log.d("map", bd.getString("name"));
							return true;
						}});
			*/
		}
 
}}
