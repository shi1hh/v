package com.example.hungrytest;

import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.model.LatLng;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class LocationActivity extends Activity implements OnClickListener {

	private Location lac;
	private String provider;
	private LocationManager locm;
	private Activity context;
	 Button btn_location;
	 Button btn_back_loc;
		static final String LOG_TAG ="LocationActivity";
	public LocationClient mLocationClient=null;
	public BDLocationListener myListener=new MyLocationListener();
	//定位GPS
	public class MyLocationListener implements BDLocationListener
	{

		@Override
		public void onReceiveLocation(BDLocation arg0) {
			// TODO Auto-generated method stub
			if (arg0 == null) {
				return;
			}
			Intent t1=new Intent();
			Bundle bundle=new Bundle();
			bundle.putString("address",arg0.getProvince()+arg0.getCity()+arg0.getStreet());
			bundle.putDouble("longitude", arg0.getLongitude());
			bundle.putDouble("latitude", arg0.getLatitude());
			bundle.putFloat("radius", arg0.getRadius());
			bundle.putFloat("direction", arg0.getDirection());
			Log.d(LOG_TAG, "location="+ arg0.getLatitude()+"   "+arg0.getLongitude()+"oo");
	        t1.putExtras(bundle);
			context.setResult(RESULT_OK, t1);
			mLocationClient.stop();
			context.finish();
		}
		
	}
	
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setlocation);
        this.context=this;
        init();
   
    }
    void init()
    {
    	initId();
        initLocation();
        addListener();
    }
    void initId()
    {
    	 btn_location=(Button) findViewById(R.id.btn_location);
    	 btn_back_loc=(Button) findViewById(R.id.btn_back_loc);
    }
    //控件初始化
    public void addListener()
    {
    	btn_back_loc.setOnClickListener(this);
        btn_location.setOnClickListener(this);
    }
    //GPS定位设置
    public void initLocation()
    {
    	   mLocationClient=new LocationClient(this.getApplicationContext());
           mLocationClient.registerLocationListener(myListener);
   
           	LocationClientOption option=new LocationClientOption();
           	option.setAddrType("all");
           	
           	option.setLocationMode(LocationMode.Hight_Accuracy);
           	option.setCoorType("bd09ll");
           	option.setScanSpan(0);
           	option.setIsNeedAddress(true);
           	option.setOpenGps(true);
           	option.setNeedDeviceDirect(true);
           	mLocationClient.setLocOption(option);
      
    }

    
  
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.btn_location:
			mLocationClient.start();
			break;
		case R.id.btn_back_loc:
			Intent intent=new Intent(this,IndexActivity.class);
			startActivity(intent);
			this.finish();
			overridePendingTransition(R.anim.pagetoleft, R.anim.pagetoleft2);
			break;
		}
	}
 

	
    
	
	
	
	
}
