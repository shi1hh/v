package Fragments;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobUser.BmobThirdUserAuth;

import com.example.hungrytest.Login;
import com.example.hungrytest.R;
import com.example.hungrytest.UserOpera;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Fragmentwode extends Fragment {
	View view;
	LayoutInflater layoutInflater;
	TextView textView_login_note;
	TextView textView_login;
	ImageView imageView_figure;
	RelativeLayout login;
	Button button_setting;
	@Override
	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2)
	{
		view=arg0.inflate(R.layout.fragment_wode, null);
		layoutInflater=arg0;
        init();
        
	
		
		
		
		return view;
	}
	
	private void init() {
		// TODO Auto-generated method stub
		initID();
        getUser();
	}
	private void initID() {
		textView_login_note=(TextView) view.findViewById(R.id.textView_login_note);
		button_setting=(Button) view.findViewById(R.id.button_setting);
		textView_login=(TextView) view.findViewById(R.id.textView_login);
		imageView_figure=(ImageView)view.findViewById(R.id.imageView_figure);
		login=(RelativeLayout) view.findViewById(R.id.login);
		login.setOnClickListener(clickListener);
		button_setting.setOnClickListener(clickListener);
	}
	private void getUser() {
		if(Login.mQQAuth!=null)
		{
			if(Login.mQQAuth.isSessionValid())
			{
				  UserInfo mInfo = new UserInfo(getActivity(),Login.mQQAuth.getQQToken());
				  mInfo.getUserInfo(new IUiListener() {
					
					@Override
					public void onError(UiError arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onComplete(Object arg0) {
						// TODO Auto-generated method stub
						try {
							JSONObject json = (JSONObject) arg0;
							String nickString=json.getString("nickname");
							textView_login.setText(nickString);
							String path = json.getString("figureurl_qq_2");
							MyUtils.ImgLoader.LoadURLImg(path, imageView_figure);
							textView_login_note.setText("   °ó¶¨ÊÖ»ú>>>>");
							
				
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					@Override
					public void onCancel() {
						// TODO Auto-generated method stub
						
					}
				});
			}
		}
	}
	OnClickListener clickListener=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch (arg0.getId()) {
			case R.id.login:
				Intent intent=new Intent(Fragmentwode.this.getActivity(),Login.class);
				startActivity(intent);
				Fragmentwode.this.getActivity().overridePendingTransition(R.anim.pagetoright, R.anim.pagetoright2);
				break;
			case R.id.button_setting:
				
				Intent intent2=new Intent(Fragmentwode.this.getActivity(),UserOpera.class);
				startActivity(intent2);
				Fragmentwode.this.getActivity().overridePendingTransition(R.anim.pagetoright, R.anim.pagetoright2);
				break;
			default:
				break;
			}
		}
	};
}