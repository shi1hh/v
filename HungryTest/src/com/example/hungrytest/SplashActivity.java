package com.example.hungrytest;



import android.R.bool;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

public class SplashActivity extends Activity {

	private final int DELAYTIME_SPLASH=3000;
	private final int GO_GUI=1;
	private final int GO_MAIN=2;
	private final String SHAREPREFERRENCE_NAME="first_prf";

	
	Handler handler=new Handler(){
		public void  handleMessage(Message message) {
			switch (message.what) {
			case GO_GUI:
				Intent i1=new Intent(SplashActivity.this,GuiActivity.class);
				SplashActivity.this.startActivity(i1);
				SplashActivity.this.finish();
				break;
			case GO_MAIN:
				Intent i2=new Intent(SplashActivity.this,MainActivity.class);
				SplashActivity.this.startActivity(i2);
				SplashActivity.this.finish();
				break;
			}
		}
	};
	
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        init();

    
    }
    public void init()
    {
    	SharedPreferences prf=this.getSharedPreferences(SHAREPREFERRENCE_NAME, MODE_PRIVATE);
    	boolean isfirst=prf.getBoolean("isfirst", true);
    	if(!isfirst)
    	{
    		handler.sendEmptyMessageDelayed(GO_GUI, DELAYTIME_SPLASH);
    	}
    	else {
    		handler.sendEmptyMessageDelayed(GO_GUI, DELAYTIME_SPLASH);
			
    		
		}
    }
	
    
	
}
