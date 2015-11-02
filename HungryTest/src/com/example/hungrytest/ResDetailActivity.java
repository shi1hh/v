package com.example.hungrytest;

import java.util.List;
import java.util.Map;

import com.baidu.mapapi.model.LatLng;

import HTTPUtil.HttpCallback;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ResDetailActivity extends Activity implements OnClickListener{

	LatLng location=null;
	Button button_res_back;
	ListView listView_restau;
	TextView tv_restau_address;
	TextView tv_restau_title;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.restaurant);
        initWidget();
        initData();

    
    }
    //初始化列表数据
    void initData()
    {

        HTTPUtil.connect.getRestauData(new HttpCallback(){

			@Override
			public void onFinish(String response) {
				// TODO Auto-generated method stub
				final List<Map<String,Object>> lsmap=JSON.DataList.getRestaurants(response);
				if(lsmap!=null)
				{
					for(int i=0;i<lsmap.size();i++)
					{
						Log.d("map!12",lsmap.get(i).get("title").toString());
					}
					
					runOnUiThread(new Runnable(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							initListView(lsmap);
						}});
				}
			}

			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        
        
    	
    }
    //初始化列表数据
    void initListView(List<Map<String,Object>> lsmap)
    {

    
    
    
    }
    //初始化控件
    void initWidget()
    {
        button_res_back=(Button) findViewById(R.id.button_res_back);
        listView_restau=(ListView) findViewById(R.id.listView_restau);
        button_res_back.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.button_res_back:
			break;
		}
	}
	
	
}
