package com.example.hungrytest;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.BmobUser.BmobThirdUserAuth;
import cn.bmob.v3.listener.OtherLoginListener;

import com.example.hungrytest.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {

	Button button_back;
	TextView textView_loginwx;
	TextView textView_loginqq;
	
	public static QQAuth mQQAuth;
	private UserInfo mInfo;
	private Tencent mTencent;
	private final String APP_ID = "222222";
	public final String LOG_TAG = "Login";

	public static String mAppid;
	
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        init();
        addListener();
       
    }
    public void init()
    {
    	initID();
    	
    }
    public void initID()
    {
    	textView_loginwx=(TextView) findViewById(R.id.textView_loginwx);
    	button_back=(Button) findViewById(R.id.button_back);
    	textView_loginqq=  (TextView) findViewById(R.id.textView_loginqq);
    }
    public void addListener()
    {
    	
    	textView_loginwx.setOnClickListener(this);
    	textView_loginqq.setOnClickListener(this);
    	button_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(Login.this,IndexActivity.class);
				startActivity(intent);
				Login.this.finish();
				Login.this.overridePendingTransition(R.anim.pagetoleft, R.anim.pagetoleft2);
			}
		});
    }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.textView_loginwx:
			Toast.makeText(this, "eeer1", Toast.LENGTH_SHORT).show();
			break;
		case R.id.textView_loginqq:
			login();
			break;
		default:
			break;
		}
	}
	private void login() {
		final Context context = Login.this;
		final Context ctxContext = context.getApplicationContext();

		mAppid = APP_ID;
		mQQAuth = QQAuth.createInstance(mAppid, ctxContext);
		mTencent = Tencent.createInstance(mAppid, Login.this);
		if(!mQQAuth.isSessionValid())
		{
		
		IUiListener listener = new BaseUiListener() {
			@Override
			protected void doComplete(JSONObject values) {
				Log.d(LOG_TAG, "docomlete");
				
				try {
					String token = values.getString(com.tencent.connect.common.Constants.PARAM_ACCESS_TOKEN);
					String expires = values.getString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN);
					String openId = values.getString(com.tencent.connect.common.Constants.PARAM_OPEN_ID);
					final BmobThirdUserAuth authInfo = new BmobThirdUserAuth(BmobThirdUserAuth.SNS_TYPE_QQ,token, expires,openId);
					
					BmobUser.loginWithAuthData(getApplicationContext(), authInfo, new OtherLoginListener() {
						
						@Override
						public void onSuccess(JSONObject userAuth) {
							// TODO Auto-generated method stub
							Log.d(LOG_TAG,authInfo.getSnsType()+"登陆成功返回:"+userAuth);
							Intent intent=new Intent(Login.this,IndexActivity.class);
							intent.putExtra("fragment", 3);
							startActivity(intent);
							finish();
						}
						
						@Override
						public void onFailure(int code, String msg) {
							// TODO Auto-generated method stub
							Toast.makeText(getApplicationContext(), "��¼ʧ��", Toast.LENGTH_SHORT).show();
						}
						
					});
					
					
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		mQQAuth.login(this, "all", listener);
		mTencent.login(this, "all", listener);}
	}
	
/*	private void updateUserInfo()
	{
		if (mQQAuth != null && mQQAuth.isSessionValid()) {
		
			IUiListener listener = new IUiListener() {

				@Override
				public void onCancel() {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onComplete(Object arg0) {
					// TODO Auto-generated method stub
					try {
					//	Log.d("222", ((JSONObject)arg0).getString("nickname"));
						Intent intent=new Intent(Login.this,IndexActivity.class);
						intent.putExtra("fragment", 3);
						intent.putExtra("nickname", ((JSONObject)arg0).getString("nickname"));
						startActivity(intent);
						finish();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				@Override
				public void onError(UiError arg0) {
					// TODO Auto-generated method stub
				}};
				mInfo = new UserInfo(this, mQQAuth.getQQToken());
				mInfo.getUserInfo(listener);
		}
	}
	*/
	
	
	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(Object response) {
		Log.d(LOG_TAG, "oncomlete");
			doComplete((JSONObject) response);
		}

		protected void doComplete(JSONObject values) {

		}

		@Override
		public void onError(UiError e) {
		
		}

		@Override
		public void onCancel() {
		
		}
	}
	
}
