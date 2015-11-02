package JSON;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import HTTPUtil.HttpCallback;

import android.util.Log;

import com.baidu.mapapi.model.LatLng;

public class DataList {

	
	public static List<Map<String,Object>> getRestaurants(String response)
	{
		List<Map<String,Object>> lsmap=new ArrayList<Map<String,Object>>();
		try {
			JSONObject jsob=new JSONObject(response);
			jsob=new JSONObject(jsob.getString("result"));
			JSONArray jsarr=new JSONArray(jsob.getString("data"));
			Map<String,Object> map;
			for(int i=0;i<jsarr.length();i++)
			{
				jsob=jsarr.getJSONObject(i);
				map=new HashMap<String,Object>();
				map.put("title", jsob.get("title"));
				map.put("province", jsob.get("province"));
				map.put("city", jsob.get("city"));
				map.put("address", jsob.get("address"));
				map.put("tags", jsob.get("tags"));
				map.put("id", jsob.get("id"));
                lsmap.add(map);
                Log.d("obj", map.get("title").toString());
			}
			
			return lsmap;
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
}
