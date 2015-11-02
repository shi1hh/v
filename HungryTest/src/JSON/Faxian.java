package JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.hungrytest.R;

import android.widget.ListView;

public class Faxian {

	public static List<Map<String, Object>> getDataGridView() {
		List<Map<String, Object>> lsmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("logo", R.drawable.faxiangrid1);
		map.put("note", "大闸蟹");
		map.put("price", 68);
		map.put("isreward", true);
		lsmap.add(map);

		map = new HashMap<String, Object>();
		map.put("logo", R.drawable.faxiangrid2);
		map.put("note", "鸡尾酒");
		map.put("price", 68);
		map.put("isreward", true);
		lsmap.add(map);

		map = new HashMap<String, Object>();
		map.put("logo", R.drawable.faxiangrid3);
		map.put("note", "小黄人暖手杯");
		map.put("price", 80);
		map.put("isreward", false);
		lsmap.add(map);

		map = new HashMap<String, Object>();
		map.put("logo", R.drawable.faxiangrid4);
		map.put("note", "生日管家");
		map.put("price", 99);
		map.put("isreward", false);
		lsmap.add(map);
		return lsmap;
	}

}
