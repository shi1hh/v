package Fragments;

import java.util.List;
import java.util.Map;

import com.example.hungrytest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class FragmentSellerMap extends Fragment {

	
	View view;
	LayoutInflater layoutInflater;
	GridView gridView_faxian;
    List<Map<String, Object>> lsmap;
	@Override
	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2)
	{
		
		view=arg0.inflate(R.layout.fragment_faxian, null);
		layoutInflater=arg0;
		init();
		
		
		return view;
	}
	private void init() {
		
	}
	
	 
}
