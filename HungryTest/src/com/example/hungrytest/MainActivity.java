package com.example.hungrytest;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;//假如用到位置提醒功能，需要import该类
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.location.Poi;
import com.baidu.mapapi.*;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

public class MainActivity extends Activity implements OnClickListener {


   private TextView textView_location;
   private Button btnGo;
   private int CODE_LOCATION=1;
   private MapView map;
   private BaiduMap mBaiduMap;
   private BitmapDescriptor mCurrentMarker;
   private PoiSearch mPoiSearch;
   
  private BitmapDescriptor bd=BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initWidget(); 

    
    }
 //控件初始化
    public void initWidget()
    {
        LinearLayout ll = (LinearLayout)findViewById(R.id.linear_title);
        ll.setOnClickListener(this);
        textView_location=(TextView) findViewById(R.id.textView_location);
        map=(MapView) findViewById(R.id.map_view);
        mBaiduMap=map.getMap();
        btnGo=(Button) findViewById(R.id.btn_go);
        btnGo.setOnClickListener(this);
	}
    

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.linear_title:
			Intent i1=new Intent(MainActivity.this,LocationActivity.class);
		    startActivityForResult(i1,CODE_LOCATION);
		    break;
		case R.id.btn_go:
		
			break;
		}
	}
	//接受GPS定位
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data); 
		if(requestCode==CODE_LOCATION&&resultCode == RESULT_OK)
		{
			map.setVisibility(View.VISIBLE);
			mBaiduMap.setMyLocationEnabled(true);
			Bundle bundle=data.getExtras();
			textView_location.setText(bundle.getString("address"));
			MyLocationData locationdata=new MyLocationData.Builder().accuracy(bundle.getFloat("radius"))
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
			
		}
		
	
	}
	//搜素覆盖物监听
    OnGetPoiSearchResultListener poiListener=new OnGetPoiSearchResultListener(){

		@Override
		public void onGetPoiDetailResult(PoiDetailResult result) {
			// TODO Auto-generated method stub
	
			   
		}

		@Override
		public void onGetPoiResult(PoiResult result) {
			// TODO Auto-generated method stub

			   if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {  
			        return;  
			    } 

                 if (result.error == SearchResult.ERRORNO.NO_ERROR)
                 {	
/*                	 Log.d("poi", result.getAllPoi().get(0).address);
                	 mBaiduMap.clear();
            		 PoiOverlay poioverlay=new PoiOverlay(mBaiduMap);
                     poioverlay.setData(result);
                     poioverlay.addToMap();
                     poioverlay.zoomToSpan();*/
                     mBaiduMap.clear();
                     LatLng latLng=null;
                     OverlayOptions overlayoptions=null;
                     Marker marker=null;
                     for(PoiInfo pinfo:result.getAllPoi())
                     {
                    	latLng=new LatLng(pinfo.location.latitude,pinfo.location.longitude);
                    	overlayoptions=new MarkerOptions().position(latLng).icon(bd).zIndex(5);
                    	marker=(Marker) mBaiduMap.addOverlay(overlayoptions);
                    	Bundle bd=new Bundle();
                    	bd.putString("name", pinfo.address);
                    	marker.setExtraInfo(bd);
                     }
		            
                 }
			
		}
    	
    };
    protected void onResume()
    {
    	super.onResume();
    /*	map.onResume();*/
    }
    protected void onPause()
    {
    	super.onPause();
    	/*map.onPause();*/
    }
    protected void onDestroy()
    {
    	super.onDestroy();
    	/*map.onDestroy();
    	mPoiSearch.destroy();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
