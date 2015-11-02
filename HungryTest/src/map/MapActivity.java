package map;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.model.LatLng;
import com.example.hungrytest.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;



public class MapActivity extends Activity {
	   private MapView map;
	   private BaiduMap mBaiduMap;
	   private BitmapDescriptor mCurrentMarker;
	   double latitude;
	   double longitude;
	   String address;
	   private BitmapDescriptor bd=BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);
      
       init();
   
    }
    private void getIntentData() {
    	Intent intent=getIntent();
    	  longitude=intent.getDoubleExtra("longitude", -1);
          latitude=intent.getDoubleExtra("latitude", -1);
          address=intent.getStringExtra("address");
          
	}
    private void init() {
    	   map=(MapView) findViewById(R.id.map_view);
    	   mBaiduMap=map.getMap();
    	   mBaiduMap.setMyLocationEnabled(true);
    	   
    		MyLocationData locationdata=new MyLocationData.Builder().accuracy(100)
					.latitude(latitude)
					.longitude(longitude)
					.direction(100).build();
    		mBaiduMap.setMyLocationData(locationdata);
    		
    		mCurrentMarker=BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
			mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,true,mCurrentMarker));
			
			 LatLng l=new LatLng(latitude,longitude);
			
		    MapStatusUpdate mapup=MapStatusUpdateFactory.newLatLngZoom(l, 18);
            mBaiduMap.animateMapStatus(mapup);
            
        	TextView tv=new TextView(getApplicationContext());
			tv.setBackgroundResource(R.drawable.popup);
			tv.setPadding(30, 20, 30, 50); 
			tv.setTextColor(Color.BLACK);
			tv.setText(address);	
            InfoWindow infowindow=new InfoWindow(BitmapDescriptorFactory.fromView(tv), l,-47,null);
        	mBaiduMap.showInfoWindow(infowindow);
    	   
	}
	
	
}
