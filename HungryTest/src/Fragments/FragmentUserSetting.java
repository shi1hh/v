package Fragments;

import java.util.List;
import java.util.Map;

import com.example.hungrytest.Login;
import com.example.hungrytest.R;

import JSON.login;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

public class FragmentUserSetting extends Fragment implements OnClickListener{

	
	View view;
	LayoutInflater layoutInflater;
	Button button_exit;

	@Override
	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2)
	{
		
		view=arg0.inflate(R.layout.fragment_user_setting, null);
		layoutInflater=arg0;
		
		
		return view;
	}
	void initID()
	{
		button_exit=(Button) view.findViewById(R.id.button_exit);
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.button_exit:
			if(Login.mQQAuth!=null)
			{
				if(Login.mQQAuth.isSessionValid())
				{Login.mQQAuth.logout(getActivity().getApplicationContext());}
			}
			break;

		default:
			break;
		}
	}
	
	
}
